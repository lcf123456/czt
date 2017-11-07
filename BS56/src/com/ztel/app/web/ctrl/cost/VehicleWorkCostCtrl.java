/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.cost;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.cost.VehicleWorkCostVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.cost.VehiclePartCostVo;
import com.ztel.app.vo.cost.VehicleWorkCostVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author NJ
 * @since 2017年6月26日 */
@Controller
@RequestMapping("/cost/vehicleworkcost")
public class VehicleWorkCostCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(VehicleWorkCostCtrl.class);
	
	@Autowired
	private VehicleWorkCostVoService vehicleWorkCostVoService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
//	@RequestMapping("toVehicleRepairReceipt")
//	public String toVehicleRepairReceipt(HttpServletRequest request) {
//		String id=request.getParameter("id");
//		request.setAttribute("id", id);
//		return "/cost/v_vehiclerepairreceipt";
//	}
	 
	 /**
	  * 查找维修车辆工时费列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getVehicleWorkCostListByApplyId")
	 @ResponseBody
	 public void getVehicleWorkCostListByApplyId(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 
		 String fid=request.getParameter("fid");
		 
		 List<VehicleWorkCostVo> vehicleWorkCostList=vehicleWorkCostVoService.getVehicleWorkCostListByApplyId(new BigDecimal(fid));
		 
		//直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
		 String result = JSON.toJSONString(vehicleWorkCostList);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);  
	 }
	 
	 /**
	  *车辆维修工时费新增
	  * @param request
	  * @return
	  */
	 @RequestMapping("doInsertVehicleWorkCost")
	 @ResponseBody
	 public  void doInsertVehicleWorkCost(VehicleWorkCostVo vehicleWorkCostVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 
		 try {
			 
			 vehicleWorkCostVoService.doVehicleWorkCostAdd(vehicleWorkCostVo);
			 
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/cost/vehiclepartcost/doInsertVehiclePartCost", "车辆维修", "维修工时费新增", "");
			 map.put("msg", "成功");
			 total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 
		 //直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);  
	 }
}