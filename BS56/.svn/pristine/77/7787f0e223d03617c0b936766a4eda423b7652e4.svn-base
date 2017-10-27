/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sq.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sq.RouteScoresummaryVoMapper;
import com.ztel.app.service.sq.RouteScoresummaryService;
import com.ztel.app.vo.sq.RouteScoresummaryVo;
import com.ztel.framework.vo.Pagination;

@Service
public  class RouteScoresummaryServiceImpl implements RouteScoresummaryService {
	
	@Autowired
	private RouteScoresummaryVoMapper RouteScoresummaryMapper = null;
	

	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public RouteScoresummaryServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("custid", "custid");
		sortKeyMapping.put("id", "id");
	
	}
	
	public List<RouteScoresummaryVo> searchRouteScoresummaryList(Pagination<?> page) {
		
		page.sortKeyToColumn(sortKeyMapping);

		return this.RouteScoresummaryMapper.getRouteScoresummaryPageList(page);
	}

	@Override
	public List<RouteScoresummaryVo> searchRouteScoresummaryPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);

		return this.RouteScoresummaryMapper.getRouteScoresummarysPageList(page);
	}

	

	
}


