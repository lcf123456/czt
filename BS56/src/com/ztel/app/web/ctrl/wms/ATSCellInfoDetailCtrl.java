/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.wms;

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

import com.ztel.app.service.PubService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.wms.ATSCellInfoDetailVoService;
import com.ztel.app.vo.wms.ATSCellInfoDetailVo;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @since 2017年2月26日
 *盘点信息
 */
@Controller
@RequestMapping("/wms/atscellinfodetail")
public class ATSCellInfoDetailCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(ATSCellInfoDetailCtrl.class);

	@Autowired
	private OperationlogService operationlogService = null;
	
	@Autowired
	private ATSCellInfoDetailVoService	 atsCellInfoDetailVoService = null;
	
	@Autowired
	private PubService  pubService = null;
	
	@RequestMapping("toATSCellInfoDetail")
	public String index(HttpServletRequest request) {
		return "/wms/v_atscelldetail";
	}
	
	/**
	 * 获立库库存信息
	 * @param atsCellInfoDetailVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getATSCellInfoSummary")
	 @ResponseBody
	 public Map<String, Object> getATSCellInfoSummary(ATSCellInfoDetailVo atsCellInfoDetailVo,HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		List<ATSCellInfoDetailVo> ATSCellInfoDetailVoVoList = new ArrayList<ATSCellInfoDetailVo>();
		ATSCellInfoDetailVoVoList = atsCellInfoDetailVoService.getATSCellInfoSummary(atsCellInfoDetailVo);
		
		 result.put("rows",ATSCellInfoDetailVoVoList);  
		 result.put("total",ATSCellInfoDetailVoVoList.size());  
		 
		return result;
	}
	
}
	
    
