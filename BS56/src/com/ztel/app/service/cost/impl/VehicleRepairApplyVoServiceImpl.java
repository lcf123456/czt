package com.ztel.app.service.cost.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.cost.VehicleRepairApplyVoMapper;
import com.ztel.app.persist.mybatis.cost.VehicleRepairCostVoMapper;
import com.ztel.app.service.cost.VehicleRepairApplyVoService;
import com.ztel.app.service.cost.VehicleRepairCostVoService;
import com.ztel.app.vo.cost.VehicleRepairApplyVo;
import com.ztel.app.vo.cost.VehicleRepairCostVo;
import com.ztel.framework.vo.Pagination;

@Service
public class VehicleRepairApplyVoServiceImpl implements VehicleRepairApplyVoService{

    @Autowired
    private VehicleRepairApplyVoMapper vehicleRepairApplyVoMapper =null;
    
    @Autowired
    private VehicleRepairCostVoMapper vehicleRepairCostVoMapper =null;
    
    @Autowired
    private VehicleRepairCostVoService vehicleRepairCostVoService =null;
//	@Override
//	public List<SuppliesImpVo> getSuppliesImpPageList(Pagination<?> page) {
//		page.sortKeyToColumn(sortKeyMapping);
//		return this.suppliesImpVoMapper.getSuppliesImpPageList(page);
//	}
//
//	@Override
//	@Transactional(rollbackFor=Exception.class)
//	public void doRefundSupplies(SuppliesImpVo suppliesImpVo) {
//		//新建SPLTypeStock对象,用来更新库存表信息
//		SPLTypeStockVo splTypeStockVo=new SPLTypeStockVo();
//		splTypeStockVo.setTypeid(suppliesImpVo.getTypeid());
//		splTypeStockVo.setQuantity(suppliesImpVo.getRefundqty().negate());
//		splTypeStockVo.setTotalamount(suppliesImpVo.getTotalamount().negate());
//		
//		// 物资退库
//		this.suppliesImpVoMapper.doSuppliesRefund(suppliesImpVo);
//		//更新物资类别库存与金额
//		splTypeStockVoService.updateTypeStockByImpOrRefund(splTypeStockVo);
//	}
//
//	@Transactional(rollbackFor=Exception.class)
//	public void doInsertSuppliesImp(SuppliesImpVo suppliesImpVo) {
//		//新建SPLTypeStock对象,用来更新库存表信息
//		SPLTypeStockVo splTypeStockVo=new SPLTypeStockVo();
//		splTypeStockVo.setTypeid(suppliesImpVo.getTypeid());
//		splTypeStockVo.setQuantity(suppliesImpVo.getInitqty());
//		splTypeStockVo.setTotalamount(suppliesImpVo.getTotalamount());
//		
//		//插入物资信息
//		this.suppliesImpVoMapper.insertSelective(suppliesImpVo);
//		//更新物资类别库存与金额
//		splTypeStockVoService.updateTypeStockByImpOrRefund(splTypeStockVo);
//	}
//
//	@Override
//	public String getMaxSuppliesCode(String dateStr) {
//		// TODO Auto-generated method stub
//		return this.suppliesImpVoMapper.getMaxSuppliesCode(dateStr);
//	}
//
//	@Override
//	public List<SuppliesImpVo> getSuppliesRefundPageList(Pagination<?> page) {
//		// TODO Auto-generated method stub
//		Map<String, String> sortKeyMap = new HashMap<>();
//		sortKeyMap.put("refunddate", "refunddate");
//		sortKeyMap.put("typename", "typename");
//		sortKeyMap.put("splname", "splname");
//		sortKeyMap.put("suppliername", "suppliername");
//		sortKeyMap.put("refundname", "refundname");
//		page.sortKeyToColumn(sortKeyMap);
//		return this.suppliesImpVoMapper.getSuppliesRefundPageList(page);
//	}
//
//	@Override
//	@Transactional(rollbackFor=Exception.class)
//	public void doSuppliesSettle(List<Integer> idLst) {
//		// TODO Auto-generated method stub
//		if(idLst!=null&&idLst.size()>0){
//			int len=idLst.size();
//			for(int i=0;i<len;i++){
//				suppliesImpVoMapper.doSuppliesSettle(idLst.get(i));
//			}
//		}
//	}
//
//	@Override
//	public List<SuppliesImpVo> getSuppliesSettleList(Map<String, Object> paraMap) {
//		// TODO Auto-generated method stub
//		return this.suppliesImpVoMapper.getSuppliesSettleList(paraMap);
//	}
//
//	@Override
//	public List<SuppliesImpVo> searchSuppliesImpList(Pagination<?> page) {
//		// TODO Auto-generated method stub
//		return this.suppliesImpVoMapper.getSuppliesImpList(page);
//	}
//
//	@Override
//	public List<SuppliesImpVo> getSuppliesImpListByTypeidForStock(String typeid) {
//		// TODO Auto-generated method stub
//		return this.suppliesImpVoMapper.getSuppliesImpListByTypeidForStock(typeid);
//	}
//	
//	/**
//	 * 根据条件获取物资列表
//	 */
//	public List<SuppliesImpVo> getSuppliesImpListByCond(SuppliesImpVo suppliesImpVo){
//		return this.suppliesImpVoMapper.getSuppliesImpListByCond(suppliesImpVo);
//	}

	@Override
	public List<VehicleRepairApplyVo> getVehicleRepairPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		return vehicleRepairApplyVoMapper.getVehicleRepairPageList(page);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doVehicleRepairApplyAdd(VehicleRepairApplyVo vehicleRepairApplyVo,
			VehicleRepairCostVo vehicleRepairCostVo) {
		// TODO Auto-generated method stub
		vehicleRepairApplyVoMapper.insertSelective(vehicleRepairApplyVo);
		//vehicleRepairCostVoMapper.insertSelective(vehicleRepairCostVo);
		vehicleRepairCostVoService.doVehicleRepairCostAdd(vehicleRepairCostVo);
	}

	@Override
	public void doVehicleRepairApplyUpdate(VehicleRepairApplyVo vehicleRepairApplyVo) {
		// TODO Auto-generated method stub
		vehicleRepairApplyVoMapper.updateByPrimaryKeySelective(vehicleRepairApplyVo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doVehicleRepairApplySafeAudit(VehicleRepairApplyVo vehicleRepairApplyVo) {
		 // TODO Auto-generated method stub
		 VehicleRepairApplyVo param=new VehicleRepairApplyVo();
		 param.setId(vehicleRepairApplyVo.getId());
		 param.setStatus(vehicleRepairApplyVo.getStatus());
		 param.setVehiclectrid(vehicleRepairApplyVo.getVehiclectrid());
		 param.setVehiclemainitem(vehicleRepairApplyVo.getVehiclemainitem());
		 param.setUnitid(vehicleRepairApplyVo.getUnitid());
		 param.setRepairtype(vehicleRepairApplyVo.getRepairtype());
		 param.setIsrepeat(vehicleRepairApplyVo.getIsrepeat());
		 param.setEstimatecost(vehicleRepairApplyVo.getEstimatecost());
		 
		 doVehicleRepairApplyUpdate(param);
		 
		 VehicleRepairCostVo vehicleRepairCostVo=new VehicleRepairCostVo();
		 vehicleRepairCostVo.setUnitid(new BigDecimal(vehicleRepairApplyVo.getUnitid()));
		 vehicleRepairCostVo.setId(new BigDecimal(vehicleRepairApplyVo.getId()));
		 vehicleRepairCostVoService.doVehicleRepairCostUpdate(vehicleRepairCostVo);
	}

	@Override
	public VehicleRepairApplyVo getVehicleRepairApplyInfoByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return vehicleRepairApplyVoMapper.selectByPrimaryKey(id);
	}
		 
}
	
