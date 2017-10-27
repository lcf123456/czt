package com.ztel.app.web.ctrl.sq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ztel.app.service.sq.RouteMonthstarService;
import com.ztel.app.service.sq.RouteScoresummaryService;
import com.ztel.app.vo.sq.RouteMonthstarVo;
import com.ztel.app.vo.sq.RouteScoresummaryVo;
import com.ztel.app.vo.sq.StarinfoVo;
import com.ztel.framework.util.StringUtils;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

	@Controller
	@RequestMapping("/sq/routescoresummary")
	public class RouteScoresummaryCtrl extends BaseCtrl {
		

		private static Logger logger = LogManager.getLogger(RouteScoresummaryCtrl.class);
		
		@Autowired
		private RouteScoresummaryService routescoresummaryService = null;
	
		
		@RequestMapping("/toRouteScoresummary")
		public String index(HttpServletRequest request) {
			
			return "/sq/v_routescoresummary";
		}
		
		/**
		  * 获取角色列表
		  * @return
		  * @throws Exception
		  */
		 @RequestMapping("getRouteScoresummarys")
		 @ResponseBody
		 public  Map<String, Object> getRouteScoresummaryList(String driverid,/*DataGridModel dgm,*/RouteScoresummaryVo routescoresummary,HttpServletRequest request) throws Exception {
			 Pagination<?> page = this.getPagination(request);

			 Map<String, Object> result=new HashMap<String, Object>();  
				
			 if (routescoresummary!=null) {
				 
				 
				 //取出得分时间
				 String time=routescoresummary.getScoringtime();
				 //判断得分时间是否为空，为空时默认给上个月月份
				 if(time==null||"".equals(time)){
					 //创建时钟类
					 Calendar c = Calendar.getInstance();
					 c.add(Calendar.MONTH, -1);
					 SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
					 time = format.format(c.getTime());
					 routescoresummary.setScoringtime(time);
				 }
				 
				 //System.out.println(routemonthstar.getAssesstime());
				 //System.out.println(routemonthstar.getDrivername());
				 System.out.println(routescoresummary.getCscore()+"--");
				 page.setParam(routescoresummary);
			}
			 List<RouteScoresummaryVo> ones = routescoresummaryService.searchRouteScoresummaryList(page);
			 
			 int totals=0;

			 result.put("rows",ones);  
			 result.put("total",page.getTotalCount());  

			 return result;
		 }
	
	/**
	  * 获取未考核车组列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getRouteScoresummary")
	 @ResponseBody
	 public  Map<String, Object> getRouteScoresummarysList(String driverid,/*DataGridModel dgm,*/RouteScoresummaryVo routescoresummary,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
			
		 if (routescoresummary!=null) {
			 
			 
			 //取出得分时间
			 String time=routescoresummary.getScoringtime();
			 //判断得分时间是否为空，为空时默认给上个月月份
			 if(time==null||"".equals(time)){
				 //创建时钟类
				 Calendar c = Calendar.getInstance();
				 c.add(Calendar.MONTH, -1);
				 SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
				 time = format.format(c.getTime());
				 routescoresummary.setScoringtime(time);
			 }
			 
			 //System.out.println(routemonthstar.getAssesstime());
			 //System.out.println(routemonthstar.getDrivername());
			 System.out.println(routescoresummary.getCscore()+"--");
			 page.setParam(routescoresummary);
		}
		 List<RouteScoresummaryVo> ones = routescoresummaryService.searchRouteScoresummaryPageList(page);
		 
		 int totals=0;

		 result.put("rows",ones);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }}
		 

