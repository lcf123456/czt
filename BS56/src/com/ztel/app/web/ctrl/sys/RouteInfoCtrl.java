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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.sys.RouteInfoService;
import com.ztel.app.vo.sys.RouteInfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

@Controller
@RequestMapping("/sys/route")
public class RouteInfoCtrl extends BaseCtrl{
	
	private static Logger logger = LogManager.getLogger(RouteInfoCtrl.class);
	
	@Autowired
	private RouteInfoService routeInfoService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toRouteInfo")
	public String index(HttpServletRequest request) {
		return "/sys/v_route";
	}
	
	/**
	  * 获取车组信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getRoutesList")
	 @ResponseBody
	 public  Map<String, Object> getRoutesList(RouteInfoVo routeInfoVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		 //System.out.println("------");
		 if (routeInfoVo!=null) {
			 //System.out.println("roleinfo="+roleinfo.getRolename()+","+roleinfo.getId()); 
			 page.setParam(routeInfoVo);
		}
		 List<RouteInfoVo> paras = routeInfoService.getRouteInfoPageList(page);
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	
	 /**
	  * 新增车组信息
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doRouteNew",method=RequestMethod.POST)
	// @ResponseBody
	 public  void doRouteNew(RouteInfoVo routeInfoVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 try {
			 routeInfoService.insertNewRoute(routeInfoVo);
			 UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(sessionUserVo, "/sys/route/doRouteNew", "车组信息", "新增", "");
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
	  * 修改车组信息
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doUpdateRoute",method=RequestMethod.POST)
	 // @ResponseBody
	 public   void doUpdateRoute(RouteInfoVo routeInfoVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 
		 try {
			 routeInfoService.updateRoute(routeInfoVo);
			 UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(sessionUserVo, "/sys/route/doUpdateRoute", "车组信息", "修改", "");
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
	  * 下拉框
	  * @return
	  */
	 @RequestMapping(value="getRouteinfoCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getRouteinfoCombobox() 
	 {
		 List<HashMap<String, String>> boxList=new ArrayList<>();

			 boxList =  routeInfoService.getRoutesDeptListCombobox();
		 return boxList;
	 }
	 
	 /**
	  * 核算人员下拉框
	  * @return
	  */
	 @RequestMapping(value="getRouteCalCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getRouteCalCombobox() 
	 {
		 List<HashMap<String, String>> boxList=new ArrayList<>();

			 boxList =  routeInfoService.getRoutesCalPersonListCombobox();
		 return boxList;
	 }
}
