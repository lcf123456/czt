package com.ztel.app.inspur.webctrl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.inspur.service.InspurSaleService;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.web.ctrl.BaseCtrl;
@Controller
@RequestMapping("/inspur")
public class InspurCtrl extends BaseCtrl{
	 
	@Autowired
	private InspurSaleService inspurSaleService = null;
	/**
	 * 同步零售户
	 * @param request
	 * @return
	 */
	@RequestMapping("toSynccustomer")
	public String toSynccustomer(HttpServletRequest request) {
		return "/inspur/v_synccustomer";
	}
	
	@RequestMapping("doSyncCustomer")
	 @ResponseBody
	 public  Map<String, Object> doSyncCustomer(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 String keyword = request.getParameter("keyword");
		 result = inspurSaleService.doSyncCustomer(userVo);
		 
		 return result;
	 }
	
	/**
	 * 同步订单
	 * @param request
	 * @return
	 */
	@RequestMapping("toSyncorder")
	public String toSyncorder(HttpServletRequest request) {
		return "/inspur/v_syncorder";
	}
	
	@RequestMapping("doSyncOrder")
	 @ResponseBody
	 public  Map<String, Object> doSyncOrder(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 String orderdate = request.getParameter("orderdate");
		 if(orderdate==null||orderdate.equals(""))orderdate = DateUtil.getyyyy_mm_dd();
		 
		 orderdate = orderdate.replaceAll("-", "");//日期格式需要yyyymmdd
		 result = inspurSaleService.doSyncOrder(userVo, orderdate);
		 
		 return result;
	 }
	
	/**
	 * 同步商品信息
	 * @param request
	 * @return
	 */
	@RequestMapping("toSyncitem")
	public String toSyncitem(HttpServletRequest request) {
		return "/inspur/v_syncitem";
	}
	
	@RequestMapping("doSyncItem")
	 @ResponseBody
	 public  Map<String, Object> doSyncItem(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 String keyword = request.getParameter("keyword");
		 result = inspurSaleService.doSyncItem(userVo);
		 
		 return result;
	 }
}
