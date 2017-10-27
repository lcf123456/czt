package com.ztel.app.web.ctrl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.produce.OrderService;

@Controller
@RequestMapping("/pub")
public class pubCtrl {
	private static Logger logger = LogManager.getLogger(pubCtrl.class);
	@Autowired
	private OrderService orderService = null;
	
	/**
	 * 获取订单日期,该方法暂时已废弃，订单日期通过TimebydmCtrl取
	 * @param outBoundVo 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getOrderdate")
	 @ResponseBody
	 public Map<String, Object> getOrderdate(HttpServletRequest request) throws Exception{
		 Map<String, Object> map=new HashMap<String, Object>();  
		String date = "";
		 date = orderService.selectOrderdate();
		 map.put("orderdate", date);
		return map;
	}
}
