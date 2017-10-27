/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sq.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.sq.AssessweightVoMapper;
import com.ztel.app.service.sq.AssessweightService;
import com.ztel.app.vo.sq.AssessweightVo;
import com.ztel.framework.vo.Pagination;
@Service
public  class AssessweightServiceImpl implements AssessweightService {
	
	@Autowired
	private AssessweightVoMapper assessweightMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public AssessweightServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("id", "id");
		
	}
	@Transactional(rollbackFor=Exception.class)
	public int deleteAssessweightById(Integer id,AssessweightVo assessweightVo) {
		// TODO 自动生成的方法存根
		if(id!=null&&!"".equals(id)&&assessweightVo!=null&&!"".equals(assessweightVo.getId()))
		{this.assessweightMapper.deleteByPrimaryKey(id);
		this.assessweightMapper.insertSelective(assessweightVo);}
		return 0;
	}
	@Override
	public List<AssessweightVo> getAssessweightList(Pagination<?> page) {
		// TODO 自动生成的方法存根
		page.sortKeyToColumn(sortKeyMapping);
		return this.assessweightMapper.getAssessweightList(page);
		//return null;
	}



}

