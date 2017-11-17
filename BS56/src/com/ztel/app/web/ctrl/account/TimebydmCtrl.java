package com.ztel.app.web.ctrl.account;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.account.TimebydmVoService;
import com.ztel.app.service.produce.OrderService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.account.TimebydmVo;
import com.ztel.app.vo.cost.SPLConsumeVo;
import com.ztel.app.vo.sys.BlockcustomerVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.ItemVo;
import com.ztel.app.vo.wms.ShipOrderVo;
import com.ztel.app.web.ctrl.sys.BlockcustomerCtrl;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * 预付款客户
 * @author lcf
 *
 */
@Controller
@RequestMapping("/account/timebydm")
public class TimebydmCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(TimebydmCtrl.class);
	@Autowired
	private TimebydmVoService timebydmVoService = null;
	
	@Autowired
	private OrderService orderService = null;

	@Autowired
	private OperationlogService operationlogService = null;
	@RequestMapping("toPrepayvehicle")
	public String toPrepayvehicle(HttpServletRequest request) {
		return "/account/v_prepayvehicle";
		//return "/gis/v_truckseq";
	}
	@RequestMapping("toOrderdate")
	public String toOrderdate(HttpServletRequest request) {
		return "/account/v_orderdate";
		//return "/gis/v_truckseq";
	}
	/**
	 * 获取订单日期或送货日期
	 * @param outBoundVo 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getTimebydm")
	 @ResponseBody
	 public Map<String, Object> getTimebydm(HttpServletRequest request) throws Exception{
		 Map<String, Object> map=new HashMap<String, Object>();  
		 
		String date = request.getParameter("date");
		if(date==null||date.equals(""))date =( new Date()).toString();
		String type = request.getParameter("type");
		if(type==null||type.equals(""))type="0";//type=0:根据送货日期取订单日期    type=1：根据订单日期取送货日期
		
		 date = timebydmVoService.getTimebydm(date, type);
		 map.put("timebydm", date);
		return map;
	}
	/**
	  * 获取订单配送日期信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getTimebydmdateList")
	 @ResponseBody
	 public Map<String, Object> getTimebydmdateList(TimebydmVo timebydmVo, HttpServletRequest request) throws Exception{
		 Pagination<?> page = this.getPagination(request);
		 Map<String, Object> result=new HashMap<String, Object>();  
		 if (timebydmVo!=null) {
			 //System.out.println("timebydmVo="+timebydmVo.getId()); 
			 page.setParam(timebydmVo);
		}
		 
		 List<TimebydmVo> paras = timebydmVoService.getTimebydmdatePageList(page);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 
	
	/**
	  * 修改订单配送日期
	  * @return
	  */
	 @RequestMapping(value="doupdateOrderdate",method=RequestMethod.POST)
	 @ResponseBody
		 public Map<String, Object>  doupdateOrderdate(TimebydmVo timebydmVo,HttpServletResponse response, HttpServletRequest request) throws Exception {
			 Map<String, Object> map=new HashMap<String, Object>();  
			 int total=0;
		 try {  
			 timebydmVoService.updateTimebydmdate(timebydmVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/account/timebydm/doupdateOrderdate", "订单配送日期", "修改", "");
			 map.put("msg", "成功");
			 total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
			 total=1;
		}
		 map.put("total", total);
		 //String result = JSON.toJSONString(map);
		// response.setContentType("text/html;charset=UTF-8");
		// response.getWriter().write(result);
		 return map;
	 }
	 
	 

	 	
	


}
