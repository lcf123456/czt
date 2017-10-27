/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.gis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.gis.DeliveryInfoVoService;
import com.ztel.app.vo.gis.DeliveryInfoVo;
import com.ztel.app.vo.gis.GroupinfoVo;
import com.ztel.framework.web.ctrl.BaseCtrl;

@Controller
@RequestMapping("/gis/deliveryinfo")
public class DeliveryInfoVoCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(DeliveryInfoVoCtrl.class);
	
	//用于初始化数据的时候，进行数据类型转换，String类型转为Date类型
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@Autowired
	private DeliveryInfoVoService deliveryInfoVoService = null;
	
	@RequestMapping("toDeliveryInfo")
	public String index(HttpServletRequest request) {
		
		return "/gis/v_deliveryinfo";
	}

	 @RequestMapping(value="getDeliveryInfoList")
	 @ResponseBody
	 public  List<DeliveryInfoVo> getDeliveryInfoList(HttpServletRequest request,GroupinfoVo groupinfoVo)
	 {
		 String searchdate=request.getParameter("searchdate");
		 List<DeliveryInfoVo> deliveryInfoList=deliveryInfoVoService.getDeliveryInfoList(searchdate);
		 return deliveryInfoList;
				 
	 }
}
	