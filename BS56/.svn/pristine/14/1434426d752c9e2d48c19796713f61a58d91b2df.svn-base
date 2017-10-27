package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.wms.InventoryLineVoMapper;
import com.ztel.app.service.wms.InventoryLineVoService;
import com.ztel.app.service.wms.InventoryVoService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.wms.ATSCellInfoDetailVo;
import com.ztel.app.vo.wms.InventoryLineVo;
import com.ztel.app.vo.wms.InventoryVo;
@Service
public class InventoryLineVoServiceImpl implements InventoryLineVoService {

	@Autowired
	private InventoryLineVoMapper inventoryLineVoMapper = null;
	
	@Autowired
	private InventoryVoService inventoryVoService = null;
	
	@Override
	public int doInventoryLineAdd(InventoryLineVo inventoryLineVo) {
		// TODO Auto-generated method stub
		return inventoryLineVoMapper.insertSelective(inventoryLineVo);
	}

	/**
	 * 盘点信息完善,需要传入五个区的盘点数据
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doInventoryComplete(String inventoryId, List<ATSCellInfoDetailVo> atsCellList,List<InventoryLineVo> scatteredList,List<SortTroughVo> troughList,List<SortTroughVo> shelfList) {
		// TODO Auto-generated method stub
		BigDecimal fid=new BigDecimal(inventoryId);
		int listLen=0;
		//遍历插入立库盘点数据
		if(atsCellList!=null&&atsCellList.size()>0){
			listLen=atsCellList.size();
			ATSCellInfoDetailVo atsCellInfoDetailVo=new ATSCellInfoDetailVo();
			for(int i=0;i<listLen;i++){
				atsCellInfoDetailVo=atsCellList.get(i);
				InventoryLineVo inventoryLineVo=new InventoryLineVo();
				inventoryLineVo.setInventoryid(fid);
				inventoryLineVo.setCigarettecode(atsCellInfoDetailVo.getCigarettecode());
				inventoryLineVo.setCigarettename(atsCellInfoDetailVo.getCigarettename());
				inventoryLineVo.setBoxqty(atsCellInfoDetailVo.getQty());
				inventoryLineVo.setAreaid(new BigDecimal(Constant.storagearea_lk));
				inventoryLineVo.setInventorytype(new BigDecimal(10));
				
				doInventoryLineAdd(inventoryLineVo);
			}
		}
		//遍历插入散烟区盘点数据
		if(scatteredList!=null&&scatteredList.size()>0){
			listLen=scatteredList.size();
			for(int i=0;i<listLen;i++){
				InventoryLineVo inventoryLineVo=scatteredList.get(i);
				inventoryLineVo.setInventoryid(fid);
				inventoryLineVo.setItemqty(inventoryLineVo.getTotalqty());
				inventoryLineVo.setAreaid(new BigDecimal(Constant.storagearea_sy));
				inventoryLineVo.setInventorytype(new BigDecimal(10));
				
				doInventoryLineAdd(inventoryLineVo);
				
				//移除散烟区数据??
			}
		}
		//遍历插入分拣区盘点数据
		if(troughList!=null&&troughList.size()>0){
			listLen=troughList.size();
			SortTroughVo sortTroughVo=new SortTroughVo();
			for(int i=0;i<listLen;i++){
				sortTroughVo=troughList.get(i);
				InventoryLineVo inventoryLineVo=new InventoryLineVo();
				inventoryLineVo.setInventoryid(fid);
				inventoryLineVo.setCigarettecode(sortTroughVo.getCigarettecode());
				inventoryLineVo.setCigarettename(sortTroughVo.getCigarettename());
				inventoryLineVo.setTroughno(sortTroughVo.getTroughnum());
				inventoryLineVo.setItemqty(sortTroughVo.getLastmantissa());
				inventoryLineVo.setAreaid(new BigDecimal(Constant.storagearea_fj));
				inventoryLineVo.setInventorytype(new BigDecimal(10));
				
				doInventoryLineAdd(inventoryLineVo);
				
				//移除散烟区数据？？，更新通道表尾数------------------
			}
		}
		//遍历插入重力式货架盘点数据
		if(shelfList!=null&&shelfList.size()>0){
			listLen=shelfList.size();
			SortTroughVo sortTroughVo=new SortTroughVo();
			for(int i=0;i<listLen;i++){
				sortTroughVo=shelfList.get(i);
				InventoryLineVo inventoryLineVo=new InventoryLineVo();
				inventoryLineVo.setInventoryid(fid);
				inventoryLineVo.setCigarettecode(sortTroughVo.getCigarettecode());
				inventoryLineVo.setCigarettename(sortTroughVo.getCigarettename());
				inventoryLineVo.setTroughno(sortTroughVo.getTroughnum());
				inventoryLineVo.setBoxqty(sortTroughVo.getLastmantissa());
				inventoryLineVo.setAreaid(new BigDecimal(Constant.storagearea_zlshj));
				inventoryLineVo.setInventorytype(new BigDecimal(10));
				
				doInventoryLineAdd(inventoryLineVo);
				
				//移除散烟区数据？？，更新通道表尾数------------------
			}
		}
		//更新盘点主表状态
		InventoryVo inventoryVo=new InventoryVo();
		inventoryVo.setInventoryid(fid);
		inventoryVo.setStatus(new BigDecimal(10));
		inventoryVoService.doInventoryUpdate(inventoryVo);
	}

	@Override
	public List<InventoryLineVo> getLastInventoryInfoByCond(InventoryLineVo inventoryLineVo) {
		// TODO Auto-generated method stub
		return inventoryLineVoMapper.selectLastInventoryInfoByCond(inventoryLineVo);
	}

	@Override
	public List<InventoryLineVo> getInventoryInfoByCond(InventoryLineVo inventoryLineVo) {
		// TODO Auto-generated method stub
		return inventoryLineVoMapper.selectInventoryInfoByCond(inventoryLineVo);
	}
}
