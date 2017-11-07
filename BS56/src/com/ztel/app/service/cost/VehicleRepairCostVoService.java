/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.cost;

import com.ztel.app.vo.cost.VehicleRepairCostVo;

/**
 * @author NJ
 * @since 2017年6月26日
 */
public interface VehicleRepairCostVoService {
	
    public void doVehicleRepairCostUpdate(VehicleRepairCostVo vehicleRepairCostVo);
    
    public void doVehicleRepairCostAdd(VehicleRepairCostVo vehicleRepairCostVo);
    
    /**
     * 维修费用信息修改  根据申报id修改
     * @param vehicleRepairApplyVo
     */
    public void doVehicleRepairCostUpdateByDid(VehicleRepairCostVo vehicleRepairCostVo);
    
}
