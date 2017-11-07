/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.cost;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.cost.VehicleWorkCostVo;

/**
 * @author NJ
 * @since 2017年6月26日
 */
public interface VehicleWorkCostVoService {
	
	public List<VehicleWorkCostVo> getVehicleWorkCostListByApplyId(BigDecimal fid);
	
	public int doVehicleWorkCostAdd(VehicleWorkCostVo vehicleWorkCostVo);
	
	public void doVehicleWorkCostAddAndUpdateCostInfo(VehicleWorkCostVo vehicleWorkCostVo);
    
}
