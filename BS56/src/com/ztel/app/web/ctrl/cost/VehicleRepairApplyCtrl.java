/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.cost;

import java.math.BigDecimal;
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
import com.ztel.app.service.PubService;
import com.ztel.app.service.cost.SPLTypeServcie;
import com.ztel.app.service.cost.VehicleRepairApplyVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.sys.RouteInfoService;
import com.ztel.app.vo.cost.VehicleRepairApplyVo;
import com.ztel.app.vo.cost.VehicleRepairCostVo;
import com.ztel.app.vo.sys.RouteInfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author NJ
 * @since 2017年6月26日 */
@Controller
@RequestMapping("/cost/vehiclerepairapply")
public class VehicleRepairApplyCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(VehicleRepairApplyCtrl.class);
	
	@Autowired
	private VehicleRepairApplyVoService vehicleRepairApplyVoService = null;
	
	@Autowired
	private RouteInfoService routeInfoService = null;
	
	@Autowired
	private PubService pubService = null;
	
	@Autowired
	private SPLTypeServcie splTypeServcie = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toVehicleRepairApplyList")
	public String index(HttpServletRequest request) {
		return "/cost/v_vehiclerepair";
	}
	
	@RequestMapping("toVehicleRepairReceipt")
	public String toVehicleRepairReceipt(HttpServletRequest request) {
		String id=request.getParameter("id");
		request.setAttribute("id", id);
		return "/cost/v_vehiclerepairreceipt";
	}
	/**
	  * 车辆维修申报列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getVehicleRepairPageList")
	 @ResponseBody
	 public  Map<String, Object> getVehicleRepairPageList(VehicleRepairApplyVo vehicleRepairApplyVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (vehicleRepairApplyVo!=null) {
			 page.setParam(vehicleRepairApplyVo);
		}
		 
		 List<VehicleRepairApplyVo> paras = vehicleRepairApplyVoService.getVehicleRepairPageList(page);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 
	 /**
	  * 查找维修车辆
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getRouteVehicleInfo")
	 @ResponseBody
	 public  List<RouteInfoVo> getRouteVehicleInfo(HttpServletRequest request) throws Exception {
		 
		 //Map<String, Object> result=new HashMap<String, Object>();  
		 
		 String keywd=request.getParameter("keywd");
		 
		 List<RouteInfoVo> paras = routeInfoService.selectRoutesByCond(keywd);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 //result.put("rows",paras);  
		 //result.put("total",paras.size());  
		 
		 return paras;
	 }
	 
	 /**
	  * 查找维修车辆
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getReceiptInfo")
	 @ResponseBody
	 public void getReceiptInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 
		 String id=request.getParameter("id");
		 VehicleRepairApplyVo vehicleRepairApplyVo=vehicleRepairApplyVoService.getVehicleRepairApplyInfoByPrimaryKey(id);
		 
		 
		//直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
		 String result = JSON.toJSONString(vehicleRepairApplyVo);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);  
	 }
//	 
//	 /**
//      * 获取物资供应商下拉列表
//      * @return
//      * @throws Exception
//      */
//     @RequestMapping("getSuppliersCombobox")
//     @ResponseBody
//     public List<HashMap<String, String>> getSuppliersCombobox(@RequestParam("ctype") String ctype,HttpServletRequest request) {
//    	 // TODO Auto-generated method stub
//    	 SupplierInfoVo supplierInfoVo=new SupplierInfoVo();
//    	 if(ctype!=null&&!"".equals(ctype))supplierInfoVo.setCtype(ctype);
//    	 List<HashMap<String, String>> boxList=new ArrayList<>();
//		 boxList=supplierInfoVoService.getSuppliersCombobox(supplierInfoVo);
//    	 return boxList;
//     }
//     
//     /**
//      * 获取物资类别下拉列表
//      * @return
//      * @throws Exception
//      */
//     @RequestMapping("getSPLTypesCombobox")
//     @ResponseBody
//     public List<HashMap<String, String>> getSPLTypesCombobox(@RequestParam("clevel") int clevel,@RequestParam("fid") int fid,HttpServletRequest request) {
//    	 // TODO Auto-generated method stub
//    	 SPLTypeInfoVo splTypeInfoVo=new SPLTypeInfoVo();
//    	 splTypeInfoVo.setClevel(clevel);
//    	 splTypeInfoVo.setFid(fid);
//    	 List<HashMap<String, String>> boxList=new ArrayList<>();
//    	 boxList=splTypeServcie.getSPLTypeInfoCombobox(splTypeInfoVo);
//    	 return boxList;
//     }
//	 	
	 /**
	  * 维修申报新增
	  * @param request
	  * @return
	  */
	 @RequestMapping("doVehicleRepairApplyAdd")
	 @ResponseBody
	 public  void doVehicleRepairApplyAdd(VehicleRepairApplyVo vehicleRepairApplyVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/cost/vehiclerepairapply/doVehicleRepairApplyAdd", "车辆维修申报", "新增", "");
		 
		 try {
			 //申报vo
			 long id=pubService.getSequence("S_COST_VEHICLEREPAIRAPPLY");
			 
			 String code="000000"+id;
			 code=code.substring(code.length()-6);
			 
			 vehicleRepairApplyVo.setId(id+"");
			 vehicleRepairApplyVo.setCode(code);
			 vehicleRepairApplyVo.setIsreply("10");
			 vehicleRepairApplyVo.setPrintflag("0");
			 vehicleRepairApplyVo.setStatus("10");
			 vehicleRepairApplyVo.setCreateid(userVo.getId());
			 vehicleRepairApplyVo.setCreatename(userVo.getUsername());
			 vehicleRepairApplyVo.setVehiclemainitem(vehicleRepairApplyVo.getMainitem());
			 
			 //费用明细vo
			 VehicleRepairCostVo vehicleRepairCostVo=new VehicleRepairCostVo();
			 vehicleRepairCostVo.setId(new BigDecimal(id));
			 vehicleRepairCostVo.setDid(id+"");
			 vehicleRepairCostVo.setVehicleid(vehicleRepairApplyVo.getVehicleid());
			 vehicleRepairCostVo.setStatus("10");
			 vehicleRepairCostVo.setRoutecode(vehicleRepairApplyVo.getRoutecode());
			  //-----------------------------------------------------------------------------
			 
			 vehicleRepairApplyVoService.doVehicleRepairApplyAdd(vehicleRepairApplyVo, vehicleRepairCostVo);
			 
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
	  * 车辆维修申报--分部主任审核
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doVehicleRepairApplyAudit",method=RequestMethod.POST)
	 // @ResponseBody
	 public   void doVehicleRepairApplyAudit(VehicleRepairApplyVo vehicleRepairApplyVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/cost/vehiclerepairapply/doVehicleRepairApplyAudit", "车辆维修申报", "分部主任审核", "");
			 
			 VehicleRepairApplyVo param=new VehicleRepairApplyVo();
			 param.setId(vehicleRepairApplyVo.getId());
			 param.setStatus(vehicleRepairApplyVo.getStatus());
			 param.setLeaderid(userVo.getId());
			 param.setLeadersuggestion(vehicleRepairApplyVo.getLeadersuggestion());
			 
			 vehicleRepairApplyVoService.doVehicleRepairApplyUpdate(param);
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
	  * 车辆维修申报--车管员审核
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doVehicleRepairApplySafeAudit",method=RequestMethod.POST)
	 // @ResponseBody
	 public   void doVehicleRepairApplySafeAudit(VehicleRepairApplyVo vehicleRepairApplyVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/cost/vehiclerepairapply/doVehicleRepairApplySafeAudit", "车辆维修申报", "车管员审核", "");
			 
			 vehicleRepairApplyVo.setVehiclectrid(userVo.getId());
			 
			 vehicleRepairApplyVoService.doVehicleRepairApplySafeAudit(vehicleRepairApplyVo);
			 
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
//	 
//	 /**
//	  * 物资管理--结算
//	  * @return
//	  * @throws Exception
//	  */                                    
//	 @RequestMapping(value="doSettleSupplies",method=RequestMethod.POST)
//	 // @ResponseBody
//	 public  void doSettleSupplies(@RequestParam("id") List<Integer> ids,HttpServletRequest request,HttpServletResponse response) throws Exception {
//		 Map<String, Object> map=new HashMap<String, Object>();  
//		 int total=0;
//		 if (ids!=null&&ids.size()>0) {
//			 total = ids.size();
//		}
//		 try {
//			 suppliesImpVoService.doSuppliesSettle(ids);
//			 UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
//			 operationlogService.insertLog(sessionUserVo, "/cost/suppliesimp/doSettleSupplies", "物资管理", "结算", "");
//			 map.put("msg", "成功");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();  
//			map.put("msg", "失败");
//		}
//		 map.put("total", total);
//		 
//		//直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
//		 String result = JSON.toJSONString(map);
//		 response.setContentType("text/html;charset=UTF-8");
//		 response.getWriter().write(result);  
//	 }
//	 
//	 @RequestMapping("doExportSuppliesSettleExcel")
//	 @ResponseBody
//	 public  void doExportSuppliesSettleExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
//		 Pagination<?> page = this.getPagination(request);
//		 page.setNumPerPage(1000);
//		 //取结算标志
//		 String settleMentFlag=request.getParameter("settlementflag");
//		 //取要导出的id串
//		 String ids=request.getParameter("ids");
//		 //将id字符串转换成int数组
//		 String[]arr=ids.split(",");
//		 int len=arr.length;
//		 int[]idarr=new int[len];
//		 for(int i=0;i<len;i++){
//			 idarr[i]=Integer.parseInt(arr[i]);
//		 }
//		 
//		 //多参数用map传值
//		 Map<String,Object> map=new HashMap<>();
//		 map.put("flag", settleMentFlag);
//		 map.put("ids", idarr);
//		 //page.setParam(suppliesImpVo);
//		 
//		 //取导出数据
//		 ArrayList<SuppliesImpVo> suppliesImpList = ( ArrayList<SuppliesImpVo>)suppliesImpVoService.getSuppliesSettleList(map);
//		 
//	     List<String> needPrintFields=new ArrayList<String>();
//	     needPrintFields.add("typename");
//	     needPrintFields.add("code");
//	     needPrintFields.add("splname");
//	     needPrintFields.add("suppliername");
//	     needPrintFields.add("price");
//	     needPrintFields.add("unitid");
//	     needPrintFields.add("initqty");
//	     needPrintFields.add("totalamount");
//	     needPrintFields.add("imptime");
//	     needPrintFields.add("settleflagname");
//	     
//		 List<String> ColumnTitle=new ArrayList<String>();
//		 ColumnTitle.add("物资类别");
//		 ColumnTitle.add("物资编号");
//		 ColumnTitle.add("物资名称");
//		 ColumnTitle.add("供应商");
//		 ColumnTitle.add("单价(元)");
//		 ColumnTitle.add("单位");
//		 ColumnTitle.add("入库数量");
//		 ColumnTitle.add("入库金额(元)");
//		 ColumnTitle.add("入库时间");
//		 ColumnTitle.add("结算状态");
//		 
//		 String sheetname="未结算物资信息";
//		 String title="未结算物资信息";
//		 //打印已结算列表，加入结算日期
//		 if("10".equals(settleMentFlag)){
//			 needPrintFields.add("settledate");
//			 ColumnTitle.add("结算日期");
//			 sheetname="已结算物资信息";
//			 title="已结算物资信息";
//		 }
//		 
//		FileUtil.ExportToExcel(suppliesImpList,sheetname,title, needPrintFields, ColumnTitle,  response);
//		}
//
///**
// * 入库单列表
// * @return
// * @throws Exception
// */
//@RequestMapping("getSuppliesImpList")
//@ResponseBody
//public  Map<String, Object> getSuppliesImpList(SuppliesImpVo suppliesImpVo,HttpServletRequest request) throws Exception {
//	 Pagination<?> page = this.getPagination(request);
//
//	 Map<String, Object> result=new HashMap<String, Object>();  
//	
//	 if (suppliesImpVo!=null) {
//		 page.setParam(suppliesImpVo);
//	}
//	 
//	 List<SuppliesImpVo> paras = suppliesImpVoService.searchSuppliesImpList(page);
//	 //System.out.println(paras.size());
//	 //System.out.println(paras.get(0).getParanameE());
//	 result.put("rows",paras);  
//	 result.put("total",page.getTotalCount());  
//
//	 return result;
//}
//@RequestMapping("getSuppliesimpExcel")
//@ResponseBody
//public  void getSuppliesimpExcel(SuppliesImpVo suppliesImpVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
//	 Pagination<?> page = this.getPagination(request);
//	 page.setNumPerPage(1000000);
//	 Map<String, Object> result=new HashMap<String, Object>();  
//	 if (suppliesImpVo!=null) {
//		 page.setParam(suppliesImpVo);
//	
//	}
//	 ArrayList<SuppliesImpVo> suppliesimpList = ( ArrayList<SuppliesImpVo>)suppliesImpVoService.searchSuppliesImpList(page);
//List<String> needPrintFields=new ArrayList<String>();
//	needPrintFields.add("typename");
//	needPrintFields.add("code");
//	needPrintFields.add("splname");
//	needPrintFields.add("suppliername");
//	needPrintFields.add("price");
//	needPrintFields.add("unitid");
//	needPrintFields.add("initqty");
//	needPrintFields.add("totalamount");
//	needPrintFields.add("splbrand");
//	List<String> ColumnTitle=new ArrayList<String>();
//	ColumnTitle.add("物资类别");
//	ColumnTitle.add("编号");
//	ColumnTitle.add("物品名称");
//	ColumnTitle.add("供应商");
//	ColumnTitle.add("单价（元）");
//	ColumnTitle.add("单位");
//	ColumnTitle.add("实收数量");
//	ColumnTitle.add("总价");
//	ColumnTitle.add("入库时间");
//	List<String> footer=new ArrayList<String>() ;
//	double total=0d;
//	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
//	for(int i=0;i<suppliesimpList.size();i++)
//	{
//		total=total+suppliesimpList.get(i).getTotalamount().doubleValue();
//		suppliesimpList.get(i).setSplbrand(format.format(suppliesimpList.get(i).getImptime()));
//				
//	}
//	DecimalFormat df2 = new DecimalFormat("###.00");
//	String tempTotal=df2.format(total);
//	footer.add("备注：                                                                                                                                   验收人签章：                                                                                                           合计：  "+tempTotal);
//    
//	FileUtil.ExportToExcel(suppliesimpList,DateUtil.getyyyy_mm_dd()+"入库单",DateUtil.getyyyy_mm_dd()+"入库单", needPrintFields, ColumnTitle, footer,response);
//	}
///**
// * 入库汇总查询统计导出EXCEL
// * @return
// * @throws Exception
// */
//@RequestMapping("getStoragecountExcel")
//@ResponseBody    
//public  void getStorgecountExcel(SuppliesImpVo suppliesImpVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
//	 Pagination<?> page = this.getPagination(request);
//	 page.setNumPerPage(1000000);
//	 Map<String, Object> result=new HashMap<String, Object>();  
//	 if (suppliesImpVo!=null) {
//		 page.setParam(suppliesImpVo);
//	
//	}
//	 ArrayList<SuppliesImpVo> suppliesimpPageList = ( ArrayList<SuppliesImpVo>)suppliesImpVoService.getSuppliesImpPageList(page);
//List<String> needPrintFields=new ArrayList<String>();
//    needPrintFields.add("typename");	
//    needPrintFields.add("code");
// 	needPrintFields.add("splname");
// 	needPrintFields.add("suppliername");
//	needPrintFields.add("price");
//	needPrintFields.add("unitid");
//	needPrintFields.add("initqty");
//	needPrintFields.add("totalamount");
//	needPrintFields.add("splbrand");
//	List<String> ColumnTitle=new ArrayList<String>();
//	ColumnTitle.add("物资类别");
//	ColumnTitle.add("编号");
//	ColumnTitle.add("物品名称");
//	ColumnTitle.add("供应商");
//	ColumnTitle.add("单价");
//	ColumnTitle.add("单位");
//	ColumnTitle.add("入库数量");
//	ColumnTitle.add("总价");
//	ColumnTitle.add("入库时间");
//	List<String> footer=new ArrayList<String>() ;
//	double total=0d;
//	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//	for(int i=0;i<suppliesimpPageList.size();i++)
//	{
//		total=total+suppliesimpPageList.get(i).getTotalamount().doubleValue();
//		suppliesimpPageList.get(i).setSplbrand(format.format(suppliesimpPageList.get(i).getImptime()));
//				
//	}
//	DecimalFormat df2 = new DecimalFormat("###.00");
//	String tempTotal=df2.format(total);
//	footer.add("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　金额合计:　　　"+tempTotal);
//    
//	FileUtil.ExportToExcel(suppliesimpPageList,suppliesImpVo.getBegdate()+"至"+suppliesImpVo.getEnddate()+"入库单汇总",suppliesImpVo.getBegdate()+"至"+suppliesImpVo.getEnddate()+"入库单汇总", needPrintFields, ColumnTitle, footer,response);
//	}
//
//
///**
// * 入库单列表
// * @return
// * @throws Exception
// */
//@RequestMapping("getSuppliesImpListByCond")
//@ResponseBody
//public  Map<String, Object> getSuppliesImpListByCond(HttpServletRequest request) throws Exception {
//
//	 Map<String, Object> result=new HashMap<String, Object>();  
//	 List<SuppliesImpVo> paras = new ArrayList<SuppliesImpVo>();
//	 String splname = request.getParameter("splname");
//	 SuppliesImpVo suppliesImpVo2 = new SuppliesImpVo();
//	 suppliesImpVo2.setSplname(splname);
//	 try{
//		 paras= suppliesImpVoService.getSuppliesImpListByCond(suppliesImpVo2);
//		 result.put("rows",paras);  
//		 result.put("total",paras.size());  
//	 }
//	 catch(Exception e){
//		 result.put("rows",paras);  
//		 result.put("total",0);  
//		 e.printStackTrace();
//	 }
//
//	 return result;
//}
}