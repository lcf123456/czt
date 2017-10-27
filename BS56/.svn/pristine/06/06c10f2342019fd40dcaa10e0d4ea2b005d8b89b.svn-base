package com.ztel.app.service.produce.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.produce.SortTroughVoMapper;
import com.ztel.app.service.produce.SortTroughService;
import com.ztel.app.service.wms.MantissaService;
import com.ztel.app.service.wms.StorageAreaInOutService;
import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.wms.InventoryLineVo;
import com.ztel.app.vo.wms.MantissaVo;
import com.ztel.app.vo.wms.StorageAreaInOutVo;
import com.ztel.framework.vo.Pagination;
@Service
public class SortTroughServiceImpl implements SortTroughService {

	@Autowired
	private SortTroughVoMapper sortTroughMapper = null;
	@Autowired
	private MantissaService  mantissaService = null;
	@Autowired
	private StorageAreaInOutService storageAreaInOutService = null;
	@Override
	public List<SortTroughVo> getSortTroughPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		return sortTroughMapper.getSortTroughPageList(page);
	}
	@Override
	public void updateByPrimaryKeySelective(SortTroughVo vo) {
		// TODO Auto-generated method stub
		sortTroughMapper.updateByPrimaryKeySelective(vo);
	}
	@Override
	public List<SortTroughVo> getSorttroughList(SortTroughVo sorttroughVo) {
		// TODO 自动生成的方法存根
		return sortTroughMapper.getSorttroughList(sorttroughVo);
	}
	
	@Override
	public List<SortTroughVo> selectListByCond(SortTroughVo sortTroughVo){
		return sortTroughMapper.selectListByCond(sortTroughVo);
	}
	@Override
	public List<SortTroughVo> getSortTroughSummaryByCond(StorageAreaInOutVo storageAreaInOutVo,
			SortTroughVo sorttroughVo) {
				// TODO Auto-generated method stub
				//取调拨数据
				List<StorageAreaInOutVo> inOutList=storageAreaInOutService.getLastCellInOutInfoByCond(storageAreaInOutVo);
				//取通道尾数
				List<SortTroughVo> inventoryLineList=getSorttroughList(sorttroughVo);
				for(int i=0;i<inOutList.size();i++)
				{
					StorageAreaInOutVo v=inOutList.get(i);
					String cellno=v.getCellno();
					for(int j=0;j<inventoryLineList.size();j++)
					{
						SortTroughVo svo=inventoryLineList.get(j);
						if(svo.getTroughnum().equals(cellno))
						{
							svo.setLastmantissa(svo.getMantissa().add(v.getQty()));
							svo.setInoutqty(v.getQty());
							//svo.setf(v.getQty());
							break;
						}
					}
				}
				
				for(int i=0;i<inventoryLineList.size();i++)
				 {
					 SortTroughVo temp=inventoryLineList.get(i);
					 MantissaVo mVo=new MantissaVo();
						mVo.setAreaid(storageAreaInOutVo.getAreaid());
						//mVo.setCigarettename(vo.getCigarettename());
						mVo.setCigarettecode(temp.getCigarettecode());
						mVo.setCreatedate(new Date());
						mVo=	mantissaService.getMantissa(mVo);
						if(mVo!=null)
						{
							temp.setFillqty(mVo.getQty());
							temp.setActcount(new BigDecimal(999));
						}
						else
						{
							temp.setFillqty(new BigDecimal("0"));
						}
				 }
//				for(int j=0;j<inventoryLineList.size();j++)
//				{
//				inventoryLineList.get(j).setMantissa(new BigDecimal("0") );
//				}
//				HashMap<Integer, SortTroughVo>map=new HashMap<>();
//				int inOutLen=inOutList.size();
//				int lineLen=inventoryLineList.size();
//				//遍历盘点尾数到map中，然后遍历调拨数据，匹配烟草品牌进行数据加减处理
//				for(int i=0;i<lineLen;i++){
//					SortTroughVo lineVo=inventoryLineList.get(i);
//					map.put(Integer.parseInt(lineVo.getTroughnum()), lineVo);
//				}
//				//若上期盘点尾数包含调拨汇总中的品牌，则已上期尾数为基准进行加减；
//				//若上期盘点尾数不包含调拨汇总中的品牌，则直接将凋敝汇总数据加入到Map中
//				//String cellNo="";
//				int cellNo=0;
//				BigDecimal lineQty=new BigDecimal(0);
//				BigDecimal inoutQty=new BigDecimal(0);
//				BigDecimal totalQty=new BigDecimal(0);
//				
//				for(int i=0;i<inOutLen;i++){
//					StorageAreaInOutVo areaInOutVo=inOutList.get(i);
//					cellNo=Integer.parseInt(areaInOutVo.getCellno());
//					SortTroughVo vo=new SortTroughVo();
//					//若上期盘点尾数包含调拨汇总中的品牌，则已上期尾数为基准进行加减；
//					if(map.containsKey(cellNo)){
//						vo=map.get(cellNo);
//						lineQty=vo.getMantissa();
//						inoutQty=areaInOutVo.getQty();
//						totalQty=lineQty.add(inoutQty);
//						if(totalQty==null)totalQty=new BigDecimal(0);
//						vo.setInoutqty(inoutQty);
//						vo.setLastmantissa(totalQty);
//						//map.put(cigaretteCode, vo);
//					}
////					else{
////						//若上期盘点尾数不包含调拨汇总中的品牌，则直接将调拨汇总数据加入到Map中
////						vo.setCigarettecode(cigaretteCode);
////						vo.setCigarettename(areaInOutVo.getCigarettename());
////						vo.setItemqty(new BigDecimal(0));
////						vo.setInoutqty(areaInOutVo.getQty());
////						vo.setTotalqty(areaInOutVo.getQty());
////						//map.put(cigaretteCode, vo);
////					}
//					map.put(cellNo, vo);
//				}
//				
//				//List<InventoryLineVo> lineList=(List<InventoryLineVo>) map.values();
//				List<SortTroughVo> lineList=new ArrayList<>();
//				Object[] keys=map.keySet().toArray();
//				for(int i=0;i<keys.length;i++){
//					lineList.add(map.get(Integer.parseInt(keys[i].toString())));
//				}
				return inventoryLineList;
	}
	@Override
	public int updateMantissaByCond(SortTroughVo sortTroughVo) {
		// TODO Auto-generated method stub
		return sortTroughMapper.updateMantissaByCond(sortTroughVo);
	}

}
