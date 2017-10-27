package com.ztel.app.service.gis.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.gis.OrderlineinfoVoMapper;
import com.ztel.app.service.gis.OrderlineinfoService;
import com.ztel.app.vo.gis.OrderlineinfoVo;
import com.ztel.framework.vo.Pagination;

@Service
public class OrderlineinfoServiceImpl implements OrderlineinfoService {

	@Autowired 
	private OrderlineinfoVoMapper orderlineinfoVoMapper = null;
	
private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public OrderlineinfoServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("rolename", "rolename");
		sortKeyMapping.put("id", "id");
		sortKeyMapping.put("updatetime", "updatetime");
	}
	
	@Override
	public List<OrderlineinfoVo> getOrderlineinfoPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return orderlineinfoVoMapper.getOrderlineinfoPageList(page);
	}

}
