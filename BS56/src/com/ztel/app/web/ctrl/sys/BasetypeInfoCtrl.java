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
import com.ztel.app.service.sys.BaseTypeInfoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.sys.BasetypeInfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author NJ
 * @since 2017年4月25日
 */
@Controller
@RequestMapping("/sys/basetypeInfo")
public class BasetypeInfoCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(BasetypeInfoCtrl.class);
	
	@Autowired
	private BaseTypeInfoService baseTypeInfoService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toBasetypeInfo")
	public String index(HttpServletRequest request) {
		return "/sys/v_basetypeinfo";
	}
	
	/**
	  * 根据类型获取basetypeinfo信息列表
	  * @return
	  */
	 @RequestMapping(value="getBasetypeInfoByTypecode")
	 @ResponseBody
	public List<BasetypeInfoVo> getBasetypeInfoByTypecode(@RequestParam("typecode") String typecode){
		return baseTypeInfoService.getBaseTypeinfoList(typecode);
	}
	
	 /**
	  * 新增basetypeinfo信息
	  * @return
	  */
	 @RequestMapping(value="doBasetypeInfoNew")
	 @ResponseBody
	 public   void doBasetypeInfoNew(BasetypeInfoVo basetypeInfoVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 baseTypeInfoService.doBasetypeInfoNew(basetypeInfoVo);
			 UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(sessionUserVo, "/sys/basetypeInfo/doBasetypeInfoNew", "基础类别管理", "新增", "");
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
	  * 修改basetypeinfo信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doBasetypeinfoUpdate",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void doBasetypeinfoUpdate(BasetypeInfoVo basetypeInfoVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 baseTypeInfoService.doBasetypeInfoUpdate(basetypeInfoVo);
			 UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(sessionUserVo, "/sys/basetypeInfo/doBasetypeinfoUpdate", "基础类别管理", "修改", "");
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
	 
	 /**
	  * 删除basetypeinfo信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doBasetypeinfoDel",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> doBasetypeinfoDel(@RequestParam("id") List<Integer> ids,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
			 baseTypeInfoService.doBasetypeInfoDel(ids);
			 UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(sessionUserVo, "/sys/basetypeInfo/doBasetypeinfoDel", "基础类别管理", "删除", "");
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
	  * 取基础信息类别下拉列表
	  * @return
	  */
	 @RequestMapping(value="getBasetypesList")
	 @ResponseBody
	 public  List<BasetypeInfoVo> getBasetypesList()
	 {
		 List<BasetypeInfoVo> basetypeInfoList=baseTypeInfoService.getTypeList();
		 return basetypeInfoList;
	 }
	 
}
