/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.cost;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.cost.EquipmentRepairService;
import com.ztel.app.service.cost.EquipmentService;
import com.ztel.app.service.sq.CigfactoryService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.cost.EquipmentRepairVo;
import com.ztel.app.vo.sys.BlockcustomerVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author yy
 * @since 2017年9月28日
 *设备维修
 */
@Controller
@RequestMapping("/cost/equipmentrepair")
public class EquipmentRepairCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(EquipmentRepairCtrl.class);
	
	@Autowired
	private EquipmentRepairService equipmentrepairService = null;
	@Autowired
	private EquipmentService equipmentService = null;
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toEquipmentRepair")
	public String index(HttpServletRequest request) {
		
		return "/cost/v_equipmentrepair";
	}
	
	
	@RequestMapping(value="getEquipmentRepairPageList")
	 @ResponseBody
	 public Map<String, Object> getEquipmentRepairPageList(EquipmentRepairVo equipmentrepairVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		Pagination<?> page = this.getPagination(request);
		if (equipmentrepairVo!=null) {
			 page.setParam(equipmentrepairVo);
		}
		List<EquipmentRepairVo> equipmentrepairVoList = new ArrayList<EquipmentRepairVo>();
		equipmentrepairVoList = equipmentrepairService.selectEquipmentRepairPageList(page);
		
		 result.put("rows",equipmentrepairVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	/**
	  * 删除设备维修信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doEquipmentRepairDel",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> deleteEquipmentRepair(@RequestParam("id") List<Integer> ids,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=1;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/cost/equipmentrepair/doEquipmentRepairDel", "设备维修", "删除", "");
			 equipmentrepairService.delEquipmentRepair(ids);
		
			 map.put("msg", "成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 
		 return map;
	 }
   
	 /**
	  * 新增设备维修信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doEquipmentRepairNew",method=RequestMethod.POST)
	// @ResponseBody
	 public   void EquipmentRepairNew(EquipmentRepairVo equipmentrepairVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=1;
       
		 try {   equipmentrepairVo.setDelstatus("10");//状态
			 equipmentrepairService.insertEquipmentRepair(equipmentrepairVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/cost/equipmentrepair/doEquipmentRepairNew", "设备维修", "新增", "");
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
	 
	 /**
	  * 修改设备维修信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doEquipmentRepairUpdate",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void EquipmentRepairUpdate(EquipmentRepairVo equipmentrepairVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
       
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/cost/equipmentrepair/doEquipmentRepairUpdate", "设备维修", "修改", "");
			 equipmentrepairService.updateEquipmentRepair(equipmentrepairVo);
			 map.put("msg", "成功");
			 total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);
		 //return map;
	 }
	
	
}
	
    
