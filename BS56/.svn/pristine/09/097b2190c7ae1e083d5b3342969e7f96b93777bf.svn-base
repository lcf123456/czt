/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.wms;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.PubService;
import com.ztel.app.service.produce.OrderService;
import com.ztel.app.service.wms.ItemService;
import com.ztel.app.service.wms.OutBoundLineService;
import com.ztel.app.service.wms.OutBoundService;
import com.ztel.app.service.wms.ShipOrderService;
import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.ItemVo;
import com.ztel.app.vo.wms.OutBoundLineVo;
import com.ztel.app.vo.wms.OutBoundVo;
import com.ztel.app.vo.wms.ShipOrderVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author Zeal
 * @since 2017年2月26日
 *入库单
 */
@Controller
@RequestMapping("/wms/outbound")
public class OutBoundCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(OutBoundCtrl.class);
	
	@Autowired
	private OutBoundService outBoundService = null;
	
	@Autowired
	private ShipOrderService shipOrderService = null;
	
	@Autowired
	private OutBoundLineService outBoundLineService = null;
	 
	@Autowired
	private OrderService orderService = null;
	
	@Autowired
	private PubService pubService = null;
	
	@Autowired
	private ItemService itemService = null;
	
	//@Autowired
	//private OutBoundLineService  inBoundLineService = null;
	
	/**
	 * 订单出库
	 * @param request
	 * @return
	 */
	@RequestMapping("toOutbound")
	public String index(HttpServletRequest request) {
		String outboundtype = request.getParameter("outboundtype");//出库类型(10订单出库 20 调拨出库)
		request.setAttribute("outboundtype", outboundtype);
		return "/wms/v_outbound";
	}
	
	/**
	 * 调拨出库
	 * @param request
	 * @return
	 */
	@RequestMapping("toOutbounddb")
	public String toOutbounddb(HttpServletRequest request) {
		String outboundtype = request.getParameter("outboundtype");//出库类型(10订单出库 20 调拨出库)
		request.setAttribute("outboundtype", outboundtype);
		return "/wms/v_outbound_db";
	}
	
	/**
	 * 获取出库单列表
	 * @param outBoundVo 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getOutBoundPageList")
	 @ResponseBody
	 public Map<String, Object> getOutBoundPageList(HttpServletRequest request) throws Exception{
		Pagination<?> page = this.getPagination(request);
		 Map<String, Object> map=new HashMap<String, Object>();   
		String outboundtype = request.getParameter("outboundtype");
		if(outboundtype==null)outboundtype="10";//缺省10订单出库 20调拨出库
		
		String consignsorsearch = request.getParameter("consignsorsearch");
		String datetype = request.getParameter("datetype");//10出库日期  20订单日期
		String searchtime = request.getParameter("searchtime");
		String searchtime2 = request.getParameter("searchtime2");
		String keyword = request.getParameter("keyword");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		OutBoundVo outBoundVo = new OutBoundVo();
		if(searchtime!=null&&!searchtime.equals("")){
			Date searchtimeD = sdf.parse(searchtime);
			Date searchtime2D = sdf.parse(searchtime2);
			if(datetype!=null&&datetype.equals("10"))
			{
					outBoundVo.setOuttime(searchtimeD);
			}
			else if(datetype!=null&&datetype.equals("20"))
			{
				outBoundVo.setOrderdate(searchtimeD);
			}
			outBoundVo.setEndtime(searchtime2D);
		}
		
		if(consignsorsearch!=null&&!consignsorsearch.equals("")&&!consignsorsearch.equals("0000"))outBoundVo.setConsignsor(consignsorsearch);//0000为所有货主
		if(keyword!=null&&!keyword.equals(""))outBoundVo.setKeyword(keyword);
		
			outBoundVo.setOutboundtype(new BigDecimal(outboundtype));
			
			if (outBoundVo!=null) {
				 page.setParam(outBoundVo);
			}
		List<OutBoundVo> outBoundVoList = new ArrayList<OutBoundVo>();
		outBoundVoList = outBoundService.selectOutboundList(page);
		 
		map.put("rows", outBoundVoList);
		map.put("total",page.getTotalCount());  
		return map;
	}
	
	/**
	 * 获取出库单列表
	 * @param outBoundVo 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getRouteCodeList")
	 @ResponseBody
	 public List<ShipOrderVo> getRouteCodeList(HttpServletRequest request) throws Exception{
		 List<ShipOrderVo> result=new ArrayList<ShipOrderVo>(); 
		 String orderdateStr = request.getParameter("orderdate");//订单日期
		 //orderdateStr = "2017-05-23";
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    Date orderdate = sdf.parse(orderdateStr);
		    
		 ShipOrderVo shipOrderVo = new ShipOrderVo();
		 shipOrderVo.setOrderdate(orderdate);
		 result = shipOrderService.selectRoutecodeList(shipOrderVo);
		return result;
	}
	
	 /**
	  * 生成出库单：订单出库
	  * @return
	  */
	 @RequestMapping(value="doSave")
	 @ResponseBody
	 public  Map<String, Object> doSave(@RequestParam("routes") List<String> routes,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 try
		 {
			 
		
		//menuinfoService.doAddMenu(menuInfo);
		 String outtime = request.getParameter("outtime");//出库日期
		 String orderdate = request.getParameter("orderdate");//订单日期
		// String consignsor = request.getParameter("consignsor");//货主
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    Date orderdateD = sdf.parse(orderdate);
		    Date outtimeD = sdf.parse(outtime);
		    
		 Long userid = 0L;
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
		 }
		 
		 if(routes!=null&&routes.size()>0){
			 for(int i=0;i<routes.size();i++){
				 String routeqty = routes.get(i);
				 String route = routeqty.split("-")[0];//车组
				 String qty = routeqty.split("-")[1];//数量
				 String consignsor = routeqty.split("-")[2];//货主
				 //System.out.println("route:"+route+",qty="+qty);
				 Long outboundid = pubService.getSequence("S_WMS_OUTBOUND");
				 OutBoundVo outBoundVo = new OutBoundVo();
				 outBoundVo.setOutboundid(new BigDecimal(outboundid));
				 outBoundVo.setConsignsor(consignsor);
				 outBoundVo.setOrderdate(orderdateD);
				 outBoundVo.setQty(new BigDecimal(qty));
				 outBoundVo.setVehicleno(route);
				 outBoundVo.setOutboundtype(new BigDecimal("10"));
				 outBoundVo.setOuttime(outtimeD);
				 outBoundVo.setUserid(new BigDecimal(userid));
				 outBoundService.doSave(outBoundVo);
			 }
		 }
		 
		 map.put("msg", "新增成功");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 e.printStackTrace();
		 }
		 return map;
	 }
	 
	 /**
	  * 生成出库单:调拨出库
	  * @return
	  */
	 @RequestMapping(value="doSavedb")
	 @ResponseBody
	 public  Map<String, Object> doSavedb(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 try{
		 String obid = request.getParameter("obid");//id
		 String qty = request.getParameter("qty");//出库数量
		 String consignsor = request.getParameter("consignsor");//货主
		 String outtime = request.getParameter("outtime");//出库日期
		 
		 String cigarettecode = request.getParameter("cigarettecode");//卷烟编码
		 String cigarettename = request.getParameter("cigarettename");//卷烟名称
		 String jtsize = request.getParameter("jtsize");//件条比率
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    Date outtimeD = sdf.parse(outtime);
		    
		 Long id = 0L;
		 if(obid!=null&&!obid.equals("0")){//第二次插入
			 id = new Long(obid);
		 }
		 else
			 {//首次插入
			 id = pubService.getSequence("S_WMS_MACHINEDAMAGED");
			 }
		 BigDecimal userid = new BigDecimal("0");
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = new BigDecimal(userVo.getId());
		 }
		
			 OutBoundVo outBoundVo = new OutBoundVo();
			 outBoundVo.setConsignsor(consignsor);
			// outBoundVo.setOrderdate(orderdateD);
			 outBoundVo.setQty(new BigDecimal(qty));
			 //outBoundVo.setVehicleno(route);
			 outBoundVo.setOutboundtype(new BigDecimal("20"));//20调拨出库
			 outBoundVo.setOuttime(outtimeD);
			 outBoundVo.setUserid(userid);
			 outBoundService.doSavedb(outBoundVo,new BigDecimal(id),obid,cigarettecode,cigarettename,jtsize);
		 
		 map.put("msg", "新增成功");
		 map.put("obid", id+"");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
		/**
		 * 获取出库单明细列表
		 * @param outBoundVo 
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="getOutboundLineList")
		 @ResponseBody
		 public List<OutBoundLineVo>  getOutboundLineList(HttpServletRequest request) throws Exception{
			 List<OutBoundLineVo> result=new ArrayList<OutBoundLineVo>(); 
			 String outboundid = request.getParameter("outboundid");
			 result = outBoundLineService.selectByOutboundid(new BigDecimal(outboundid));
			return result;
		}
		
		 /**
		  * 删除角色
		  * @return
		  * @throws Exception
		  */
		 @RequestMapping(value="dodelete",method=RequestMethod.POST)
		 @ResponseBody
		 public   Map<String, Object> dodelete(@RequestParam("id") List<BigDecimal> id,HttpServletRequest request) throws Exception {
			 Map<String, Object> map=new HashMap<String, Object>();  
			 int total=0;
			 if (id!=null&&id.size()>0) {
				 total = id.size();
			}
			 try {
				 outBoundService.deleteOubboundById(id);
				 //UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
				 //operationlogService.insertLog(userVo, "/sys/role/roledelete", "角色管理", "删除", "");
				 map.put("msg", "成功");
			} catch (Exception e) {
				e.printStackTrace();  
				map.put("msg", "失败");
			}
			 map.put("total", total);
			 
			 return map;
		 }
		 
		 /**
			 * 获取出库单列表
			 * @param outBoundVo 
			 * @param request
			 * @return
			 * @throws Exception
			 */
			@RequestMapping(value="getItemList")
			 @ResponseBody
			 public List<ItemVo> getItemList(HttpServletRequest request) throws Exception{
				 List<ItemVo> result=new ArrayList<ItemVo>(); 
				 String keyword = request.getParameter("keyword");
				 if(keyword==null)keyword="";
				    ItemVo itemVo = new ItemVo();
				    itemVo.setKeyword(keyword);
				// shipOrderVo.setOrderdate(orderdate);
				 result = itemService.selectListByCond(itemVo);
				return result;
			}
}
