/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.perform;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.ztel.app.service.perform.UserlevelService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.cost.SPLTypeInfoVo;
import com.ztel.app.vo.perform.UserlevelVo;
import com.ztel.app.vo.perform.WorkcontentVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author lcf
 * @since 2017年2月26日
 * 重点工作
 */
@Controller
@RequestMapping("/perform/userlevel")
public class UserlevelCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(UserlevelCtrl.class);
	
	@Autowired
	private UserlevelService userlevelService = null;

	@Autowired
	private OperationlogService operationlogService = null;
	/**
	 * 考核授权
	 * @param request
	 * @return
	 */
	@RequestMapping("touserlevel")
	public String tokeywork(HttpServletRequest request) {
		return "/perform/v_userlevel";
	}
	
	
	
	 @RequestMapping(value="getUserlevelList")
	 @ResponseBody
	 public  List<UserlevelVo> getUserlevelList(HttpServletRequest request)
	 {
		 String id = request.getParameter("id");
		 Long userid = 0L;
		 if(id==null||id.equals("")){
			 userid=0L;
		 }
		 else {
			 userid = Long.parseLong(id);
		 }
		 List<UserlevelVo> userlevelList=userlevelService.getUserlevelList(userid);
		 return userlevelList;
	 }
	
	 /**
	  * 新增
	  * @return
	  */
	 @RequestMapping(value="doAddUserlevel")
	 @ResponseBody
	 public  Map<String, Object> doAddUserlevel(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String userstr= request.getParameter("userstr");
		 String userid= request.getParameter("userid");
		 String userlevel= request.getParameter("userlevel");
		 //System.out.println("--------------"+sPLTypeInfoVo.toString());
		 try{
			 userlevelService.doAddUserlevel(userstr,userid,userlevel);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/perform/userlevel/doAddUserlevel", "考核授权", "授权", "");

			 map.put("msg", "新增成功");
		 }
		 catch(Exception e){
			 map.put("msg", "新增失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 修改
	  * @return
	  */
	 @RequestMapping(value="doEditUserlevel")
	 @ResponseBody
	 public  Map<String, Object> doEditUserlevel(UserlevelVo userlevelVo,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 //System.out.println("id----"+menuinfo.getId()+",name="+menuinfo.getMenuname());
		// List<Menuinfo> menuList=menuinfoService.searchMenuinfoList(id);
		 int resutl =  userlevelService.doEditUserlevel(userlevelVo);
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/perform/userlevel/doEditUserlevel", "考核授权", "修改", "");
		 if(resutl>0)
		 {
			 map.put("total", "1");
			 map.put("msg", "修改成功");
		 }
		 else{
			 map.put("total", "1");
			 map.put("msg", "修改失败");
		 }
		 
		 return map;
	 }
	 
	 /**
	  * 删除,删除用户及删除parentid为userid的用户信息
	  * @return
	  */
	 @RequestMapping(value="doDelUserlevel")
	 @ResponseBody
	 public  Map<String, Object> doDelUserlevel(Long userid,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();  
			
		// List<Menuinfo> menuList=menuinfoService.searchMenuinfoList(id);
		 if(userid==null)userid=0L;
		 int result =  userlevelService.doDelUserlevel(userid);
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/perform/userlevel/doDelUserlevel", "考核授权", "删除", "");
		 if(result==1)
		 {
			 map.put("total", "1");
			 map.put("msg", "删除成功");
		 }
		 else{
			 map.put("total", "0");
			 map.put("msg", "删除失败！");
		 }
		 return map;
	 }
}
