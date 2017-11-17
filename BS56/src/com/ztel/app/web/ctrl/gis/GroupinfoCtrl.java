/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.gis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import com.fsj.spring.web.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.sys.UserVoService;
import com.ztel.app.service.gis.GroupinfoVoService;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.gis.GroupinfoVo;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author SN
 * @since 2017年9月4日
 * 部门参数表
 */
@Controller
@RequestMapping("/gis/groupinfo")
public class GroupinfoCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(GroupinfoCtrl.class);
	
	//用于初始化数据的时候，进行数据类型转换，String类型转为Date类型
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@Autowired
	private GroupinfoVoService groupinfoVoService = null;
	
	@Autowired
	private UserVoService userVoService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toGroupinfo")
	public String index(HttpServletRequest request) {
		
		return "/gis/v_groupinfo";
	}

	
	/**
	  * 获取装卸组信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="getGroupinfoVoList")
	 @ResponseBody
	 public  List<GroupinfoVo> GroupinfoVo(HttpServletRequest request,GroupinfoVo groupinfoVo)
	 {
		 //String groupinfo=request.getParameter("gropucode");
		// System.out.println("groupinfono=="+groupinfono+"----------groupinfos="+groupinfos);
		// groupinfoVo.setGroupinfo(gropucode);
		 List<GroupinfoVo> groupinfoList=groupinfoVoService.getGroupinfoVoList(groupinfoVo);
		 return groupinfoList;
				 
	 }
	 
	 /**
	  * 获取装卸组用户列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="getuseGroupinfoVoList")
	 @ResponseBody
	 public  List<GroupinfoVo> GroupinfouseVo(HttpServletRequest request,GroupinfoVo groupinfoVo)
	 {
		 List<GroupinfoVo> groupinfoList=groupinfoVoService.useGroupinfoVoList(groupinfoVo);
		 return groupinfoList;
				 
	 }
	 /**
	  * 删除装卸组
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="dodelGroupinfoVo",method=RequestMethod.POST )
	 @ResponseBody
	 public   Map<String, Object> deleteGroupinfo(@RequestParam("groupcode") List<String> groupcodes,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 List<String> codelist=new ArrayList<>();
		 int total=0;
		String	 s="";
		 if (groupcodes!=null&&groupcodes.size()>0) {
			 total = groupcodes.size();
		}
		 for (int i=0;i<groupcodes.size();i++){
			 s=java.net.URLDecoder.decode(groupcodes.get(i),"UTF-8");
			 //System.out.println(s);
			 codelist.add(s);
		 }
		 
		 try {
			 groupcodes=codelist;
			 System.out.print(groupcodes);
			 groupinfoVoService.delGroupinfoVo(groupcodes);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/gis/groupinfo/dodelGroupinfoVo", "装卸组信息", "删除", "");
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
	  * 删除装卸组人员
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="dodelGroupuserVo",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> deleteGroupuser(@RequestParam("id") List<Integer> ids,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
			 groupinfoVoService.delGroupuserVo(ids);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/gis/groupinfo/dodelGroupuserVo", "装卸组人员", "删除", "");
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
	  * 新增装卸组
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doinsertGroupinfoVo",method=RequestMethod.POST)
	// @ResponseBody
	 public   void GroupinfoNew(GroupinfoVo groupinfoVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		  //String membername=groupinfoVo.getMembername();
		  //membername=java.net.URLDecoder.decode(membername,"UTF-8");
		  //groupinfoVo.setMembername(membername);
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/gis/groupinfo/doinsertGroupinfoVo", "装卸组信息", "新增", "");
			 //System.out.println(userVo);
			 //groupinfoVo.setMember(userVo.getId());//对比姓名取相应ID
			 groupinfoVoService.insertGroupinfoVo(groupinfoVo);
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
	  * 修改装卸组信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doupdateGroupinfoVo",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void updateGroupinfoVo(GroupinfoVo groupinfoVo,@RequestParam("groupcode") List<String> groupcodes,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/gis/groupinfo/doupdateGroupinfoVo", "装卸组信息", "修改", "");
			 groupinfoVo.setMember(userVo.getId());//取session中用户ID
			 groupinfoVoService.delGroupinfoVo(groupcodes);
			 groupinfoVoService.insertGroupinfoVo(groupinfoVo);
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
	  * 下拉框
	  * @return
	  */
	 @RequestMapping(value="getGroupinfoCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getGroupinfoCombobox() 
	 {
		 List<HashMap<String, String>> boxList=new ArrayList<>();

			 boxList =  groupinfoVoService.getGroupinfoCombobox();
		 return boxList;
	 }
	 
	 /**
	  * 获取装卸组信息列表(全部信息，未做group操作的)
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="getAllGroupinfoList")
	 @ResponseBody
	 public  List<GroupinfoVo> getAllGroupinfoList(HttpServletRequest request,GroupinfoVo groupinfoVo)
	 {
		 List<GroupinfoVo> groupinfoList=groupinfoVoService.selectAllGroupinfoList(groupinfoVo);
		 return groupinfoList;
	 }
	 
}
	