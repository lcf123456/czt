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
//import com.fsj.spring.web.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.sq.StarinfoVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.sq.StarinfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author SN
 * @since 2017年4月25日
 * 星级参数表
 */
@Controller
@RequestMapping("/sq/starinfo")
public class StarinfoCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(StarinfoCtrl.class);
	
	@Autowired
	private StarinfoVoService starinfoVoService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toStarinfo")
	public String index(HttpServletRequest request) {
		
		return "/sq/v_starinfo";
	}
	@RequestMapping(value="/toStarinfoNew",method=RequestMethod.GET)
	public String popWindow() throws Exception{
		return "/sq/v_starinfo_new";
	}
	/**
	  * 获取参数信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getStarinfo")
	 @ResponseBody
	 public  Map<String, Object> getStarinfoPageList(StarinfoVo starinfoVo,HttpServletRequest request) throws Exception {
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
		
		 if (starinfoVo!=null) {
			// System.out.println("starinfoVo="+starinfoVo.getMaxscore()); 
			 page.setParam(starinfoVo);
		}
		 List<StarinfoVo> paras = starinfoVoService.getStarinfoPageList(page);
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
	 @RequestMapping(value="delStarinfo",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> deleteStarinfo(@RequestParam("id") List<Integer> ids,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
			 starinfoVoService.delStarinfo(ids);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/sq/starinfo/delStarinfo", "星级信息", "删除", "");
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
	  * 新增星级参数
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="insertStarinfo",method=RequestMethod.POST)
	// @ResponseBody
	 public   void insertStarinfo(StarinfoVo starinfoVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 starinfoVo.setCreateid(userVo.getId());//取session中用户ID
			 starinfoVoService.insertStarinfo(starinfoVo);
			 operationlogService.insertLog(userVo, "/sq/starinfo/insertStarinfo", "星级信息", "新增", "");
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
	  * 修改星级参数
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="updateStarinfo",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void updateStarinfo(StarinfoVo starinfoVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 starinfoVo.setCreateid(userVo.getId());//取session中用户ID
			 starinfoVoService.updateStarinfo(starinfoVo);
			 operationlogService.insertLog(userVo, "/sq/starinfo/updateStarinfo", "星级信息", "修改", "");
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
