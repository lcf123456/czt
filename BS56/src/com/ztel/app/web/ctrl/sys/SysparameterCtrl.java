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
//import com.fsj.spring.web.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.sys.RoleinfoService;
import com.ztel.app.service.sys.SysparameterService;
import com.ztel.app.vo.sys.RoleInfoVo;
import com.ztel.app.vo.sys.SysparameterVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author NJ
 * @since 2017年4月18日

 * 系统参数表

 */
@Controller
@RequestMapping("/sys/sysparameter")
public class SysparameterCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(SysparameterCtrl.class);
	
	@Autowired
	private SysparameterService sysparameterService = null;
	
	@RequestMapping("/toSysparainfo")
	public String index(HttpServletRequest request) {
		
		return "/sys/v_sysparameter";
	}
	@RequestMapping(value="/toSysParaNew",method=RequestMethod.GET)
	public String popWindow() throws Exception{
		return "/sys/v_sysparameter_new";
	}
	/**
	  * 获取系统参数信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getSysParas")
	 @ResponseBody
	 public  Map<String, Object> getSysParaList(SysparameterVo sysparameterVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
//		// System.out.println("重置之前numPerPage="+page.getNumPerPage()+","+page.getSortColumn()+",isSortAsc="+page.isSortAsc()); 
//		 //按照DataGrid的分页重新设置分页参数

//		 if (dgm!=null&&dgm.getSort()!=null&&dgm.getSort()!="") {
//			 page.setSortKey(dgm.getSort());//设置排序字段
//			
//			 boolean isSortAsc=false;
//			 if(dgm.getOrder()!=null&&dgm.getOrder()!=""&&dgm.getOrder().equals("asc"))isSortAsc=true;
//			 page.setSortAsc(isSortAsc);//设置是否正序排练
//			// System.out.println("dgm sort= "+dgm.getSort()+",order ="+dgm.getOrder()+",isSortAsc="+isSortAsc+",pageNum="+dgm.getPage()+",getRows="+dgm.getRows()); 
//			 page.setNumPerPage(dgm.getRows());//设置每页显示记录数

//		}

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (sysparameterVo!=null) {
			 //System.out.println("roleinfo="+roleinfo.getRolename()+","+roleinfo.getId()); 
			 page.setParam(sysparameterVo);
		}
		 List<SysparameterVo> paras = sysparameterService.getSysparameterPageList(page);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 	
	 /**
	  * 删除参数
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="delSysPara",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> deleteSysPara(@RequestParam("id") List<Integer> ids) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
			 sysparameterService.delSysparameter(ids);
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
	  * 新增系统参数
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="sysParaNew",method=RequestMethod.POST)
	// @ResponseBody
	 public   void sysParaNew(SysparameterVo sysparameterVo,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 sysparameterService.insertSysparameter(sysparameterVo);
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
	  * 修改系统参数
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="sysParaUpdate",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void SysParaUpdate(SysparameterVo sysparameterVo,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 sysparameterService.updateSysparameter(sysparameterVo);
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
