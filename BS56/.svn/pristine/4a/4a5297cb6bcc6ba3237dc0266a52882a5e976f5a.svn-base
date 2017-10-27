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

import com.ztel.app.service.wms.InBoundLineService;
import com.ztel.app.service.wms.InBoundService;
import com.ztel.app.service.wms.StorageAreaService;
import com.ztel.app.vo.wms.InBoundLineVo;
import com.ztel.app.vo.wms.InBoundVo;
import com.ztel.app.vo.wms.StorageAreaVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author yy
 * @since 2017年8月2日
 *区域表
 */
@Controller
@RequestMapping("/wms/storagearea")
public class StorageareaCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(StorageareaCtrl.class);
	
	@Autowired
	private StorageAreaService storageAreaService = null;
	
	@RequestMapping("toStoragearea")
	public String index(HttpServletRequest request) {
		
		return "/wms/v_storagearea";
	}
	
	
	@RequestMapping(value="getStorageAreaPageList")
	 @ResponseBody
	 public Map<String, Object> getStorageAreaPageList(StorageAreaVo storageAreaVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		Pagination<?> page = this.getPagination(request);
		if (storageAreaVo!=null) {
			 page.setParam(storageAreaVo);
		}
		List<StorageAreaVo> storageAreaVoList = new ArrayList<StorageAreaVo>();
		storageAreaVoList = storageAreaService.selectStorageAreaPageList(page);
		
		 result.put("rows",storageAreaVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	
	
}
	
    
