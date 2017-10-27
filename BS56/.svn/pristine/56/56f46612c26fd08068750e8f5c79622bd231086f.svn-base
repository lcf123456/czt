/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.perform;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.perform.TransverseAssessService;
import com.ztel.app.vo.perform.TransverseAssessVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.CigarettedamagedLineVo;
import com.ztel.app.vo.wms.CigarettedamagedVo;
import com.ztel.app.vo.wms.ItemstockLineVo;
import com.ztel.app.vo.wms.ShipOrderVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.util.FileUtil;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author lcf
 * @since 2017年2月26日
 * 横向考核
 */
@Controller
@RequestMapping("/perform/transverseAssess")
public class TransverseAssessCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(TransverseAssessCtrl.class);
	
	@Autowired
	private TransverseAssessService transverseAssessService = null;
	
	/**
	 * 横向考核
	 * @param request
	 * @return
	 */
	@RequestMapping("toTransverseAssess")
	public String index(HttpServletRequest request) {
		//String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常)
		//request.setAttribute("damagedtype", damagedtype);
		return "/perform/v_transverseAssess";
	}
	
	/**
	 * 领导考核
	 * @param request
	 * @return
	 */
	@RequestMapping("toTransverseAssessLeader")
	public String toTransverseAssessLeader(HttpServletRequest request) {
		//String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常)
		//request.setAttribute("damagedtype", damagedtype);
		return "/perform/v_transverseAssessLeader";
	}
	
	/**
	 * 横向考核汇总
	 * @param request
	 * @return
	 */
	@RequestMapping("toTransverseAssessReport")
	public String toTransverseAssessReport(HttpServletRequest request) {
		//String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常)
		//request.setAttribute("damagedtype", damagedtype);
		return "/perform/v_transverseAssessReport";
	}
	
	@RequestMapping(value="getTransverseAssessPageList")
	 @ResponseBody
	 public Map<String, Object> getTransverseAssessPageList(TransverseAssessVo transverseAssessVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 String keyword = transverseAssessVo.getKeyword();
		 String searchtime = request.getParameter("searchtime");
		 if(searchtime==null)searchtime=DateUtil.getyyyy_mm()+"-01";
		 //if(searchtime==null)searchtime="2017-02-01";
		 String searchtime2 = request.getParameter("searchtime2");
		 if(searchtime2==null)searchtime2=DateUtil.getyyyy_mm_dd();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 if(searchtime!=null&&!searchtime.equals("")){
				Date searchtimeD = sdf.parse(searchtime);
				Date searchtime2D = sdf.parse(searchtime2);
				transverseAssessVo.setBegintime(searchtimeD);
				transverseAssessVo.setEndtime(searchtime2D);
		 }
		 
		Pagination<?> page = this.getPagination(request);
		if (transverseAssessVo!=null) {
			 page.setParam(transverseAssessVo);
		}
		List<TransverseAssessVo> transverseAssessVoList = new ArrayList<TransverseAssessVo>();
		transverseAssessVoList = transverseAssessService.selectTransverseAssessPageList(page);
		
		 result.put("rows",transverseAssessVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	
	@RequestMapping(value="getTransverseAssessList")
	 @ResponseBody
	 public List<TransverseAssessVo> getTransverseAssessList(HttpServletRequest request) throws Exception{
		List<TransverseAssessVo> result = new ArrayList<TransverseAssessVo>();
		TransverseAssessVo transverseAssessVo = new TransverseAssessVo();
		String keyword = request.getParameter("keyword");
		 String searchtime = request.getParameter("searchtime");
		 String ctypesearch = request.getParameter("ctypesearch");
		 String order = request.getParameter("order");
		 String sort = request.getParameter("sort");
		 if(searchtime==null)searchtime=DateUtil.getyyyy_mm()+"-01";
		 String searchtime2 = request.getParameter("searchtime2");
		 if(searchtime2==null)searchtime2=DateUtil.getyyyy_mm_dd();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 if(searchtime!=null&&!searchtime.equals("")){
				Date searchtimeD = sdf.parse(searchtime);
				Date searchtime2D = sdf.parse(searchtime2);
				transverseAssessVo.setBegintime(searchtimeD);
				transverseAssessVo.setEndtime(searchtime2D);
		 }
		 transverseAssessVo.setCtype(ctypesearch);
		 transverseAssessVo.setKeyword(keyword);
		 if(order!=null&&!order.equals("")&&sort!=null&&!sort.equals(""))
		 {
			 transverseAssessVo.setSortParam(sort+" "+order);
		 }
		 else{
			 transverseAssessVo.setSortParam("id desc");
		 }
		 result = transverseAssessService.selectTransverseAssessList(transverseAssessVo);
		return result;
	}

	
	 /**
	  * 新增横向考核
	  * @return
	  */
	 @RequestMapping(value="doSave")
	 @ResponseBody
	 public  Map<String, Object> doSave(HttpServletRequest request) throws Exception
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String checkeddeptid_new = request.getParameter("checkeddeptid_new");//被考核部门
		 if(checkeddeptid_new==null||checkeddeptid_new.equals(""))checkeddeptid_new="0";
		 String assessdate_new = request.getParameter("assessdate_new");//检查日期
		 String checktype_new = request.getParameter("checktype_new");//检查项目
		 String ctype_new = request.getParameter("ctype_new");//考核类别
		 if(ctype_new==null||ctype_new.equals(""))ctype_new="0";
		 String amount_new = request.getParameter("amount_new");//考核金额
		 if(amount_new==null||amount_new.equals(""))amount_new="0";
		 String content_new = request.getParameter("content_new");//考核内容
		 
		 Long userid = 0L;
		 int checkdeptid = 0;
		 String username = "";
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
			 checkdeptid = userVo.getDeptid();
			 username = userVo.getUsername();
		 }
		 
		 TransverseAssessVo transverseAssessVo = new TransverseAssessVo();
		 transverseAssessVo.setCreateid(userid);
		 transverseAssessVo.setCheckdeptid(checkdeptid);
		 transverseAssessVo.setCheckeddeptid(Integer.parseInt(checkeddeptid_new));
		 transverseAssessVo.setChecktype(Integer.parseInt(checktype_new));
		 transverseAssessVo.setAmount(new BigDecimal(amount_new));
		 transverseAssessVo.setContent(content_new);
		 transverseAssessVo.setCreatetime(new Date());
		 transverseAssessVo.setCtype(ctype_new);
		 transverseAssessVo.setFlag("10");
		 transverseAssessVo.setCreatename(username);

		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 if(assessdate_new!=null&&!assessdate_new.equals("")){
				Date assessdate_newD = sdf.parse(assessdate_new);
				transverseAssessVo.setAssessdate(assessdate_newD);
		 }
		 
		 try{
			 transverseAssessService.insertTransverseAssess(transverseAssessVo);
		 
		 map.put("msg", "新增成功");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 
	 /**
	  * 审核
	  * @return
	  */
	 @RequestMapping(value="doAudit")
	 @ResponseBody
	 public  Map<String, Object> doAudit(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String id = request.getParameter("asid");
		 String amount = request.getParameter("amount");//入库单号
		 
		 TransverseAssessVo transverseAssessVo = new TransverseAssessVo();
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 transverseAssessVo.setAssessid(userVo.getId());
			 transverseAssessVo.setAssessname(userVo.getUsername());
			 transverseAssessVo.setAssessdate(new Date());
		 }
		 transverseAssessVo.setId(new BigDecimal(id));
		 transverseAssessVo.setAmount(new BigDecimal(amount));;
		 transverseAssessVo.setFlag("20");
		 try{
			 transverseAssessService.doAudit(transverseAssessVo);
			// cigarettedamagedService.doAudit(cigarettedamagedVo);
		 
		 map.put("msg", "审核成功");
		 }catch(Exception e){
			 map.put("msg", "审核失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 
	
	 /**
	  * 删除
	  * @return
	  */
	 @RequestMapping(value="doDel")
	 @ResponseBody
	 public  Map<String, Object> doDel(HttpServletRequest request)
	 {
		 Map<String, Object> resultMap=new HashMap<String, Object>();  
			
		 String id = request.getParameter("id");
		 if(id==null||id.equals(""))id="0";

		 try{
			 transverseAssessService.doDel(new BigDecimal(id));
		 resultMap.put("msg", "删除成功！");
		 }catch(Exception e){
			 resultMap.put("msg", "删除失败！");
		 }
		 resultMap.put("total", 1);
		 return resultMap;
	 }
	 
	 @RequestMapping("doExportToExcel")
		@ResponseBody
		public void doExportToExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
			

		 List<TransverseAssessVo> result = new ArrayList<TransverseAssessVo>();
			TransverseAssessVo transverseAssessVo = new TransverseAssessVo();
			String keyword = request.getParameter("keyword");
			 String searchtime = request.getParameter("searchtime");
			 String ctypesearch = request.getParameter("ctypesearch");
			 String order = request.getParameter("order");
			 String sort = request.getParameter("sort");
			 if(searchtime==null)searchtime=DateUtil.getyyyy_mm()+"-01";
			 String searchtime2 = request.getParameter("searchtime2");
			 if(searchtime2==null)searchtime2=DateUtil.getyyyy_mm_dd();
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			 if(searchtime!=null&&!searchtime.equals("")){
					Date searchtimeD = sdf.parse(searchtime);
					Date searchtime2D = sdf.parse(searchtime2);
					transverseAssessVo.setBegintime(searchtimeD);
					transverseAssessVo.setEndtime(searchtime2D);
			 }
			 transverseAssessVo.setCtype(ctypesearch);
			 transverseAssessVo.setKeyword(keyword);
			 if(order!=null&&!order.equals("")&&sort!=null&&!sort.equals(""))
			 {
				 transverseAssessVo.setSortParam(sort+" "+order);
			 }
			 else{
				 transverseAssessVo.setSortParam("id desc");
			 }
			 
			ArrayList<TransverseAssessVo> itemStockList =( ArrayList<TransverseAssessVo>) transverseAssessService.selectTransverseAssessList(transverseAssessVo);

			List<String> needPrintFields=new ArrayList<String>();
		     needPrintFields.add("assessdatestr");
		     needPrintFields.add("checkdeptname");
		     needPrintFields.add("checktypename");
		     needPrintFields.add("checkeddeptname");
		     needPrintFields.add("content");
		     needPrintFields.add("amount");
		     needPrintFields.add("ctypename");
		     needPrintFields.add("flagname");
		     
			 List<String> ColumnTitle=new ArrayList<String>();
			 ColumnTitle.add("考核日期");
			 ColumnTitle.add("检查部门");
			 ColumnTitle.add("检查项目");
			 ColumnTitle.add("被考核部门");
			 ColumnTitle.add("考核内容");
			 ColumnTitle.add("考核金额");
			 ColumnTitle.add("类型");
			 ColumnTitle.add("考核状态");
			 
			 String sheetname="横向考核汇总";
			 String title="横向考核汇总";
				 
			 FileUtil.ExportToExcel(itemStockList,sheetname,title, needPrintFields, ColumnTitle,  response);
			

		}
	
}
	
    
