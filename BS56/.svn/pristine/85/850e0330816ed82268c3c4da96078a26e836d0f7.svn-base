package com.ztel.app.service.cost.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.cost.VehicleRepairCostVoMapper;
import com.ztel.app.service.cost.VehicleRepairCostVoService;
import com.ztel.app.vo.cost.VehicleRepairCostVo;

@Service
public class VehicleRepairCostVoServiceImpl implements VehicleRepairCostVoService{

    @Autowired
    private VehicleRepairCostVoMapper vehicleRepairCostVoMapper =null;

	@Override
	public void doVehicleRepairCostUpdate(VehicleRepairCostVo vehicleRepairCostVo) {
		// TODO Auto-generated method stub
		vehicleRepairCostVoMapper.updateByPrimaryKeySelective(vehicleRepairCostVo);
	}

	@Override
	public void doVehicleRepairCostAdd(VehicleRepairCostVo vehicleRepairCostVo) {
		// TODO Auto-generated method stub
		vehicleRepairCostVoMapper.insertSelective(vehicleRepairCostVo);
	}
		 
}
	
