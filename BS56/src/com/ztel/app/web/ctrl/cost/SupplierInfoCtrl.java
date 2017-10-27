/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.cost;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.ztel.app.service.cost.SupplierInfoVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.cost.SupplierInfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author SN
 * @since 2017年6月28日
 * 部门参数表
 */
@Controller
@RequestMapping("/cost/supplierinfo")
public class SupplierInfoCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(SupplierInfoCtrl.class);
	
	//用于初始化数据的时候，进行数据类型转换，String类型转为Date类型
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@Autowired
	private SupplierInfoVoService supplierInfoVoService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	
	@RequestMapping("toSupplierInfo")
	public String index(HttpServletRequest request) {
		
		return "/cost/v_supplierinfo";
	}

	
	/**
	  * 获取供应商信息列表-页面
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="getSupplierInfoVoPageList")
	 @ResponseBody
	 public  Map<String, Object> getSupplierInfoVoPageList(SupplierInfoVo supplierInfoVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (supplierInfoVo!=null) {
			 System.out.println("supplierInfoVo="+supplierInfoVo.getId()); 
			 page.setParam(supplierInfoVo);
		}
		 
		 List<SupplierInfoVo> paras = supplierInfoVoService.getSupplierInfoVoPageList(page);
		 System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 
 	
	 /**
	  * 删除供应商信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="dodelSupplierInfoVo",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> delSupplierInfoVo(@RequestParam("id") List<Integer> ids,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
			 supplierInfoVoService.delSupplierInfoVo(ids);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/cost/supplierinfo/dodelSupplierInfoVo", "供应商信息", "删除", "");
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
	  * 新增供应商
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doinsertSupplierInfoVo",method=RequestMethod.POST)
	// @ResponseBody
	 public   void insertSupplierInfoVo(SupplierInfoVo supplierInfoVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 
		 //String bdate=request.getParameter("bdate");
		 //System.out.println("buydate===--======"+bdate);
		 try {
			 //SimpleDateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
			 //if(bdate!=null&&!"".equals(bdate)){
			//	 Date date = fmt.parse(bdate);
			//	 vehicleVo.setBuydate(date);
			////	 System.out.println("buydate========="+vehicleVo.getBuydate().toString());
			 //}
			 supplierInfoVoService.insertSupplierInfoVo(supplierInfoVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/cost/supplierinfo/doinsertSupplierInfoVo", "供应商信息", "新增", "");
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
	  * 修改供应商信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doupdateSupplierInfoVo",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void updateSupplierInfoVo(SupplierInfoVo supplierInfoVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 supplierInfoVoService.updateSupplierInfoVo(supplierInfoVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/cost/supplierinfo/doupdateSupplierInfoVo", "供应商信息", "修改", "");
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
	  * 查看供应商信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doviewSupplierInfoVo",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void viewSupplierInfoVo(SupplierInfoVo supplierInfoVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 supplierInfoVoService.viewSupplierInfoVo(supplierInfoVo);
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
	