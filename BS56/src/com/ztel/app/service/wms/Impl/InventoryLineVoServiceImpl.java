package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.wms.InventoryLineVoMapper;
import com.ztel.app.service.produce.SortTroughService;
import com.ztel.app.service.wms.InventoryLineVoService;
import com.ztel.app.service.wms.InventoryVoService;
import com.ztel.app.service.wms.ItemstockService;
import com.ztel.app.service.wms.StorageAreaInOutHisService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.wms.ATSCellInfoDetailVo;
import com.ztel.app.vo.wms.InventoryLineVo;
import com.ztel.app.vo.wms.InventorySumVo;
import com.ztel.app.vo.wms.InventoryVo;
import com.ztel.app.vo.wms.ItemstockVo;
@Service
public class InventoryLineVoServiceImpl implements InventoryLineVoService {

	@Autowired
	private InventoryLineVoMapper inventoryLineVoMapper = null;
	
	@Autowired
	private InventoryVoService inventoryVoService = null;
	
	@Autowired
	private SortTroughService sortTroughService = null;
	
	@Autowired
	private StorageAreaInOutHisService storageAreaInOutHisService = null;
	
	@Autowired
	private ItemstockService itemstockService = null;
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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

	/**
	 * 保存盘点信息
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doInventoryCompleteNew(String inventoryId, List<InventorySumVo> sumList,ItemstockVo itemstockVo) {
		// TODO Auto-generated method stub
		itemstockService.insertItemstock(itemstockVo);
		
		BigDecimal fid=new BigDecimal(inventoryId);
		int listLen=0;BigDecimal flagnum=null;
		if(sumList!=null&&sumList.size()>0){
			listLen=sumList.size();
			InventorySumVo sumVo=null;
			for(int i=0;i<listLen;i++){
				sumVo=sumList.get(i);
				//插入立库数据
				//判断立库是否有数据
				flagnum=sumVo.getAtscellqty();
				if(flagnum!=null){
					InventoryLineVo atsCellInfoDetailVo=new InventoryLineVo();
					atsCellInfoDetailVo.setInventoryid(fid);
					atsCellInfoDetailVo.setCigarettecode(sumVo.getCigarettecode());
					atsCellInfoDetailVo.setCigarettename(sumVo.getCigarettename());
					atsCellInfoDetailVo.setItemqty(sumVo.getAtscellqty());
					atsCellInfoDetailVo.setAreaid(new BigDecimal(Constant.storagearea_lk));
					atsCellInfoDetailVo.setInventorytype(new BigDecimal(10));
					doInventoryLineAdd(atsCellInfoDetailVo);
				}
				
				//插入散烟区数据
				flagnum=sumVo.getScatteredqty();
				if(flagnum!=null){
					InventoryLineVo scatteredVo=new InventoryLineVo();
					scatteredVo.setInventoryid(fid);
					scatteredVo.setCigarettecode(sumVo.getCigarettecode());
					scatteredVo.setCigarettename(sumVo.getCigarettename());
					scatteredVo.setItemqty(sumVo.getScatteredqty());
					scatteredVo.setAreaid(new BigDecimal(Constant.storagearea_sy));
					scatteredVo.setInventorytype(new BigDecimal(10));
					doInventoryLineAdd(scatteredVo);
				}
				
				//插入虚拟区数据
				flagnum=sumVo.getVirtualqty();
				if(flagnum!=null){
					InventoryLineVo virtualVo=new InventoryLineVo();
					virtualVo.setInventoryid(fid);
					virtualVo.setCigarettecode(sumVo.getCigarettecode());
					virtualVo.setCigarettename(sumVo.getCigarettename());
					virtualVo.setItemqty(sumVo.getVirtualqty());
					virtualVo.setAreaid(new BigDecimal(Constant.storagearea_virtual));
					virtualVo.setInventorytype(new BigDecimal(10));
					doInventoryLineAdd(virtualVo);
				}
				
				//用于更新尾数
				SortTroughVo sortTroughVo=null;
				//插入重力式货架
				flagnum=sumVo.getShelfqty();
				if(flagnum!=null){
					InventoryLineVo shelfVo=new InventoryLineVo();
					shelfVo.setInventoryid(fid);
					shelfVo.setCigarettecode(sumVo.getCigarettecode());
					shelfVo.setCigarettename(sumVo.getCigarettename());
					shelfVo.setTroughno(sumVo.getShelfno());
					shelfVo.setItemqty(sumVo.getShelfqty());
					shelfVo.setAreaid(new BigDecimal(Constant.storagearea_zlshj));
					shelfVo.setInventorytype(new BigDecimal(10));
					doInventoryLineAdd(shelfVo);
					
					//更新尾数
//					sortTroughVo=new SortTroughVo();
//					sortTroughVo.setMantissa(sumVo.getShelfqty());
//					sortTroughVo.setTroughnum(sumVo.getShelfno());
//					sortTroughVo.setCigarettetype(new BigDecimal(20));
//					sortTroughVo.setTroughtype(new BigDecimal(20));
//					sortTroughService.updateMantissaByCond(sortTroughVo);
				}
				//插入分拣区数据
				flagnum=sumVo.getSortingqty();
				if(flagnum!=null){
					InventoryLineVo sortingVo=new InventoryLineVo();
					sortingVo.setInventoryid(fid);
					sortingVo.setCigarettecode(sumVo.getCigarettecode());
					sortingVo.setCigarettename(sumVo.getCigarettename());
					sortingVo.setTroughno(sumVo.getSortingno());
					sortingVo.setItemqty(sumVo.getSortingqty());
					sortingVo.setAreaid(new BigDecimal(Constant.storagearea_fj));
					sortingVo.setInventorytype(new BigDecimal(10));
					doInventoryLineAdd(sortingVo);
					
					//更新尾数
//					sortTroughVo=new SortTroughVo();
//					sortTroughVo.setMantissa(sumVo.getSortingqty());
//					sortTroughVo.setTroughnum(sumVo.getSortingno());
//					sortTroughVo.setCigarettetype(new BigDecimal(20));
//					sortTroughVo.setTroughtype(new BigDecimal(10));
//					sortTroughService.updateMantissaByCond(sortTroughVo);
				}
				//异型烟分拣一区
				flagnum=sumVo.getUnnormalqty1();
				if(flagnum!=null){
					InventoryLineVo un1Vo=new InventoryLineVo();
					un1Vo.setInventoryid(fid);
					un1Vo.setCigarettecode(sumVo.getCigarettecode());
					un1Vo.setCigarettename(sumVo.getCigarettename());
					un1Vo.setTroughno(sumVo.getUnnormalno1());
					un1Vo.setItemqty(sumVo.getUnnormalqty1());
					un1Vo.setAreaid(new BigDecimal(Constant.storagearea_fj));
					un1Vo.setInventorytype(new BigDecimal(10));
					doInventoryLineAdd(un1Vo);
					
					//更新尾数
//					sortTroughVo=new SortTroughVo();
//					sortTroughVo.setMantissa(sumVo.getUnnormalqty1());
//					sortTroughVo.setTroughnum(sumVo.getUnnormalno1());
//					sortTroughVo.setUncigarettetype(new BigDecimal(20));
//					sortTroughVo.setTroughtype(new BigDecimal(10));
//					sortTroughVo.setGroupno(new BigDecimal(1));
//					sortTroughService.updateMantissaByCond(sortTroughVo);
				}
				//异型烟分拣二区
				flagnum=sumVo.getUnnormalqty2();
				if(flagnum!=null){
					InventoryLineVo un2Vo=new InventoryLineVo();
					un2Vo.setInventoryid(fid);
					un2Vo.setCigarettecode(sumVo.getCigarettecode());
					un2Vo.setCigarettename(sumVo.getCigarettename());
					un2Vo.setTroughno(sumVo.getUnnormalno2());
					un2Vo.setItemqty(sumVo.getUnnormalqty2());
					un2Vo.setAreaid(new BigDecimal(Constant.storagearea_fj));
					un2Vo.setInventorytype(new BigDecimal(10));
					doInventoryLineAdd(un2Vo);
					
					//更新尾数
//					sortTroughVo=new SortTroughVo();
//					sortTroughVo.setMantissa(sumVo.getUnnormalqty2());
//					sortTroughVo.setTroughnum(sumVo.getUnnormalno2());
//					sortTroughVo.setUncigarettetype(new BigDecimal(20));
//					sortTroughVo.setTroughtype(new BigDecimal(10));
//					sortTroughVo.setGroupno(new BigDecimal(2));
//					sortTroughService.updateMantissaByCond(sortTroughVo);
				}
				//异型烟分拣公共区
				flagnum=sumVo.getCommonqty();
				if(flagnum!=null){
					InventoryLineVo commonVo=new InventoryLineVo();
					commonVo.setInventoryid(fid);
					commonVo.setCigarettecode(sumVo.getCigarettecode());
					commonVo.setCigarettename(sumVo.getCigarettename());
					commonVo.setTroughno(sumVo.getCommonno());
					commonVo.setItemqty(sumVo.getCommonqty());
					commonVo.setAreaid(new BigDecimal(Constant.storagearea_fj));
					commonVo.setInventorytype(new BigDecimal(10));
					doInventoryLineAdd(commonVo);
					
					//更新尾数
//					sortTroughVo=new SortTroughVo();
//					sortTroughVo.setMantissa(sumVo.getCommonqty());
//					sortTroughVo.setTroughnum(sumVo.getCommonno());
//					sortTroughVo.setUncigarettetype(new BigDecimal(20));
//					sortTroughVo.setTroughtype(new BigDecimal(10));
//					sortTroughVo.setGroupno(new BigDecimal(3));
//					sortTroughService.updateMantissaByCond(sortTroughVo);
				}
			}
			//更新盘点主表状态
			InventoryVo inventoryVo=new InventoryVo();
			inventoryVo.setInventoryid(fid);
			inventoryVo.setStatus(new BigDecimal(10));
			inventoryVoService.doInventoryUpdate(inventoryVo);
			
			//searchtime来自主表记录的产生时间
//			InventoryVo inventoryVo1=inventoryVoService.getInventoryInfo(fid);
//			storageAreaInOutHisService.insertToInOutHis(sdf.format(inventoryVo1.getCreatetime()));
		}
	}

	@Override
	public List<InventoryLineVo> getInventoryTroughInfoByCond(InventoryLineVo inventoryLineVo) {
		// TODO Auto-generated method stub
		return inventoryLineVoMapper.selectInventoryTroughInfoByCond(inventoryLineVo);
	}
}
