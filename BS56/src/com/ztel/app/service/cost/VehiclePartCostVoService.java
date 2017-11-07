/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.cost;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.cost.VehiclePartCostVo;

/**
 * @author NJ
 * @since 2017年6月26日
 */
public interface VehiclePartCostVoService {
	
	public List<VehiclePartCostVo> getVehiclePartCostListByApplyId(BigDecimal did);
	
	public int doVehiclePartCostAdd(VehiclePartCostVo vehiclePartCostVo);
	
	public void doVehiclePartCostAddAndUpdateCostInfo(VehiclePartCostVo vehiclePartCostVo);
    
}
