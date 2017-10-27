package com.ztel.app.web.ctrl.mobile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.mobile.OrdersignService;
import com.ztel.app.vo.mobile.OrdersignVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.util.StringUtils;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

@Controller
@RequestMapping("/mobile/ordersign")
public class OrdersignCtrl extends BaseCtrl {
	private static Logger logger = LogManager.getLogger(OrdersignCtrl.class);
	
	@Autowired
	private OrdersignService ordersignService = null;
	
	/**
	 * 签收
	 * @param request
	 * @return
	 */
	@RequestMapping("toordersign")
	public String toordersign(HttpServletRequest request) {
		//String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常)
		//request.setAttribute("damagedtype", damagedtype);
		return "/mobile/v_ordersign";
	}
	
	/**
	 * 签收统计
	 * @param request
	 * @return
	 */
	@RequestMapping("toordersignreport")
	public String toordersignreport(HttpServletRequest request) {
		//String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常)
		//request.setAttribute("damagedtype", damagedtype);
		return "/mobile/v_ordersignreport";
	}
	
	/**
	 * 签收查询，带翻页
	 * @param shipOrderVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getOrdersignPageList")
	 @ResponseBody
	 public Map<String, Object> getOrdersignPageList(OrdersignVo OrdersignVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 String keyword = OrdersignVo.getKeywd();
		//判断keyword是否是数字
		 if(keyword!=null&&!keyword.equals("")&&StringUtils.isNumeric(keyword)){//是数字
			 OrdersignVo.setKeywdnumber(keyword);
			 OrdersignVo.setKeywd("");
		 }
		 String searchtime = request.getParameter("searchtime");
		 //if(searchtime==null)searchtime=DateUtil.getyyyy_mm_dd();
		 searchtime="2017-08-20";
		 //if(searchtime==null)searchtime="2017-02-01";
		 String searchtime2 = request.getParameter("searchtime2");
		 if(searchtime2==null)searchtime2=DateUtil.getyyyy_mm_dd();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 if(searchtime!=null&&!searchtime.equals("")){
				Date searchtimeD = sdf.parse(searchtime);
				Date searchtime2D = sdf.parse(searchtime2);
				OrdersignVo.setBegintime(searchtimeD);
				OrdersignVo.setEndtime(searchtime2D);
		 }

		 
		Pagination<?> page = this.getPagination(request);
		if (OrdersignVo!=null) {
			 page.setParam(OrdersignVo);
		}
		List<OrdersignVo> ordersignVoList = new ArrayList<OrdersignVo>();
		ordersignVoList = ordersignService.selectOrdersignPageList(page);
		
		 result.put("rows",ordersignVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	
	@RequestMapping(value="getOrdersignReportList")
	 @ResponseBody
	 public List<OrdersignVo> getOrdersignReportList(HttpServletRequest request) throws Exception{
		 
		 OrdersignVo ordersignVo = new OrdersignVo();
		 String searchtime = request.getParameter("searchtime");
		 //if(searchtime==null)searchtime=DateUtil.getyyyy_mm_dd();
		 searchtime="2017-09-01";
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 if(searchtime!=null&&!searchtime.equals("")){
				Date searchtimeD = sdf.parse(searchtime);
				ordersignVo.setCreatetime(searchtimeD);
		 }

		List<OrdersignVo> ordersignVoList = new ArrayList<OrdersignVo>();
		ordersignVoList = ordersignService.selectOrdersignReportList(ordersignVo);
		
		return ordersignVoList;
	}

}
