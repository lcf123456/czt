package com.ztel.app.service.mobile.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.mobile.OrdersignVoMapper;
import com.ztel.app.service.mobile.OrdersignService;
import com.ztel.app.vo.mobile.OrdersignVo;
import com.ztel.framework.vo.Pagination;

@Service
public class OrdersignServiceImpl implements OrdersignService {

	@Autowired 
	private OrdersignVoMapper ordersignVoMapper = null;
	
private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public OrdersignServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("routecode", "routecode");
	}
	
	@Override
	public List<OrdersignVo> selectOrdersignPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return ordersignVoMapper.selectOrdersignPageList(page);
	}

	public List<OrdersignVo> selectOrdersignReportList(OrdersignVo ordersignVo){
		return ordersignVoMapper.selectOrdersignReportList(ordersignVo);
	}
}
