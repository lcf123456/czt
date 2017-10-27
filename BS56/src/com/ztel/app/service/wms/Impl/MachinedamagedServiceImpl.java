package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.wms.InBoundLineVoMapper;
import com.ztel.app.persist.mybatis.wms.ItemVoMapper;
import com.ztel.app.persist.mybatis.wms.MachinedamagedLineVoMapper;
import com.ztel.app.persist.mybatis.wms.MachinedamagedVoMapper;
import com.ztel.app.persist.mybatis.wms.StorageAreaInOutVoMapper;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.wms.ItemVo;
import com.ztel.app.vo.wms.MachinedamagedLineVo;
import com.ztel.app.vo.wms.MachinedamagedVo;
import com.ztel.app.vo.wms.StorageAreaInOutVo;
import com.ztel.framework.vo.Pagination;

@Service
public class MachinedamagedServiceImpl implements MachinedamagedService {

	@Autowired
	private MachinedamagedVoMapper machinedamagedVoMapper = null;
	
	@Autowired
	private MachinedamagedLineVoMapper  machinedamagedLineVoMapper = null;
	
	@Autowired 
	private StorageAreaInOutVoMapper storageAreaInOutVoMapper = null;
	
	@Autowired
	private ItemVoMapper itemVoMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public MachinedamagedServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("createtime", "createtime");
		sortKeyMapping.put("id", "id");
	}
	
	@Override
	public List<MachinedamagedVo> selectCigarettedamagedPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return machinedamagedVoMapper.selectCigarettedamagedPageList(page);
	}

	@Transactional(rollbackFor=Exception.class)
	public void insertMachinedamaged(SortTroughVo sortTroughVo,BigDecimal qty,BigDecimal id,Long userid,String mdid){
		MachinedamagedVo machinedamagedVo = new MachinedamagedVo();
		 
		if(mdid.equals("0")||mdid.equals(""))
		{
			machinedamagedVo.setId(id);
			 machinedamagedVo.setQty(qty);
			 machinedamagedVo.setCreateuser(userid);
			 machinedamagedVoMapper.insertSelective(machinedamagedVo);
		}else
		{
			machinedamagedVo.setId(new BigDecimal(mdid));
			machinedamagedVo.setQty(qty);
			machinedamagedVoMapper.updateByPrimaryKeySelective(machinedamagedVo);
		}
			
		 
		//取件条比例和件码
			String barcode = "100000";//缺省
			BigDecimal jtSize = new BigDecimal("50");//默认1件50条
			ItemVo itemVo = new ItemVo();
			itemVo.setItemno(sortTroughVo.getCigarettecode());
			List<ItemVo> itemVoList = itemVoMapper.selectListByCond(itemVo);
			if(itemVoList!=null && itemVoList.size()>0){
				for(int j=0;j<itemVoList.size();j++){
					ItemVo itemVo2 = itemVoList.get(j);
					barcode = itemVo2.getBigboxBar();
					jtSize = itemVo2.getJtSize();
				}
			}
			
		 MachinedamagedLineVo machinedamagedLineVo = new MachinedamagedLineVo();
		 machinedamagedLineVo.setTroughnum(sortTroughVo.getTroughnum());
		 machinedamagedLineVo.setCigarettecode(sortTroughVo.getCigarettecode());
		 machinedamagedLineVo.setCigarettename(sortTroughVo.getCigarettename());
		 machinedamagedLineVo.setDamagedqty(qty);
		 machinedamagedLineVo.setFid(id);
		 machinedamagedLineVo.setBarcode(barcode);
		 machinedamagedLineVoMapper.insertSelective(machinedamagedLineVo);
		 
	}
	
	public void doAudit(MachinedamagedVo machinedamagedVo){
		//1.更新机损烟主表
		machinedamagedVoMapper.updateByPrimaryKeySelective(machinedamagedVo);
		
		//审核通过
		if(machinedamagedVo.getCheckflag().equals("20")){
			//取破损明细主表
			MachinedamagedLineVo machinedamagedLineVo = new MachinedamagedLineVo();
			machinedamagedLineVo.setFid(machinedamagedVo.getId());
			List<MachinedamagedLineVo> machinedamagedLineVoList = machinedamagedLineVoMapper.selectListByCond(machinedamagedLineVo);
			if(machinedamagedLineVoList!=null&&machinedamagedLineVoList.size()>0){
				for(int i=0;i<machinedamagedLineVoList.size();i++){
					MachinedamagedLineVo machinedamagedLineVo1 = machinedamagedLineVoList.get(i);
					
					//2.区域调拨明细（散烟区）入1条记录
					//入区域调拨明细表
					StorageAreaInOutVo storageAreaInOutVo= new StorageAreaInOutVo();
					storageAreaInOutVo.setAreaid(new BigDecimal(Constant.storagearea_sy));//散烟区4
					storageAreaInOutVo.setQty(machinedamagedLineVo1.getDamagedqty());
					storageAreaInOutVo.setInouttype(new BigDecimal("20"));//入
					storageAreaInOutVo.setCigarettecode(machinedamagedLineVo1.getCigarettecode());
					storageAreaInOutVo.setCigarettename(machinedamagedLineVo1.getCigarettename());
					storageAreaInOutVo.setCigarattetype(new BigDecimal("20"));//10 来烟破损  20 机损烟（针对散烟区）30 退货
					storageAreaInOutVo.setCreatetime(new Date());
					storageAreaInOutVo.setStatus(new BigDecimal("20"));//用于自动补货的标志，此处都用20表示完成
					storageAreaInOutVo.setBarcode(machinedamagedLineVo1.getBarcode());//件码
					storageAreaInOutVo.setTaskno(machinedamagedLineVo1.getId()+"");//此处taskno对应破损id
					storageAreaInOutVo.setCreatename(machinedamagedVo.getCheckusername());
					storageAreaInOutVoMapper.insertSelective(storageAreaInOutVo);
					
					//3.区域调拨明细（分拣）出1条记录
					//入区域调拨明细表
					StorageAreaInOutVo storageAreaInOutVo2= new StorageAreaInOutVo();
					storageAreaInOutVo2.setCellno(machinedamagedLineVo1.getTroughnum());//分拣加上通道号即储位号
					storageAreaInOutVo2.setAreaid(new BigDecimal(Constant.storagearea_fj));//分拣3
					storageAreaInOutVo2.setQty(machinedamagedLineVo1.getDamagedqty().multiply(new BigDecimal("-1")));//出的时候为负数
					storageAreaInOutVo2.setInouttype(new BigDecimal("10"));//出
					storageAreaInOutVo2.setCigarettecode(machinedamagedLineVo1.getCigarettecode());
					storageAreaInOutVo2.setCigarettename(machinedamagedLineVo1.getCigarettename());
					storageAreaInOutVo2.setCigarattetype(new BigDecimal("20"));//10 来烟破损  20 机损烟（针对散烟区）30 退货
					storageAreaInOutVo2.setCreatetime(new Date());
					storageAreaInOutVo2.setStatus(new BigDecimal("20"));//用于自动补货的标志，此处都用20表示完成
					storageAreaInOutVo2.setBarcode(machinedamagedLineVo1.getBarcode());//件码
					storageAreaInOutVo2.setTaskno(machinedamagedLineVo1.getId()+"");//此处taskno对应破损id
					storageAreaInOutVo2.setCreatename(machinedamagedVo.getCheckusername());
					storageAreaInOutVoMapper.insertSelective(storageAreaInOutVo2);
				}
			}
		
		}
	}
	
	public void doUpdate(MachinedamagedVo machinedamagedVo){
		machinedamagedVoMapper.updateByPrimaryKeySelective(machinedamagedVo);
	}
}
