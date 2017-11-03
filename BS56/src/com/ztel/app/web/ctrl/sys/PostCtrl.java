/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.sys;

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
import com.ztel.app.service.sys.DeptVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.sys.PostinfoService;
import com.ztel.app.vo.sys.PostInfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author NJ
 * @since 2017年4月25日
 */
@Controller
@RequestMapping("/sys/post")
public class PostCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(PostCtrl.class);
	
	@Autowired
	private PostinfoService postinfoService = null;
	@Autowired
	private DeptVoService deptVoService = null;
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toPostinfo")
	public String index(HttpServletRequest request) {
		return "/sys/v_post";
	}
	
	 /**
	  * 获取岗位列表
	  * @return
	  */
	 @RequestMapping(value="getPostTreeList")
	 @ResponseBody
	 public  List<PostInfoVo> getPostTreeList()
	 {
		 List<PostInfoVo> postList=postinfoService.getPostinfoTreeList();
		 return postList;
	 }
	 /**
	  * 岗位管理信息新增
	  * @return
	  */
	 @RequestMapping(value="doPostNew")
	 @ResponseBody
	 public   void doPostinfoNew(PostInfoVo postInfoVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 postinfoService.insertPostInfo(postInfoVo);
			 UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(sessionUserVo, "/sys/post/doPostNew", "岗位管理", "新增", "");
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
	  * 修改岗位管理信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doPostinfoUpdate",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void UpdatePostinfo(PostInfoVo postInfoVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 postinfoService.updatePostInfo(postInfoVo);
			 UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(sessionUserVo, "/sys/post/doPostinfoUpdate", "岗位管理", "修改", "");
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
	  * 删除岗位管理信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doPostDel",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> doPostDel(@RequestParam("id") List<Integer> ids,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
			 postinfoService.delPostInfo(ids);
			 UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(sessionUserVo, "/sys/post/doPostDel", "岗位管理", "删除", "");
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
	  * getPostList
	  * @return
	  */
	 @RequestMapping(value="getPostList")
	 @ResponseBody
	 public  List<PostInfoVo> getPostList()
	 {
		 List<PostInfoVo> postList=postinfoService.getPostinfoList();
		 return postList;
	 }
	 
	 @RequestMapping(value="getDeptTreeList")
	 @ResponseBody
	 public List<HashMap<String, String>> getAllDeptTree(){
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 boxList=deptVoService.getDeptCombobox();
		 return boxList;
	 }
}
