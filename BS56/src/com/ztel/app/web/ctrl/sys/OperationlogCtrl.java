/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.sys;


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

import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.sys.OperationlogVo;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author lcf
 * @since 2017年6月16日
 * 操作日志
 */
@Controller
@RequestMapping("/sys/operationlog")
public class OperationlogCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(OperationlogCtrl.class);
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	
	@RequestMapping("toOperationlog")
	public String toOperation(HttpServletRequest request) {
		return "/sys/v_operationlog";
	}
	
	@RequestMapping("getOperationlogList")
	 @ResponseBody
	 public  Map<String, Object> getOperationlogList(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 String keyword = request.getParameter("keyword");
		 List<OperationlogVo> ones = operationlogService.getOperationlogList(keyword);
		 

		 result.put("rows",ones);  
		 result.put("total",ones.size());  

		 return result;
	 }
	
	
}
