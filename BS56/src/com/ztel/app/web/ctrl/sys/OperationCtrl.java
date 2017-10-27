/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.sys;


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
import com.ztel.app.service.sys.MenuinfoService;
import com.ztel.app.service.sys.OperationinfoService;
import com.ztel.app.vo.sys.MenuInfoVo;
import com.ztel.app.vo.sys.RoleInfoVo;
import com.ztel.app.vo.sys.OperationinfoVo;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author Zeal
 * @since 2017年3月16日
 * 菜单控制
 */
@Controller
@RequestMapping("/sys/operation")
public class OperationCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(OperationCtrl.class);
	
	@Autowired
	private OperationinfoService operationinfoService = null;
	
	@Autowired
	private MenuinfoService menuinfoService = null;
	
	@RequestMapping("toOperation")
	public String toOperation(HttpServletRequest request) {
		return "/sys/v_operation";
	}
	
	@RequestMapping("getOperationinfos")
	 @ResponseBody
	 public  Map<String, Object> getOperationinfoList(HttpServletRequest request) throws Exception {

		String mcode = request.getParameter("mcode");
		if(mcode==null||mcode.equals(""))mcode="abc";
		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 List<OperationinfoVo> ones = operationinfoService.getOperationinfoList(mcode);
		 

		 result.put("rows",ones);  
		 result.put("total",ones.size());  

		 return result;
	 }
	
	/**
	 * 根据菜单id获取菜单信息
	 * @param request
	 * @return
	 */
	@RequestMapping("getMenuInfoVo")
	@ResponseBody
	public MenuInfoVo getMenuInfoVo(HttpServletRequest request){
		MenuInfoVo  menuInfoVo = null;
		String id = request.getParameter("mid");//菜单id
		menuInfoVo = menuinfoService.getMenuinfoVoByid(id);
		return menuInfoVo;
	}
	
	 /**
	  * 新增角色
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="operationNew",method=RequestMethod.POST)
	// @ResponseBody
	 public   void operationNew(OperationinfoVo operationinfoVo,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
       
		 System.out.println("roleid="+operationinfoVo.getId()+",roleName="+operationinfoVo.getName()+",remarks="+operationinfoVo.getMenucode());
		 try {
			 //roleinfoService.newRole(roleinfo);
			 operationinfoService.operationNew(operationinfoVo);
			 map.put("msg", "成功");
			 total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", 1);
		 
		 //直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);  

		 //return result;
	 }
	 
	 /**
	  * 新增角色
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="operationUpdate",method=RequestMethod.POST)
	// @ResponseBody
	 public   void operationUpdate(OperationinfoVo operationinfoVo,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
       
		 System.out.println("roleid="+operationinfoVo.getId()+",roleName="+operationinfoVo.getName()+",remarks="+operationinfoVo.getMenucode());
		 try {
			 operationinfoService.operationUpdate(operationinfoVo);
			 map.put("msg", "成功");
			 total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", 1);
		 
		 //直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);  
	 }
	 
	 /**
	  * 删除功能点
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="operationDel",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> operationDel(@RequestParam("id") List<Integer> id) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (id!=null&&id.size()>0) {
			 total = id.size();
		}
		 try {
			 operationinfoService.operationDel(id);
			 map.put("msg", "成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 
		 return map;
	 }
}
