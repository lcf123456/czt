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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.sq.AssessweightService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.sq.AssessweightVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author SN
 * @since 2017年6月14日
 * 星级参数表
 */
@Controller
@RequestMapping("/sq/assessweight")
public class AssessweightCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(AssessweightCtrl.class);
	
	@Autowired
	private AssessweightService assessweightService = null;
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toAssessweight")
	public String index(HttpServletRequest request) {
		
		return "/sq/v_assessweight";
	}
	/**
	  * 获取参数信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getAssessweight")
	 @ResponseBody
	 public  Map<String, Object> getAssessweightList(AssessweightVo assessweightVo,HttpServletRequest request) throws Exception {
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
		
		 if (assessweightVo!=null) {
			// System.out.println("assessweightVo="+assessweightVo.getMaxscore()); 
			 page.setParam(assessweightVo);
		}
		 List<AssessweightVo> paras = assessweightService.getAssessweightList(page);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 	
	 /**
	  * 删除权重得分记录,并新增权重得分记录
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="dodeleteAssessweight",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> deleteAssessweight(AssessweightVo assessweightVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		// System.out.println(assessweightVo.getId()+"-----");
		 try {
			 assessweightService.deleteAssessweightById(assessweightVo.getId(),assessweightVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/sq/assessweight/dodeleteAssessweight", "考核权重设置", "修改", "");
			 map.put("msg", "成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 //直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式

		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		// response.getWriter().write(result);
		return map;  
	 }
    
}