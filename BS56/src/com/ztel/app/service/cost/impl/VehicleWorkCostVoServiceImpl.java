package com.ztel.app.service.cost.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.cost.VehicleRepairCostVoMapper;
import com.ztel.app.persist.mybatis.cost.VehicleWorkCostVoMapper;
import com.ztel.app.service.cost.VehicleWorkCostVoService;
import com.ztel.app.vo.cost.VehicleRepairCostVo;
import com.ztel.app.vo.cost.VehicleWorkCostVo;

@Service
public class VehicleWorkCostVoServiceImpl implements VehicleWorkCostVoService{

    @Autowired
    private VehicleWorkCostVoMapper vehicleWorkCostVoMapper =null;
    
    @Autowired
    private VehicleRepairCostVoMapper vehicleRepairCostVoMapper =null;

	@Override
	public List<VehicleWorkCostVo> getVehicleWorkCostListByApplyId(BigDecimal fid) {
		// TODO Auto-generated method stub
		return vehicleWorkCostVoMapper.getVehicleWorkCostListByApplyId(fid);
	}

	@Override
	public int doVehicleWorkCostAdd(VehicleWorkCostVo vehicleWorkCostVo) {
		// TODO Auto-generated method stub
		return vehicleWorkCostVoMapper.insertSelective(vehicleWorkCostVo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doVehicleWorkCostAddAndUpdateCostInfo(VehicleWorkCostVo vehicleWorkCostVo) {
		// TODO Auto-generated method stub
		doVehicleWorkCostAdd(vehicleWorkCostVo);
		VehicleRepairCostVo vehicleRepairCostVo=new VehicleRepairCostVo();
		vehicleRepairCostVo.setDid(vehicleWorkCostVo.getFid()+"");
		vehicleRepairCostVo.setWorkeamount(vehicleWorkCostVo.getAmount());
		vehicleRepairCostVoMapper.updateByPrimaryKeySelectiveByDid(vehicleRepairCostVo);
	}
		 
}
	
