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
import com.ztel.app.service.cost.EquipmentService;
import com.ztel.app.vo.cost.EquipmentRepairVo;
import com.ztel.app.vo.cost.EquipmentVo;
import com.ztel.app.vo.sys.BlockcustomerVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.CustomerVo;
import com.ztel.app.vo.wms.ItemVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author yy
 * @since 2017年9月28日
 *设备管理
 */
@Controller
@RequestMapping("/cost/equipment")
public class EquipmentCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(EquipmentCtrl.class);
	
	@Autowired
	private EquipmentService equipmentService = null;
	
	@RequestMapping("toEquipment")
	public String index(HttpServletRequest request) {
		
		return "/cost/v_equipment";
	}
	
	
	@RequestMapping(value="getEquipmentPageList")
	 @ResponseBody
	 public Map<String, Object> getEquipmentPageList(EquipmentVo equipmentVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		Pagination<?> page = this.getPagination(request);
		if (equipmentVo!=null) {
			 page.setParam(equipmentVo);
		}
		List<EquipmentVo> equipmentVoList = new ArrayList<EquipmentVo>();
		equipmentVoList = equipmentService.selectEquipmentPageList(page);
		
		 result.put("rows",equipmentVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	 /**
	  * 修改设备管理信息
	  * @return
	  */
	 @RequestMapping(value="doupdateEquipment",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> doupdateEquipment(EquipmentVo equipmentVo,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
       
		 try {
			 equipmentService.updateEquipment(equipmentVo);
			 map.put("msg", "成功");
			 total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		// String result = JSON.toJSONString(map);
		 //response.setContentType("text/html;charset=UTF-8");
		 //response.getWriter().write(result);
		 return map;
	 }
	 /**
	  * 删除设备管理信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doEquipmentDel",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> deleteEquipment(@RequestParam("id") List<Integer> ids) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
		
			 equipmentService.delEquipment(ids);
		
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
	  * 新增设备管理信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doEquipmentNew",method=RequestMethod.POST)
	// @ResponseBody
	 public   void EquipmentNew(EquipmentVo equipmentVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=1;
        
		 try { equipmentVo.setStatus("10");
		 equipmentVo.setDelstatus("10");
			 equipmentService.insertEquipment(equipmentVo);
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
	  * 获取品牌名称下拉列表
	  * @return
	  * @throws Exception
	 	 
	 @RequestMapping("getEquipmentCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getEquipmentCombobox(HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		boxList=equipmentService.getEquipmentCombobox();
		 return boxList;
	 }
*/
	 
	 @RequestMapping(value = "getEquipname")
	 @ResponseBody
	 public List<EquipmentVo> getEquipname(HttpServletRequest request)
	 {
		 List<EquipmentVo>  custList = null;
		 
		 //String routecode = request.getParameter("routecode");
		// if(routecode==null)routecode="";
		 String condname = request.getParameter("condname");
		 if(condname==null)condname="";
		 
		 EquipmentVo equipmentVo = new EquipmentVo();
		 //customerVo.setRoutecode(routecode);
		 equipmentVo.setParam(condname);
		 custList = equipmentService.getListEquipname(equipmentVo);
		 return custList;
	 }
	
}
	
    
