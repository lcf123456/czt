package com.ztel.app.service.produce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.produce.EquipmentFailureVoMapper;
import com.ztel.app.service.produce.EquipmentFailureVoService;
import com.ztel.app.vo.produce.EquipmentFailureVo;
import com.ztel.framework.vo.Pagination;

@Service
public class EquipmentFailureVoServiceImpl implements EquipmentFailureVoService{

    @Autowired
    private EquipmentFailureVoMapper equipmentFailureVoMapper =null;

	@Override
	public List<EquipmentFailureVo> getEquipmentFailureVoPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		return equipmentFailureVoMapper.getEquipmentFailureVoPageList(page);
	}
}
	
