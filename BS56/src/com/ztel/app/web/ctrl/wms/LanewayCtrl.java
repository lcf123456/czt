/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.wms;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.ztel.app.service.wms.LanewayService;
import com.ztel.app.vo.wms.LanewayVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author yy
 * @since 2017年8月2日
 *巷道表
 */
@Controller
@RequestMapping("/wms/laneway")
public class LanewayCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(LanewayCtrl.class);
	
	@Autowired
	private LanewayService lanewayService = null;
	
	@RequestMapping("toLaneway")
	public String index(HttpServletRequest request) {
		
		return "/wms/v_laneway";
	}
	
	
	@RequestMapping(value="getLanewayPageList")
	 @ResponseBody
	 public Map<String, Object> getLanewayPageList(LanewayVo lanewayVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		Pagination<?> page = this.getPagination(request);
		if (lanewayVo!=null) {
			 page.setParam(lanewayVo);
		}
		List<LanewayVo> lanewayVoList = new ArrayList<LanewayVo>();
		lanewayVoList = lanewayService.selectLanewayPageList(page);
		
		 result.put("rows",lanewayVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	
	
}
	
    
