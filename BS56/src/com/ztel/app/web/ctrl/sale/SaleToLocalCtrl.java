package com.ztel.app.web.ctrl.sale;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.inspur.service.InspurSaleService;
import com.ztel.app.service.sale.SaleAllService;
import com.ztel.app.service.wms.ShipOrderService;
import com.ztel.app.vo.sale.SaleorderheadVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.web.ctrl.BaseCtrl;

@Controller
@RequestMapping("/sale/saletolocal")
public class SaleToLocalCtrl extends BaseCtrl{
	 
	@Autowired
	private InspurSaleService inspurSaleService = null;
	
	@Autowired
	private SaleAllService saleAllService = null;
	
	@Autowired
	private ShipOrderService shipOrderService = null;

	/**
	 * 基础数据同步
	 * @param request
	 * @return
	 */
	@RequestMapping("toSyncbase")
	public String toSynccustomer(HttpServletRequest request) {
		return "/sale/v_syncbase";
	}
	
	/**
	 * 银行结算同步
	 * @param request
	 * @return
	 */
	@RequestMapping("toSyncsettlementflag")
	public String toSyncsettlementflag(HttpServletRequest request) {
		return "/sale/v_syncsmtflag";
	}
	
	@RequestMapping("doSyncCustomer")
	 @ResponseBody
	 public  Map<String, Object> doSyncCustomer(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 String msg = saleAllService.doSyncCustomer();
		 result.put("resultmsg", msg);
		 return result;
	 }
	
	
	/**
	 * 同步订单
	 * @param request
	 * @return
	 */
	@RequestMapping("doSyncOrder")
	 @ResponseBody
	 public  Map<String, Object> doSyncOrder(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 String orderdate = request.getParameter("orderdate");
		 if(orderdate==null||orderdate.equals(""))orderdate = DateUtil.getyyyy_mm_dd();
		 
		 String msg = saleAllService.doSyncOrder(orderdate);
		 result.put("resultmsg", msg);
		 
		 return result;
	 }
	
	/**
	 * 同步商品信息
	 * @param request
	 * @return
	 */
	@RequestMapping("doSyncItem")
	 @ResponseBody
	 public  Map<String, Object> doSyncItem(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 String msg = saleAllService.doSyncItem();
		 result.put("resultmsg", msg);
		 
		 return result;
	 }
	
	/**
	 * 银行结算同步
	 * @param request
	 * @return
	 */
	@RequestMapping("doSyncOrderStmtflag")
	 @ResponseBody
	 public  Map<String, Object> doSyncOrderStmtflag(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 String orderdate = request.getParameter("orderdate");
		 if(orderdate==null||orderdate.equals(""))orderdate = DateUtil.getyyyy_mm_dd();
		 
		 String msg = saleAllService.doSyncOrderStmtflag(orderdate);
		 result.put("resultmsg", msg);
		 
		 return result;
	 }
	
	/**
	 * 取商品信息、客户信息、订单信息的记录数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("doGetDataCount")
	 @ResponseBody
	 public  Map<String, Object> doGetDataCount(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 
		 String orderdate = request.getParameter("orderdate");
		 if(orderdate==null||orderdate.equals(""))orderdate = DateUtil.getyyyy_mm_dd();
		 
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
//		 Date orderdateD = sdf.parse(orderdate);
		 
		 int  itemCount= saleAllService.selectAllItemCount();//商品数量
		 
		 int  customerCount = saleAllService.selectAllCustomerCount();//零售户数量
		 
		 int orderlineCount = saleAllService.selectAllOrderlineCount(orderdate);//订单明细数量
		 
		 BigDecimal orderheadCount = new BigDecimal("0");
		 BigDecimal orderheadQty = new BigDecimal("0");
		 BigDecimal orderheadAmt = new BigDecimal("0");
		 SaleorderheadVo  saleorderheadVo = saleAllService.selectAllOrderheadVo(orderdate);//订单头
		 if(saleorderheadVo!=null ){
			 orderheadCount = saleorderheadVo.getId();
			 orderheadQty= saleorderheadVo.getRtotalqty();
			 orderheadAmt = saleorderheadVo.getRtotalamount();
		 }
		 
		 result.put("itemCount", itemCount);
		 result.put("customerCount", customerCount);
		 result.put("orderlineCount", orderlineCount);
		 result.put("orderheadCount", orderheadCount);
		 result.put("orderheadQty", orderheadQty);
		 result.put("orderheadAmt", orderheadAmt);
		 String itemmsg = "商品信息共"+itemCount+"条。";
		 String customermsg =  "零售户信息共"+customerCount+"条。";
		 String ordermsg = "订单头信息："+orderheadCount+"条，金额："+orderheadAmt+",数量："+orderheadQty+"。";
		 ordermsg = ordermsg + "订单明细："+orderlineCount+"条。";
		 result.put("itemmsg", itemmsg);
		 result.put("customermsg", customermsg);
		 result.put("ordermsg", ordermsg);
		 return result;
	 }
	
	/**
	 * 取中间表订单信息的记录数,用于扣款同步时的判断，如果为0：未同步  1：已同步
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("doGetOrderCount")
	 @ResponseBody
	 public  Map<String, Object> doGetOrderCount(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		String msg = "0";
		 
		 String orderdate = request.getParameter("orderdate");
		 if(orderdate==null||orderdate.equals(""))orderdate = DateUtil.getyyyy_mm_dd();
		 

		 
		 BigDecimal orderheadCount = new BigDecimal("0");
		 SaleorderheadVo  saleorderheadVo = saleAllService.selectAllOrderheadVo(orderdate);//订单头
		 if(saleorderheadVo!=null ){
			 orderheadCount = saleorderheadVo.getId();//订单记录数
			 if(orderheadCount.compareTo(new BigDecimal("0"))==1){
				 msg = "1";
			 }
		 }
		 result.put("msg", msg);
		 return result;
	 }
	
	/**
	 * 取本地表订单信息的记录数,用于银行结算同步时的判断，如果为0：未同步  1：已同步
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("doGetShiporderCount")
	 @ResponseBody
	 public  Map<String, Object> doGetShiporderCount(HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		String msg = "0";
		 
		 String orderdate = request.getParameter("orderdate");
		 if(orderdate==null||orderdate.equals(""))orderdate = DateUtil.getyyyy_mm_dd();
		 
		 BigDecimal orderheadCount = new BigDecimal("0");
		 int  count = shipOrderService.selectShiporderCount(orderdate);//订单头
		 if(count>0 ){
				 msg = "1";
		 }
		 result.put("msg", msg);
		 return result;
	 }


}
