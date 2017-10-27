/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.cost;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.cost.SPLTypeStockByDateVoService;
import com.ztel.app.service.cost.SPLTypeStockVoService;
import com.ztel.app.service.cost.SuppliesImpVoService;
import com.ztel.app.vo.cost.SPLTypeStockByDateVo;
import com.ztel.app.vo.cost.SPLTypeStockVo;
import com.ztel.app.vo.cost.SuppliesImpVo;
import com.ztel.framework.util.FileUtil;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author NJ
 * @since 2017年7月14日 */
@Controller
@RequestMapping("/cost/stock")
public class SPLTypeStockCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(SPLTypeStockCtrl.class);
	
	@Autowired
	private SuppliesImpVoService suppliesImpVoService = null;
	
	@Autowired
	private SPLTypeStockVoService splTypeStockVoService = null;
	
	@Autowired
	private SPLTypeStockByDateVoService splTypeStockByDateVoService = null;
	
	@RequestMapping("toCurrTypeStock")
	public String index(HttpServletRequest request) {
		request.setAttribute("showFlag", "currStock");
		return "/cost/v_currstock";
	}
	
	@RequestMapping("toStockByDate")
	public String index1(HttpServletRequest request) {
		request.setAttribute("showFlag", "stockByDate");
		return "/cost/v_stockbydate";
	}
	
	/**
	  * 当前库存列表信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getTypeStockList")
	 @ResponseBody
	 public  Map<String, Object> getTypeStockList(SPLTypeStockVo splTypeStockVo,HttpServletRequest request) throws Exception {
		 //Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (splTypeStockVo!=null) {
			 List<SPLTypeStockVo> paras = splTypeStockVoService.getTypeStockList(splTypeStockVo);
			 //System.out.println(paras.size());
			 //System.out.println(paras.get(0).getParanameE());
			 result.put("rows",paras);  
			 result.put("total",paras.size());  
		}

		 return result;
	 }
	 
	 /**
	  * 时间段内物资类别库存信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getSPLTypeStockByDate")
	 @ResponseBody
	 public  Map<String, Object> getSPLTypeStockByDate(SPLTypeStockByDateVo splTypeStockByDateVo,HttpServletRequest request) throws Exception {
		 //Pagination<?> page = this.getPagination(request);
		 
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 if (splTypeStockByDateVo!=null) {
			 List<SPLTypeStockByDateVo> paras = splTypeStockByDateVoService.getSPLTypeStockByDate(splTypeStockByDateVo);
			 //System.out.println(paras.size());
			 //System.out.println(paras.get(0).getParanameE());
			 result.put("rows",paras);  
			 result.put("total",paras.size());  
		 }
		 
		 return result;
	 }
	 
	 /**
	  * 当前库存列表信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getTypeStockListForPrint")
	 @ResponseBody
	 public  List<SPLTypeStockVo> getTypeStockListForPrint(SPLTypeStockVo splTypeStockVo,HttpServletRequest request) throws Exception {
		 
		 List<SPLTypeStockVo> paras = splTypeStockVoService.getTypeStockListForPrint(splTypeStockVo);
		 
		 return paras;
	 }
	 
	 
	 /**
	  * 物资类别的明细信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getStockDetailList")
	 @ResponseBody
	 public  Map<String, Object> getStockDetailList(String typeid,HttpServletRequest request) throws Exception {
		 //Pagination<?> page = this.getPagination(request);
		 
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
			 List<SuppliesImpVo> paras = suppliesImpVoService.getSuppliesImpListByTypeidForStock(typeid);
			 //System.out.println(paras.size());
			 //System.out.println(paras.get(0).getParanameE());
			 result.put("rows",paras);  
			 result.put("total",paras.size());  
		 
		 return result;
	 }
	 
	 @RequestMapping("doExportStockToExcel")
	 @ResponseBody
	 public  void doExportStockToExcel(SPLTypeStockVo splTypeStockVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 page.setNumPerPage(1000);
		 
		 ArrayList<SPLTypeStockVo> paras = ( ArrayList<SPLTypeStockVo>)splTypeStockVoService.getTypeStockList(splTypeStockVo);
		 
	     List<String> needPrintFields=new ArrayList<String>();
	     needPrintFields.add("typename");
	     needPrintFields.add("maintypename");
	     needPrintFields.add("unit");
	     needPrintFields.add("surplusqty");
	     needPrintFields.add("totalamount");
	     
		 List<String> ColumnTitle=new ArrayList<String>();
		 ColumnTitle.add("物资类别");
		 ColumnTitle.add("物资所属");
		 ColumnTitle.add("单位");
		 ColumnTitle.add("库存数量");
		 ColumnTitle.add("库存金额(元)");
		 
		 String sheetname="当前物资库存信息";
		 String title="当前物资库存信息";
		 
		FileUtil.ExportToExcel(paras,sheetname,title, needPrintFields, ColumnTitle,  response);
		}

}