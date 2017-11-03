/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.sq;

import java.text.SimpleDateFormat;
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
//import com.fsj.spring.web.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.sq.RoutescoreVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.sys.RouteInfoService;
import com.ztel.app.service.wms.CustomerService;
import com.ztel.app.vo.sq.RoutescoreVo;
import com.ztel.app.vo.sys.RouteInfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.CustomerVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author SN
 * @since 2017年5月24日 * 星级参数表 */
@Controller
@RequestMapping("/sq/routescore")
public class RoutescoreCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(RoutescoreCtrl.class);
	
	@Autowired
	private RoutescoreVoService routescoreVoService = null;
	
	@Autowired
	private RouteInfoService routeInfoService = null;
	
	@Autowired
	private CustomerService customerService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toRoutescore")
	public String index(HttpServletRequest request) {
		
		return "/sq/v_routescore";
	}
	
	@RequestMapping("toRoutescore_market")
	public String index1(HttpServletRequest request) {
		request.setAttribute("sourceid", "3");
		return "/sq/v_routescore_market";
	}
	/**
	  * 自动语音信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getRoutescore")
	 @ResponseBody
	 public  Map<String, Object> getRoutescorePageList(RoutescoreVo routescoreVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (routescoreVo!=null) {
			 //System.out.println("routescoreVo="+routescoreVo.toString()); 
			 page.setParam(routescoreVo);
		}
		 
		 List<RoutescoreVo> paras = routescoreVoService.getRoutescorePageList(page);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 	
	 /**
	  * 自动语音/市场督察-删除
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="dodelRoutescore",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> deleteRoutescore(@RequestParam("id") List<Integer> ids,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
			 routescoreVoService.delRoutescoreline(ids);
			 routescoreVoService.delRoutescore(ids);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/sq/routescore/dodelRoutescore", "自动语音/市场督察", "删除", "");
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
	  * 自动语音-查看
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doviewRoutescore",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void viewRoutescore(RoutescoreVo routescoreVo,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 routescoreVoService.viewRoutescore(routescoreVo);
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
	 
	 @RequestMapping("getViewRoutescore")
	 @ResponseBody 
		public  Map<String, Object> getViewRoutescoreList(RoutescoreVo routescoreVo,HttpServletRequest request) throws Exception {
			 Pagination<?> page = this.getPagination(request);
			 Map<String, Object> result=new HashMap<String, Object>();  
				String id=request.getParameter("id");
			 if (routescoreVo!=null) {
				 routescoreVo.setId(Long.parseLong(id));
				 //System.out.println("routescoreVo="+routescoreVo.getId()); 
				 page.setParam(routescoreVo);
			}
			 else
			 {
				 routescoreVo=new RoutescoreVo();
				 routescoreVo.setId(Long.parseLong(id));
				 page.setParam(routescoreVo);
			 }
			 
			 List<RoutescoreVo> paras = routescoreVoService.getViewRoutescorePageList(page);
			 //System.out.println(paras.size());
			 //System.out.println(paras.get(0).getAssessweight());
			 //System.out.println(paras.get(1).getAssessweight());
			 //System.out.println(paras.get(2).getAssessweight());
			 //System.out.println(paras.get(3).getAssessweight());
			 result.put("rows",paras);  
			 result.put("total",page.getTotalCount());  
		
			 return result;
		 }
	 
	 /**
	  * 市场督察信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getRoutescore_market")
	 @ResponseBody
	 public  Map<String, Object> getRoutescoreMarketPageList(RoutescoreVo routescoreVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 
		 Map<String, Object> result=new HashMap<String, Object>();  
		 if (routescoreVo!=null) {
			 page.setParam(routescoreVo);
		 }
		 List<RoutescoreVo> paras = routescoreVoService.getRoutescoreMarketPageList(page);
		 //System.out.println(paras.size());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  
		 
		 return result;
	 }
	 
	 /**
	  * 根据条件查询零售户列表
	  * @param customerVo
	  * @param request
	  * @return
	  */
	 @RequestMapping("getCustListByCondition")
	 @ResponseBody
	 public List<CustomerVo> getCustListByCondition(CustomerVo customerVo,HttpServletRequest request)
	 {
		 List<CustomerVo>  custList = null;
		 //System.out.println(customerVo.getParam()+"---");
		 custList = customerService.getListByCond(customerVo);
		 return custList;
	 }
	 
	 /**
	  * 根据车组取车组人员
	  * @param request
	  * @return
	  */
	 @RequestMapping("getDriverAndCashierByRouteCode")
	 @ResponseBody
	 public RouteInfoVo getDriverAndCashierByRouteCode(HttpServletRequest request)
	 {
		 RouteInfoVo routeInfoVo=new RouteInfoVo();
		 
		 String routecode = request.getParameter("routecode");
		 if(routecode==null)routecode="";
		 routeInfoVo = routeInfoService.getDriverAndCashierByRotecode(routecode);
		 return routeInfoVo;
	 }
	 
	 /**
	  * 新增市场督查信息
	  * @param request
	  * @return
	  */
	 @RequestMapping("doRoutescoreMarketNew")
	 @ResponseBody
	 public   void doRoutescoreMarketNew(RoutescoreVo routescoreVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/sq/routescore/doRoutescoreMarketNew", "市场督查", "新增", "");
		//主表id
		 long id=routescoreVoService.getRouteScoreId();
		 routescoreVo.setId(id);
		 //记录人id
		 routescoreVo.setCreateid(userVo.getId());
		 //公司得分
		 String che4=request.getParameter("che4");
		 if(che4==null||"".equals(che4))che4="0";
		 double companyscore=Integer.parseInt(che4);
		 routescoreVo.setCompanycore(companyscore);
		 //得分时间
		 String scoretime=request.getParameter("scoringtime1");
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 routescoreVo.setScoringtime(sdf.parse(scoretime));
		//插入主表数据
		 routescoreVoService.insertSummarySelective(routescoreVo);
		 
		 //组合从表数据
		 String evalitemid="",assessweight="",che="";
		 List<RoutescoreVo> lst=new ArrayList<>();
		 for(int i=1;i<=6;i++){
			  RoutescoreVo vo=new RoutescoreVo();
			  
			  evalitemid=request.getParameter("evalitemid"+i);
			  assessweight=request.getParameter("assessweight"+i);
			  if(assessweight==null||"".equals(assessweight))assessweight="0";
			  che=request.getParameter("che"+i);
			  if(che==null||"".equals(che))che="0";
			  //System.out.println(evalitemid+"=="+assessweight+"=="+che);
			  vo.setFid(Integer.parseInt(id+""));
			  vo.setEvalitemid(Integer.parseInt(evalitemid));
			  vo.setAssessweight(Double.parseDouble(assessweight));
			  vo.setActualscore(Double.parseDouble(che));
			  
			  routescoreVoService.insertLineSelective(vo);
		 }
		 operationlogService.insertLog(userVo, "/sq/routescore/doRoutescoreMarketNew", "市场督查", "新增", "");
		 
//		 String evalitemid2=request.getParameter("evalitemid2");
//		 String assessweight2=request.getParameter("assessweight2");
//		 String che2=request.getParameter("che2");
//		 String evalitemid3=request.getParameter("evalitemid3");
//		 String assessweight3=request.getParameter("assessweight3");
//		 String che3=request.getParameter("che3");
//		 String evalitemid4=request.getParameter("evalitemid4");
//		 String assessweight4=request.getParameter("assessweight4");
//		 
//		 String evalitemid5=request.getParameter("evalitemid5");
//		 String assessweight5=request.getParameter("assessweight5");
//		 String che5=request.getParameter("che5");
//		 String evalitemid6=request.getParameter("evalitemid6");
//		 String assessweight6=request.getParameter("assessweight6");
//		 String che6=request.getParameter("che6");
//		 System.out.println(request.getParameter("evalitemid1")+"---");
//		 System.out.println(request.getParameter("che1")+"-==--");
		 
		 try {
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