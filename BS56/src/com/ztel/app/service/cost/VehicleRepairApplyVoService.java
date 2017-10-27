/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.cost;

import java.util.List;

import com.ztel.app.vo.cost.VehicleRepairApplyVo;
import com.ztel.app.vo.cost.VehicleRepairCostVo;
import com.ztel.framework.vo.Pagination;

/**
 * @author NJ
 * @since 2017年6月26日
 */
public interface VehicleRepairApplyVoService {
	
	/**
	 * 车辆维修申报列表
	 * @param page
	 * @return
	 */
    List<VehicleRepairApplyVo> getVehicleRepairPageList(Pagination<?> page);
    
    /**
     * 维修申报新增   需要在申报表插入数据,还要在费用明细表插入数据
     * @param vehicleRepairApplyVo
     * @param vehicleRepairCostVo
     */
    public void doVehicleRepairApplyAdd(VehicleRepairApplyVo vehicleRepairApplyVo,VehicleRepairCostVo vehicleRepairCostVo);
    
    /**
     * 维修申报信息修改  可用于申报
     * @param vehicleRepairApplyVo
     */
    public void doVehicleRepairApplyUpdate(VehicleRepairApplyVo vehicleRepairApplyVo);
    
    public void doVehicleRepairApplySafeAudit(VehicleRepairApplyVo vehicleRepairApplyVo);
    
    public VehicleRepairApplyVo getVehicleRepairApplyInfoByPrimaryKey(String id);
}
