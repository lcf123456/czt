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
import com.ztel.app.service.PubService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.wms.InBoundLineService;
import com.ztel.app.service.wms.InBoundService;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.InBoundLineVo;
import com.ztel.app.vo.wms.InBoundVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author Zeal
 * @since 2017年2月26日
 *入库单
 */
@Controller
@RequestMapping("/wms/inbound")
public class InboundCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(InboundCtrl.class);

	@Autowired
	private OperationlogService operationlogService = null;
	
	@Autowired
	private InBoundService inBoundService = null;
	
	@Autowired
	private InBoundLineService  inBoundLineService = null;
	
	@Autowired
	private PubService  pubService = null;
	
	@RequestMapping("toInbound")
	public String index(HttpServletRequest request) {
		return "/wms/v_inbound";
	}
	
	@RequestMapping("toConfiscation")
	public String toConfiscation(HttpServletRequest request) {
		return "/wms/v_confiscation";
	}
	
	@RequestMapping("toinboundreport")
	public String toinboundreport(HttpServletRequest request) {
		return "/wms/v_inboundreport";
	}
	
	/**
	 * 获取罚没烟入库列表
	 * @param inBoundVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getInBoundPageList")
	 @ResponseBody
	 public Map<String, Object> getInBoundPageList(InBoundVo inBoundVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 String intype = request.getParameter("intype");
		 
		Pagination<?> page = this.getPagination(request);
		if (inBoundVo!=null) {
			if(intype!=null)inBoundVo.setIntype(new BigDecimal(intype));
			 page.setParam(inBoundVo);
		}
		List<InBoundVo> inBoundVoList = new ArrayList<InBoundVo>();
		inBoundVoList = inBoundService.selectInBoundPageList(page);
		
		 result.put("rows",inBoundVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	
	/**
	 * 获取入库单列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getInBoundList")
	 @ResponseBody
	 public List<InBoundVo> getInBoundList(HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 String keyword = request.getParameter("keyword");
		 InBoundVo inBoundVo = new InBoundVo();
		 inBoundVo.setKeyword(keyword);
	
		List<InBoundVo> inBoundVoList = new ArrayList<InBoundVo>();
		inBoundVoList = inBoundService.selectInBoundList(inBoundVo);
		
		return inBoundVoList;
	}
	
	@RequestMapping(value="getInBoundLineList")
	 @ResponseBody
	 public List<InBoundLineVo> getInBoundLineList(HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 String inboundid = request.getParameter("inboundid");
		 InBoundLineVo inBoundLineVo = new InBoundLineVo();
		 inBoundLineVo.setInboundid(new BigDecimal(inboundid));
	
		List<InBoundLineVo> inBoundLineVoList = new ArrayList<InBoundLineVo>();
		inBoundLineVoList = inBoundLineService.selectListByCond(inBoundLineVo);
		
		return inBoundLineVoList;
	}
	
	/**
	 * 罚没烟入库
	 * @param idLst
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="doConfiscationImp",method=RequestMethod.POST)
	 // @ResponseBody
	 public  void doConfiscationImp(@RequestParam("id") List<Integer> idLst,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 //System.out.println(idLst.size());
		 try {
			 
			 inBoundService.doConfiscationImp(idLst);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/wms/inbound/doConfiscationImp", "罚没烟入库", "入库", "");
			 map.put("msg", "成功");
			 total=idLst.size();
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
	 * 入库明细删除
	 * @param idLst
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="doInboundLineDel",method=RequestMethod.POST)
	// @ResponseBody
	public  void doInboundLineDel(@RequestParam("inbounddetailid") List<String> idLst,@RequestParam("inboundid") String inboundid,@RequestParam("totalnum") String totalnum,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();  
		int total=0;
		//System.out.println(idLst.size());
		try {
			inBoundLineService.doInboundLineDel(idLst,inboundid,totalnum);
			UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			operationlogService.insertLog(userVo, "/wms/inbound/doInboundLineDel", "入库明细", "删除", "");
			
			map.put("msg", "成功");
			total=idLst.size();
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
	 * 入库单作废
	 * @param idLst
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="doDestroyInbound",method=RequestMethod.POST)
	// @ResponseBody
	public  void doDestroyInbound(@RequestParam("inboundid") List<String> idLst,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();  
		int total=0;
		//System.out.println(idLst.size());
		try {
			UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			operationlogService.insertLog(userVo, "/wms/inbound/doDestroyInbound", "入库单作废", "作废", "");
			
			inBoundService.doDestroyInbound(idLst);
			
			map.put("msg", "成功");
			total=idLst.size();
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
	 * 保存入库单以及入库明细(罚没烟入库等）
	 * @param request
	 * @return
	 */
	 @RequestMapping(value="doAddInboundAndLine")
	 @ResponseBody
	 public  Map<String, Object> doAddInboundAndLine(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String status=request.getParameter("status");
		 //组装InBoundVo数据
		 InBoundVo inBoundVo=new InBoundVo();
		 String inboundid = request.getParameter("inboundid");
		 String addType="0";
		 //若无入库单id,则生成入库单id
		 if("-1".equals(inboundid)){
			 inboundid=pubService.getSequence("S_WMS_INOUTBOUND")+"";
			 addType="1";
		 }
		 inBoundVo.setInboundid(new BigDecimal(inboundid));
		 inBoundVo.setNavicert(request.getParameter("navicert"));
		 inBoundVo.setContractno(request.getParameter("contractno"));
		 inBoundVo.setQty(new BigDecimal(request.getParameter("qty")).add(new BigDecimal(request.getParameter("itemqty"))));
		 inBoundVo.setIntype(new BigDecimal(request.getParameter("intype")));
		 inBoundVo.setConsignsor(request.getParameter("consignsor"));
		 inBoundVo.setStatus(status);
		 inBoundVo.setRemarks(request.getParameter("remarks"));
		 //20171101--------------------
		 inBoundVo.setSupplier(request.getParameter("supplier"));
		 //inBoundVo.setCreatetime(request.getParameter(createtime));
		 
		 //组装InBoundLineVo数据
		 String itemval=request.getParameter("cigarettecode");
		 String cigarettecode=itemval.split("-")[0];
		 String barcode=itemval.split("-")[1];
		 InBoundLineVo inBoundLineVo=new InBoundLineVo();
		 inBoundLineVo.setInboundid(new BigDecimal(inboundid));
		 inBoundLineVo.setInbounddetailid(new BigDecimal(pubService.getSequence("S_WMS_INOUTBOUND_LINE")+""));
		 inBoundLineVo.setCigarettecode(cigarettecode);
		 inBoundLineVo.setCigarettename(request.getParameter("cigarettename"));
		 inBoundLineVo.setBoxqty(new BigDecimal(request.getParameter("itemqty")));
		 inBoundLineVo.setStatus(status);
		 inBoundLineVo.setBarcode(barcode);
		 
		 try{
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/wms/inbound/doAddInboundAndLine", "入库单新增--intype="+request.getParameter("intype"), "新增", "");
			 inBoundService.doAddInboundAndLine(inBoundVo, inBoundLineVo,addType);
			 
			 map.put("inboundid", inboundid);
			 map.put("status", status);
			 map.put("msg", "成功");
		 }catch(Exception e){
			 map.put("inboundid", -1);
			 map.put("msg", "失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
		 * 管控中心：仓储管控：入库汇总
		 * @param inBoundVo
		 * @return
		 */
	 @RequestMapping(value="getInboundReportList")
	 @ResponseBody
	 public List<InBoundLineVo> getInboundReportList(HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 InBoundVo inBoundVo = new InBoundVo();
		 String consignsor = request.getParameter("consignsorsearch");//货主
		 if(consignsor!=null&&!consignsor.equals("")&&!consignsor.equals("0000")){
			 inBoundVo.setConsignsor(consignsor);
		 }
		 String intype = request.getParameter("intype");//入库类型（工业来烟（订货）10、调拨入库 20、退货入库 30、罚没烟 40
		 intype = "10";
		 inBoundVo.setIntype(new BigDecimal(intype));
		 
		 String searchtime = request.getParameter("searchtime");
		 if(searchtime==null)searchtime=DateUtil.getyyyy_mm_dd();
		 //searchtime="2016-08-17";
		 String searchtime2 = request.getParameter("searchtime2");
		 if(searchtime2==null)searchtime2=DateUtil.getyyyy_mm_dd();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 if(searchtime!=null&&!searchtime.equals("")){
				Date searchtimeD = sdf.parse(searchtime);
				Date searchtime2D = sdf.parse(searchtime2);
				inBoundVo.setBegintime(searchtimeD);
				inBoundVo.setEndtime(searchtime2D);
		 }
		 
	
		List<InBoundLineVo> inBoundLineVoList = new ArrayList<InBoundLineVo>();
		inBoundLineVoList = inBoundLineService.selectInboundReportListByCond(inBoundVo);
		
		return inBoundLineVoList;
	}
}
	
    
