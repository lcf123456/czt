package com.ztel.app.web.ctrl.wms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.wms.WmsPubServcie;
import com.ztel.app.vo.cost.SPLTypeStockVo;
import com.ztel.app.vo.wms.InBoundVo;
import com.ztel.app.vo.wms.ItemStockVo;
import com.ztel.framework.util.FileUtil;

/**
 * wms模块公共Ctrl类
 * @author lcf
 *
 */
@Controller
@RequestMapping("/wms/pub")
public class WmsPubCtrl {
	private static Logger logger = LogManager.getLogger(WmsPubCtrl.class);
	
	@Autowired
	private WmsPubServcie wmsPubServcie = null;
	/**
	 * 库存账面量
	 * @param request
	 * @return
	 */
	@RequestMapping("toItemstock")
	public String toItemstock(HttpServletRequest request) {
		InBoundVo inBoundVo = new InBoundVo();
		List<ItemStockVo> itemStockList = wmsPubServcie.getStock(inBoundVo);
		request.setAttribute("itemStockList", itemStockList);
		return "/wms/v_itemstock";
	}
	/**
	 * 库存账面量详细列表
	 * @param request
	 * @return
	 */
	@RequestMapping("getItemstockList")
	public String getItemstockList(HttpServletRequest request) {
		String consignsor = request.getParameter("consignsor");
		
		InBoundVo inBoundVo = new InBoundVo();
		if(consignsor!=null&&!consignsor.equals("0000")){
			inBoundVo.setConsignsor(consignsor);
		}
		List<ItemStockVo> itemStockList = wmsPubServcie.getStock(inBoundVo);
		request.setAttribute("itemStockList", itemStockList);
		return "/wms/v_itemstockList";
	}
	
	/**
	 * 库存账面量详细列表
	 * @param request
	 * @return
	 */
	@RequestMapping("doExportItemstockToExcel")
	@ResponseBody
	public void doExportItemstockToExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String consignsor = request.getParameter("consignsor");
		
		InBoundVo inBoundVo = new InBoundVo();
		if(consignsor!=null&&!consignsor.equals("0000")){
			inBoundVo.setConsignsor(consignsor);
		}
		ArrayList<ItemStockVo> itemStockList =( ArrayList<ItemStockVo>) wmsPubServcie.getStock(inBoundVo);

		List<String> needPrintFields=new ArrayList<String>();
	     needPrintFields.add("cigarettename");
	     needPrintFields.add("cigarettecode");
	     needPrintFields.add("inboxqty");
	     needPrintFields.add("outboxqty");
	     needPrintFields.add("sumboxqty");
	     needPrintFields.add("initemqty");
	     needPrintFields.add("outitemqty");
	     needPrintFields.add("sumitemqty");
	     
		 List<String> ColumnTitle=new ArrayList<String>();
		 ColumnTitle.add("卷烟名称");
		 ColumnTitle.add("卷烟编码");
		 ColumnTitle.add("入库量(件)");
		 ColumnTitle.add("出库量(件)");
		 ColumnTitle.add("库存量(件)");
		 ColumnTitle.add("入库量(条)");
		 ColumnTitle.add("出库量(条)");
		 ColumnTitle.add("库存量(条)");
		 
		 String sheetname="库存账面量";
		 String title="库存账面量";
			 
		 FileUtil.ExportToExcel(itemStockList,sheetname,title, needPrintFields, ColumnTitle,  response);
		

	}
}
