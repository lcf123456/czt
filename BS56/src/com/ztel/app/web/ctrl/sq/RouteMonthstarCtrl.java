package com.ztel.app.web.ctrl.sq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.sq.RouteMonthstarVo;
import com.ztel.app.vo.sq.StarinfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.util.FileUtil;
import com.ztel.framework.util.StringUtils;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

	@Controller
	@RequestMapping("/sq/routemonthstar")
	public class RouteMonthstarCtrl extends BaseCtrl {
		

		private static Logger logger = LogManager.getLogger(RouteMonthstarCtrl.class);
		
		@Autowired
		private RouteMonthstarService routemonthstarService = null;
	
		@Autowired
		private OperationlogService operationlogService = null;
		@RequestMapping("/toRouteMonthstars")
		public String index(HttpServletRequest request) {
			
			return "/sq/v_routemonthstar";
		}
		//车组月星级汇总
		@RequestMapping("/toRouteMonthstarsum")
		public String index1(HttpServletRequest request) {
			
			return "/sq/v_routemonthstarsum";
		}
		//车组星级统计
		@RequestMapping("/toRoutestarbymonth")
		public String index2(HttpServletRequest request) {
			
			return "/sq/v_routestarbymonth";
		}
		
		
		
		/**
		  * 获取角色列表
		  * @return
		  * @throws Exception
		  */
		 @RequestMapping("getRouteMonthstars")
		 @ResponseBody
		 public  Map<String, Object> getRouteMonthstarList(String driverid,/*DataGridModel dgm,*/RouteMonthstarVo routemonthstar,HttpServletRequest request) throws Exception {
			 Pagination<?> page = this.getPagination(request);

			 Map<String, Object> result=new HashMap<String, Object>();  
				
			 if (routemonthstar!=null) {
				
				 
				 //取出得分时间
				 String time=routemonthstar.getAssesstime();
				 //判断得分时间是否为空，为空时默认给上个月月份
				 if(time==null||"".equals(time)){
					 //创建时钟类
					 Calendar c = Calendar.getInstance();
					 c.add(Calendar.MONTH, -1);
					 SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
					 time = format.format(c.getTime());
					 routemonthstar.setAssesstime(time);
				 }
				 
				 //System.out.println(routemonthstar.getAssesstime());
				 //System.out.println(routemonthstar.getDrivername());
				 System.out.println(routemonthstar.getCtype()+"--");
				 page.setParam(routemonthstar);
			}
			 List<RouteMonthstarVo> ones = routemonthstarService.searchRouteMonthstarList(page);
			 
			 int totals=0;

			 result.put("rows",ones);  
			 result.put("total",page.getTotalCount());  

			 return result;
		 }
		
		 @RequestMapping("getRouteMonthstarsum")
		 @ResponseBody
		 public  Map<String, Object> getRouteMonthstarsum(String cstarid,/*DataGridModel dgm,*/RouteMonthstarVo routemonthstar,HttpServletRequest request) throws Exception {
			 Pagination<?> page = this.getPagination(request);
			 Map<String, Object> result=new HashMap<String, Object>();  
			 if (routemonthstar!=null) {
				 String time=routemonthstar.getAssesstime();
				 //判断得分时间是否为空，为空时默认给上个月月份
				 if(time==null||"".equals(time)){
					 //创建时钟类
					 Calendar c = Calendar.getInstance();
					 c.add(Calendar.MONTH, -1);
					 SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
					 time = format.format(c.getTime());
					 routemonthstar.setAssesstime(time);
				 }
				 page.setParam(routemonthstar);
			
			}
			 List<RouteMonthstarVo> paras = routemonthstarService.getRouteMonthstarsumPageList(page);
		 int totals=0;

		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
		 }
	
		 @RequestMapping("getRoutestarbymonth")
		 @ResponseBody
		 public  Map<String, Object> getRoutestarbymonth(String cstarid,/*DataGridModel dgm,*/RouteMonthstarVo routemonthstar,HttpServletRequest request) throws Exception {
			 Pagination<?> page = this.getPagination(request);
			 Map<String, Object> result=new HashMap<String, Object>();  
			 if (routemonthstar!=null) {
				 String time=routemonthstar.getAssesstime();
				 //判断得分时间是否为空，为空时默认给上个月月份
				 if(time==null||"".equals(time)){
					 //创建时钟类
					 Calendar c = Calendar.getInstance();
					 c.add(Calendar.MONTH, -1);
					 SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
					 time = format.format(c.getTime());
					 routemonthstar.setAssesstime(time);
				 }
				 page.setParam(routemonthstar);
			
			}
			 List<RouteMonthstarVo> paras = routemonthstarService.getRoutestarbymonthList(page);
		 int totals=0;

		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
		 }
		 /**
		  * 星级信息查看导出
		  * @param request
		  * @return
		  */
		 @RequestMapping("getRoutestarbymonthExcel")
		 @ResponseBody
		 public  void getRoutestarbymonthExcel(String cstarid,/*DataGridModel dgm,*/RouteMonthstarVo routemonthstar,HttpServletRequest request,HttpServletResponse response) throws Exception {
			 Pagination<?> page = this.getPagination(request);
			 page.setNumPerPage(1000000);
			 Map<String, Object> result=new HashMap<String, Object>();  
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/sq/routemonthstar/getRoutestarbymonthExcel", "星级信息查看", "导出", "");
			 if (routemonthstar!=null) {
				 String time=routemonthstar.getAssesstime();
				 //判断得分时间是否为空，为空时默认给上个月月份
				 if(time==null||"".equals(time)){
					 //创建时钟类
					 Calendar c = Calendar.getInstance();
					 c.add(Calendar.MONTH, -1);
					 SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
					 time = format.format(c.getTime());
					 routemonthstar.setAssesstime(time);
				 }
				 page.setParam(routemonthstar);
			
			}
			 ArrayList<RouteMonthstarVo> monthstarList = ( ArrayList<RouteMonthstarVo>)routemonthstarService.getRoutestarbymonthList(page);
		 List<String> needPrintFields=new ArrayList<String>();
			needPrintFields.add("routecode");
			needPrintFields.add("drivername");
			needPrintFields.add("dstarname");
			needPrintFields.add("dtotalscore");
			needPrintFields.add("cashiername");
			needPrintFields.add("cstarname");
			needPrintFields.add("ctotalscore");
			needPrintFields.add("assesstime");
			List<String> ColumnTitle=new ArrayList<String>();
			ColumnTitle.add("所属车组");
			ColumnTitle.add("驾驶员姓名");
			ColumnTitle.add("驾驶员星级");
			ColumnTitle.add("驾驶员实际得分");
			ColumnTitle.add("收款员姓名");
			ColumnTitle.add("收款员星级");
			ColumnTitle.add("收款员实际得分");
			ColumnTitle.add("评定时间");
			FileUtil.ExportToExcel(monthstarList,routemonthstar.getAssesstime()+"月车组星级",routemonthstar.getAssesstime()+"月车组星级统计", needPrintFields, ColumnTitle,  response);
			}
	}
	
	

