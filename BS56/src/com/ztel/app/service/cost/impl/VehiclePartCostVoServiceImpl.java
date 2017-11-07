package com.ztel.app.service.cost.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.cost.VehiclePartCostVoMapper;
import com.ztel.app.persist.mybatis.cost.VehicleRepairCostVoMapper;
import com.ztel.app.service.cost.VehiclePartCostVoService;
import com.ztel.app.vo.cost.VehiclePartCostVo;
import com.ztel.app.vo.cost.VehicleRepairCostVo;

@Service
public class VehiclePartCostVoServiceImpl implements VehiclePartCostVoService{

    @Autowired
    private VehiclePartCostVoMapper vehiclePartCostVoMapper =null;
    
    @Autowired
    private VehicleRepairCostVoMapper vehicleRepairCostVoMapper =null;

	@Override
	public List<VehiclePartCostVo> getVehiclePartCostListByApplyId(BigDecimal did) {
		// TODO Auto-generated method stub
		return vehiclePartCostVoMapper.getVehiclePartCostListByApplyId(did);
	}

	@Override
	public int doVehiclePartCostAdd(VehiclePartCostVo vehiclePartCostVo) {
		// TODO Auto-generated method stub
		return vehiclePartCostVoMapper.insertSelective(vehiclePartCostVo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doVehiclePartCostAddAndUpdateCostInfo(VehiclePartCostVo vehiclePartCostVo) {
		// TODO Auto-generated method stub
		doVehiclePartCostAdd(vehiclePartCostVo);
		VehicleRepairCostVo vehicleRepairCostVo=new VehicleRepairCostVo();
		vehicleRepairCostVo.setDid(vehiclePartCostVo.getDid()+"");
		vehicleRepairCostVo.setMateramount(vehiclePartCostVo.getAmount());
		vehicleRepairCostVoMapper.updateByPrimaryKeySelectiveByDid(vehicleRepairCostVo);
	}
		 
}
	
