/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sq.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sq.IndustrialdriverVoMapper;
import com.ztel.app.service.sq.IndustrialdriverService;

import com.ztel.app.vo.sq.IndustrialdriverVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;

@Service
public  class IndustrialdriverServiceImpl implements IndustrialdriverService {
	
	@Autowired
	private IndustrialdriverVoMapper IndustrialdriverMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public IndustrialdriverServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("drivername", "drivername");
		sortKeyMapping.put("id", "id");
	
	}
	
	public List<IndustrialdriverVo> searchIndustrialdriverList(Pagination<?> page) {
		
		page.sortKeyToColumn(sortKeyMapping);
		System.out.println("driverName =  "+((IndustrialdriverVo)page.getParam()).getDrivername());
		return this.IndustrialdriverMapper.getIndustrialdriverPageList(page);
	}
	
	
	/**
	 * 根据id删除角色
	 */
	public void deleteIndustrialdriverById(List<Integer> ids)
	{
		if(ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				this.IndustrialdriverMapper.deleteByPrimaryKey(id);
			}
		}
	}
	
	
	/**
	 * 修改角色
	 */
	public void updateIndustrialdriver(IndustrialdriverVo industrialdriver)
	{
		if(industrialdriver != null && !industrialdriver.getDrivername().equals("") ) {
				this.IndustrialdriverMapper.updateByPrimaryKeySelective(industrialdriver);
		}
	}

	

	@Override
	public void newIndustrialdriver(IndustrialdriverVo industrialdriver) {
		// TODO Auto-generated method stub
		if(industrialdriver != null && !industrialdriver.getDrivername().equals("") ) {
			this.IndustrialdriverMapper.insertSelective(industrialdriver);
	}
	}



	@Override
	public String getDrivername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IndustrialdriverVo> getIndustrialdriverVoListByFactoryId(Integer factoryid) {
		// TODO Auto-generated method stub
		List<IndustrialdriverVo> industrialdriverList = IndustrialdriverMapper.getIndustrialdriverPageList(factoryid);
		return industrialdriverList;
	}


}
