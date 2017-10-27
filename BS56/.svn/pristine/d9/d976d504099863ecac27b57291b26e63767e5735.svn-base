/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.DeviceTypeMapper;
import com.ztel.app.service.DeviceService;
import com.ztel.app.vo.DeviceType;

/**
 * @author Administrator
 * @since 2017年2月27日
 */
@Service
public class DeviceTypeServiceImpl implements DeviceService {
	
	@Autowired
	private DeviceTypeMapper deviceMapper = null;
	
	public List<DeviceType> searchOneLevelTypeList() {
		return this.deviceMapper.getOneLevelTypeList();
	}
	
	public List<DeviceType> searchTwolLevelTypeByIDList(String tId) {
		return this.deviceMapper.getTwolLevelTypeByIDList(tId);
	}
	
	public List<DeviceType> searchThreelLevelTypeByIDList(String tId) {
		return this.deviceMapper.getThreelLevelTypeByIDList(tId);
	}

}
