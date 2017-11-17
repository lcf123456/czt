/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.cost;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.cost.SPLTypeServcie;
import com.ztel.app.service.cost.SupplierInfoVoService;
import com.ztel.app.service.cost.SuppliesImpVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.cost.SPLTypeInfoVo;
import com.ztel.app.vo.cost.SupplierInfoVo;
import com.ztel.app.vo.cost.SuppliesImpVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.util.FileUtil;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author NJ
 * @since 2017年6月26日 */
@Controller
@RequestMapping("/cost/suppliesimp")
public class SuppliesImpCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(SuppliesImpCtrl.class);
	
	@Autowired
	private SuppliesImpVoService suppliesImpVoService = null;
	
	@Autowired
	private SupplierInfoVoService supplierInfoVoService = null;
	
	@Autowired
	private SPLTypeServcie splTypeServcie = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toSuppliesImp")
	public String index(HttpServletRequest request) {
		request.setAttribute("showFlag", "imp");
		return "/cost/v_splimp";
	}
	@RequestMapping("toStoragelist")
	public String indexx(HttpServletRequest request) {
		return "/cost/v_storagelist";
	}
	@RequestMapping("toSuppliesRefund")
	public String index1(HttpServletRequest request) {
		request.setAttribute("showFlag", "refund");
		return "/cost/v_splrefund";
	}
	
	@RequestMapping("toSuppliesSettle")
	public String index2(HttpServletRequest request) {
		request.setAttribute("showFlag", "settle");
		return "/cost/v_splsettle";
	}
	
	@RequestMapping("toSPLImpSummary")
	public String index2Sum(HttpServletRequest request) {
		request.setAttribute("showFlag", "summary");
		return "/cost/v_splimpsum";
	}
	
	@RequestMapping("toStoragecount")
	public String indexc(HttpServletRequest request) {
		return "/cost/v_storagecount";
	}
	/**
	  * 物资入库信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getSuppliesImpPageList")
	 @ResponseBody
	 public  Map<String, Object> getSuppliesImpPageList(SuppliesImpVo suppliesImpVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (suppliesImpVo!=null) {
			 page.setParam(suppliesImpVo);
		}
		 
		 List<SuppliesImpVo> paras = suppliesImpVoService.getSuppliesImpPageList(page);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 
	 /**
	  * 物资退库信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getSuppliesRefundPageList")
	 @ResponseBody
	 public  Map<String, Object> getSuppliesRefundPageList(SuppliesImpVo suppliesImpVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 if (suppliesImpVo!=null) {
			 page.setParam(suppliesImpVo);
		 }
		 
		 List<SuppliesImpVo> paras = suppliesImpVoService.getSuppliesRefundPageList(page);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  
		 
		 return result;
	 }
	 
	 /**
      * 获取物资供应商下拉列表
      * @return
      * @throws Exception
      */
     @RequestMapping("getSuppliersCombobox")
     @ResponseBody
     public List<HashMap<String, String>> getSuppliersCombobox(@RequestParam("ctype") String ctype,HttpServletRequest request) {
    	 // TODO Auto-generated method stub
    	 SupplierInfoVo supplierInfoVo=new SupplierInfoVo();
    	 if(ctype!=null&&!"".equals(ctype))supplierInfoVo.setCtype(ctype);
    	 List<HashMap<String, String>> boxList=new ArrayList<>();
		 boxList=supplierInfoVoService.getSuppliersCombobox(supplierInfoVo);
    	 return boxList;
     }
     
     /**
      * 获取物资类别下拉列表
      * @return
      * @throws Exception
      */
     @RequestMapping("getSPLTypesCombobox")
     @ResponseBody
     public List<HashMap<String, String>> getSPLTypesCombobox(@RequestParam("clevel") int clevel,@RequestParam("fid") int fid,HttpServletRequest request) {
    	 // TODO Auto-generated method stub
    	 SPLTypeInfoVo splTypeInfoVo=new SPLTypeInfoVo();
    	 splTypeInfoVo.setClevel(clevel);
    	 splTypeInfoVo.setFid(fid);
    	 List<HashMap<String, String>> boxList=new ArrayList<>();
    	 boxList=splTypeServcie.getSPLTypeInfoCombobox(splTypeInfoVo);
    	 return boxList;
     }
	 	
	 /**
	  * 物资管理信息新增
	  * @param request
	  * @return
	  */
	 @RequestMapping("doInsertSuppliesImp")
	 @ResponseBody
	 public  void doInsertSuppliesImp(SuppliesImpVo suppliesImpVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 
		 try {
			 
			 suppliesImpVo.setQuantity(suppliesImpVo.getInitqty());
			 //取到物资code----------------------------------------------
			 int index=1;
			 String dateStr=DateUtil.getyyyymmdd();
			 String maxCode=suppliesImpVoService.getMaxSuppliesCode(dateStr);
			  if(maxCode==null||"".equals(maxCode)||"null".equals(maxCode))index=1;
			  else index=java.lang.Integer.parseInt(maxCode)+1;
			  maxCode="00"+index;
			  maxCode=dateStr+maxCode.substring(maxCode.length()-3);
			  suppliesImpVo.setCode(maxCode);
			  //-----------------------------------------------------------------------------
			 
			  //插入物资表
			 suppliesImpVoService.doInsertSuppliesImp(suppliesImpVo);
			 
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/cost/suppliesimp/doInsertSuppliesImp", "物资管理", "新增", "");
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
	 
	 /**
	  * 物资管理--退库
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doRefundSupplies",method=RequestMethod.POST)
	 // @ResponseBody
	 public   void doRefundSupplies(SuppliesImpVo suppliesImpVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/cost/suppliesimp/doRefundSupplies", "物资管理", "退库", "");
			 
			 //设置退货总金额
			 String refundamount=request.getParameter("refundamount");
			 //System.out.println("refundamount=="+refundamount);
			 suppliesImpVo.setTotalamount(new BigDecimal(refundamount));
			 //设置退库操作人
			 suppliesImpVo.setCreateid(new BigDecimal(userVo.getId()));
			 //退库
			 suppliesImpVoService.doRefundSupplies(suppliesImpVo);
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
	 
	 /**
	  * 物资管理--结算
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doSettleSupplies",method=RequestMethod.POST)
	 // @ResponseBody
	 public  void doSettleSupplies(@RequestParam("id") List<Integer> ids,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
			 suppliesImpVoService.doSuppliesSettle(ids);
			 UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(sessionUserVo, "/cost/suppliesimp/doSettleSupplies", "入库结算与查询", "结算", "");
			 map.put("msg", "成功");
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
	 
	 @RequestMapping("doExportSuppliesSettleExcel")
	 @ResponseBody
	 public  void doExportSuppliesSettleExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 page.setNumPerPage(1000);
		 //取结算标志
		 String settleMentFlag=request.getParameter("settlementflag");
		 //取要导出的id串
		 String ids=request.getParameter("ids");
		 //将id字符串转换成int数组
		 String[]arr=ids.split(",");
		 int len=arr.length;
		 int[]idarr=new int[len];
		 for(int i=0;i<len;i++){
			 idarr[i]=Integer.parseInt(arr[i]);
		 }
		 
		 //多参数用map传值
		 Map<String,Object> map=new HashMap<>();
		 map.put("flag", settleMentFlag);
		 map.put("ids", idarr);
		 //page.setParam(suppliesImpVo);
		 
		 //取导出数据
		 ArrayList<SuppliesImpVo> suppliesImpList = ( ArrayList<SuppliesImpVo>)suppliesImpVoService.getSuppliesSettleList(map);
		 
	     List<String> needPrintFields=new ArrayList<String>();
	     needPrintFields.add("typename");
	     needPrintFields.add("code");
	     needPrintFields.add("splname");
	     needPrintFields.add("suppliername");
	     needPrintFields.add("price");
	     needPrintFields.add("unitid");
	     needPrintFields.add("initqty");
	     needPrintFields.add("totalamount");
	     needPrintFields.add("imptime");
	     needPrintFields.add("settleflagname");
	     
		 List<String> ColumnTitle=new ArrayList<String>();
		 ColumnTitle.add("物资类别");
		 ColumnTitle.add("物资编号");
		 ColumnTitle.add("物资名称");
		 ColumnTitle.add("供应商");
		 ColumnTitle.add("单价(元)");
		 ColumnTitle.add("单位");
		 ColumnTitle.add("入库数量");
		 ColumnTitle.add("入库金额(元)");
		 ColumnTitle.add("入库时间");
		 ColumnTitle.add("结算状态");
		 
		 String sheetname="未结算物资信息";
		 String title="未结算物资信息";
		 //打印已结算列表，加入结算日期
		 if("10".equals(settleMentFlag)){
			 needPrintFields.add("settledate");
			 ColumnTitle.add("结算日期");
			 sheetname="已结算物资信息";
			 title="已结算物资信息";
		 }
		 
		FileUtil.ExportToExcel(suppliesImpList,sheetname,title, needPrintFields, ColumnTitle,  response);
		}

/**
 * 入库单列表
 * @return
 * @throws Exception
 */
@RequestMapping("getSuppliesImpList")
@ResponseBody
public  Map<String, Object> getSuppliesImpList(SuppliesImpVo suppliesImpVo,HttpServletRequest request) throws Exception {
	 Pagination<?> page = this.getPagination(request);

	 Map<String, Object> result=new HashMap<String, Object>();  
	
	 if (suppliesImpVo!=null) {
		 page.setParam(suppliesImpVo);
	}
	 
	 List<SuppliesImpVo> paras = suppliesImpVoService.searchSuppliesImpList(page);
	 //System.out.println(paras.size());
	 //System.out.println(paras.get(0).getParanameE());
	 result.put("rows",paras);  
	 result.put("total",page.getTotalCount());  

	 return result;
}
@RequestMapping("getSuppliesimpExcel")
@ResponseBody
public  void getSuppliesimpExcel(SuppliesImpVo suppliesImpVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
	 Pagination<?> page = this.getPagination(request);
	 page.setNumPerPage(1000000);
	 Map<String, Object> result=new HashMap<String, Object>();  
	 if (suppliesImpVo!=null) {
		 page.setParam(suppliesImpVo);
	
	}
	 ArrayList<SuppliesImpVo> suppliesimpList = ( ArrayList<SuppliesImpVo>)suppliesImpVoService.searchSuppliesImpList(page);
List<String> needPrintFields=new ArrayList<String>();
	needPrintFields.add("typename");
	needPrintFields.add("code");
	needPrintFields.add("splname");
	needPrintFields.add("suppliername");
	needPrintFields.add("price");
	needPrintFields.add("unitid");
	needPrintFields.add("initqty");
	needPrintFields.add("totalamount");
	needPrintFields.add("splbrand");
	List<String> ColumnTitle=new ArrayList<String>();
	ColumnTitle.add("物资类别");
	ColumnTitle.add("编号");
	ColumnTitle.add("物品名称");
	ColumnTitle.add("供应商");
	ColumnTitle.add("单价（元）");
	ColumnTitle.add("单位");
	ColumnTitle.add("实收数量");
	ColumnTitle.add("总价");
	ColumnTitle.add("入库时间");
	List<String> footer=new ArrayList<String>() ;
	double total=0d;
	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
	for(int i=0;i<suppliesimpList.size();i++)
	{
		total=total+suppliesimpList.get(i).getTotalamount().doubleValue();
		suppliesimpList.get(i).setSplbrand(format.format(suppliesimpList.get(i).getImptime()));
				
	}
	DecimalFormat df2 = new DecimalFormat("###.00");
	String tempTotal=df2.format(total);
	footer.add("备注：                                                                                                                                   验收人签章：                                                                                                           合计：  "+tempTotal);
    
	FileUtil.ExportToExcel(suppliesimpList,DateUtil.getyyyy_mm_dd()+"入库单",DateUtil.getyyyy_mm_dd()+"入库单", needPrintFields, ColumnTitle, footer,response);
	}
/**
 * 入库汇总查询统计导出EXCEL
 * @return
 * @throws Exception
 */
@RequestMapping("getStoragecountExcel")
@ResponseBody    
public  void getStorgecountExcel(SuppliesImpVo suppliesImpVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
	 Pagination<?> page = this.getPagination(request);
	 page.setNumPerPage(1000000);
	 Map<String, Object> result=new HashMap<String, Object>();  
	 if (suppliesImpVo!=null) {
		 page.setParam(suppliesImpVo);
	
	}
	 ArrayList<SuppliesImpVo> suppliesimpPageList = ( ArrayList<SuppliesImpVo>)suppliesImpVoService.getSuppliesImpPageList(page);
List<String> needPrintFields=new ArrayList<String>();
    needPrintFields.add("typename");	
    needPrintFields.add("code");
 	needPrintFields.add("splname");
 	needPrintFields.add("suppliername");
	needPrintFields.add("price");
	needPrintFields.add("unitid");
	needPrintFields.add("initqty");
	needPrintFields.add("totalamount");
	needPrintFields.add("splbrand");
	List<String> ColumnTitle=new ArrayList<String>();
	ColumnTitle.add("物资类别");
	ColumnTitle.add("编号");
	ColumnTitle.add("物品名称");
	ColumnTitle.add("供应商");
	ColumnTitle.add("单价");
	ColumnTitle.add("单位");
	ColumnTitle.add("入库数量");
	ColumnTitle.add("总价");
	ColumnTitle.add("入库时间");
	List<String> footer=new ArrayList<String>() ;
	double total=0d;
	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	for(int i=0;i<suppliesimpPageList.size();i++)
	{
		total=total+suppliesimpPageList.get(i).getTotalamount().doubleValue();
		suppliesimpPageList.get(i).setSplbrand(format.format(suppliesimpPageList.get(i).getImptime()));
				
	}
	DecimalFormat df2 = new DecimalFormat("###.00");
	String tempTotal=df2.format(total);
	footer.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　金额合计:　　　"+tempTotal);
    
	FileUtil.ExportToExcel(suppliesimpPageList,suppliesImpVo.getBegdate()+"至"+suppliesImpVo.getEnddate()+"入库单汇总",suppliesImpVo.getBegdate()+"至"+suppliesImpVo.getEnddate()+"入库单汇总", needPrintFields, ColumnTitle, footer,response);
	}


/**
 * 入库单列表
 * @return
 * @throws Exception
 */
@RequestMapping("getSuppliesImpListByCond")
@ResponseBody
public  Map<String, Object> getSuppliesImpListByCond(HttpServletRequest request) throws Exception {

	 Map<String, Object> result=new HashMap<String, Object>();  
	 List<SuppliesImpVo> paras = new ArrayList<SuppliesImpVo>();
	 String splname = request.getParameter("splname");
	 SuppliesImpVo suppliesImpVo2 = new SuppliesImpVo();
	 suppliesImpVo2.setSplname(splname);
	 try{
		 paras= suppliesImpVoService.getSuppliesImpListByCond(suppliesImpVo2);
		 result.put("rows",paras);  
		 result.put("total",paras.size());  
	 }
	 catch(Exception e){
		 result.put("rows",paras);  
		 result.put("total",0);  
		 e.printStackTrace();
	 }

	 return result;
}
}