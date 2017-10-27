package com.ztel.app.service.gis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.gis.DeliveryInfoVoMapper;
import com.ztel.app.service.gis.DeliveryInfoVoService;
import com.ztel.app.vo.gis.DeliveryInfoVo;
@Service
public class DeliveryInfoVoServiceImpl implements DeliveryInfoVoService{
	
	@Autowired
	private DeliveryInfoVoMapper deliveryInfoVoMapper = null;

	@Override
	public List<DeliveryInfoVo> getDeliveryInfoList(String searchdate) {
		// TODO Auto-generated method stub
		return deliveryInfoVoMapper.getDeliveryInfoList(searchdate);
	}

}
