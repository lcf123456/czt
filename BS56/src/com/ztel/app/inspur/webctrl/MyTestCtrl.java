/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.inspur.webctrl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.inspur.service.InspurSaleService;
import com.ztel.app.inspur.service.TestService;
import com.ztel.app.inspur.vo.InspurSalecustomerVo;
import com.ztel.app.inspur.vo.TestVo;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author lcf
 * @since 2017年6月16日
 * 操作日志
 */
@Controller
@RequestMapping("/inspur/test")
public class MyTestCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(MyTestCtrl.class);
	
	@Autowired
	private TestService testService = null;
	
	@Autowired
	private InspurSaleService salecustomerService = null;
	
	
	@RequestMapping("toTest")
	public String toOperation(HttpServletRequest request) {
		return "/inspur/v_operationlog";
	}
	
	@RequestMapping("getTestList")
	 @ResponseBody
	 public  Map<String, Object> getOperationlogList(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 String keyword = request.getParameter("keyword");
		 List<TestVo> ones = testService.getList();
		 

		 result.put("rows",ones);  
		 result.put("total",ones.size());  

		 return result;
	 }
	
	@RequestMapping("getInspurCustomerList")
	 @ResponseBody
	 public  Map<String, Object> getInspurCustomerList(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 String keyword = request.getParameter("keyword");
		 List<InspurSalecustomerVo> ones = salecustomerService.selectInspurCustomerList();
		 

		 result.put("rows",ones);  
		 result.put("total",ones.size());  

		 return result;
	 }
	
	
}
