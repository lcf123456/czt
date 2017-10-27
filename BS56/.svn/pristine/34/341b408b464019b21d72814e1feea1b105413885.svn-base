/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.sys;

import java.io.UnsupportedEncodingException;
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
//import com.fsj.spring.web.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.sys.DeptVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.sys.DeptTreeVo;
import com.ztel.app.vo.sys.DeptVo;
import com.ztel.app.vo.sys.PostInfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author SN
 * @since 2017年5月2日
 * 部门参数表
 */
@Controller
@RequestMapping("/sys/dept")
public class DeptCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(DeptCtrl.class);
	
	@Autowired
	private DeptVoService deptVoService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toDept")
	public String index(HttpServletRequest request) {
		
		return "/sys/v_dept";
	}

	/**
	  * 获取部门参数信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="getDeptinfoList")
	 @ResponseBody
	 public  List<DeptVo> DeptVo(HttpServletRequest request,DeptVo vo)
	 {
		 //取传来的keywd参数,并将keywd转换编码为UTF8

		 String keywd;
		try {
			keywd = new String(request.getParameter("keywd").getBytes("ISO-8859-1"), "UTF-8");
			if(keywd==null)keywd="";
			 //System.out.println(keywd+"---------------------");
			 List<DeptVo> deptList=deptVoService.getDeptinfoList(vo,keywd);
			 return deptList;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}  
		 
	 }
	 
	 @RequestMapping(value="getDeptTreeList")
	 @ResponseBody
	 public List<HashMap<String, String>> getAllDeptTree(){
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 boxList=deptVoService.getDeptCombobox();
		 return boxList;
	 }
 	
	 /**
	  * 删除参数
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="dodelDeptVo",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> deleteDept(@RequestParam("id") List<Integer> ids,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
			 deptVoService.delDeptVo(ids);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/sys/dept/dodelDeptVo", "部门管理", "删除", "");
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
	  * 新增部门参数
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doinsertDeptVo",method=RequestMethod.POST)
	// @ResponseBody
	 public   void DeptNew(DeptVo deptVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 deptVo.setCreateid(userVo.getId());//取session中用户ID
			 deptVoService.insertDeptVo(deptVo);
			 operationlogService.insertLog(userVo, "/sys/dept/doinsertDeptVo", "部门管理", "新增", "");
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
	  * 修改部门参数
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doupdateDeptVo",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void updateDeptVo(DeptVo deptVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 deptVo.setCreateid(userVo.getId());//取session中用户ID
			 deptVoService.updateDeptVo(deptVo);
			 operationlogService.insertLog(userVo, "/sys/dept/doupdateDeptVo", "部门管理", "修改", "");
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
	