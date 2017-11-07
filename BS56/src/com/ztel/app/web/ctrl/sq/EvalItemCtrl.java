/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.sq;

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
import com.ztel.app.service.sq.EvalItemService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.sq.EvalitemVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author NJ
 * @since 2017年6月17日 */
@Controller
@RequestMapping("/sq/evalitem")
public class EvalItemCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(EvalItemCtrl.class);
	
	@Autowired
	private EvalItemService evalItemService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toEvalItem")
	public String index(HttpServletRequest request) {
		return "/sq/v_autoitem";
	}
	
	/**
	  * 自动语音考核项信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getEvalItemPageList")
	 @ResponseBody
	 public  Map<String, Object> getEvalItemPageList(EvalitemVo evalitemVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (evalitemVo!=null) {
			 page.setParam(evalitemVo);
		}
		 
		 List<EvalitemVo> paras = evalItemService.getAutoItemPageList(page);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 	
	 /**
	  * 自动语音考核项删除
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doDelEvalItem",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> doDelEvalItem(@RequestParam("id") List<Integer> ids,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 //if (ids!=null&&ids.size()>0) {
		//	 total = ids.size();
		//}
		 try {
			 total=evalItemService.delEvalItem(ids);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/sq/evalitem/doDelEvalItem", "自动语音考核项", "删除", "");
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
	  * 自动语音考核项信息新增
	  * @param request
	  * @return
	  */
	 @RequestMapping("doInsertEvalItem")
	 @ResponseBody
	 public   void doInsertEvalItem(EvalitemVo evalitemVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 
		 try {
			 total=evalItemService.inertEvalItem(evalitemVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/sq/evalitem/doInsertEvalItem", "自动语音考核项", "新增", "");
			 map.put("msg", "成功");
			 //total=1;
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
	  * 修改自动语音考核项信息
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doUpdateEvalItem",method=RequestMethod.POST)
	 // @ResponseBody
	 public   void doUpdateEvalItem(EvalitemVo evalitemVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 
		 try {
			 total=evalItemService.updateEvalItem(evalitemVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/sq/evalitem/doUpdateEvalItem", "自动语音考核项", "修改", "");
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