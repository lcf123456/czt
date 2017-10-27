package com.ztel.app.persist.mybatis;

import java.util.List;

import com.ztel.app.vo.DeviceType;


/**
 * 
 * @author Administrator
 * @since 2017年2月27日
 */
public interface DeviceTypeMapper {
	
	public List<DeviceType> getOneLevelTypeList();
	
	public List<DeviceType> getTwolLevelTypeByIDList(String params);
	
	public List<DeviceType> getThreelLevelTypeByIDList(String params);
}
