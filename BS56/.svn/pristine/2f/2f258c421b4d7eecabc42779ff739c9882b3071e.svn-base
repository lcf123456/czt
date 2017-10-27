/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.framework.web.ctrl.BaseCtrl;
import com.ztel.webservice.WMSBillService;

/**
 * @author Zeal
 * @since 2017年2月26日
 */
@Controller
@RequestMapping("/test")
public class testCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(testCtrl.class);
	
	
	@Autowired
	private WMSBillService wMSBillService = null;
	
	
    /**
     * 一号工程的入库单数据XMl串解析
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/totestfunction")
	public String totestfunction(HttpServletRequest request) {
		return "/index/testfunction";
	}
    
    /**
     * 一号工程的入库单数据XMl串解析
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/toparseXml")
    @ResponseBody
	public String toparseXml(HttpServletRequest request) {
    	String returnXML = wMSBillService.BillScan("");
		return returnXML;
	}
    
    
}
