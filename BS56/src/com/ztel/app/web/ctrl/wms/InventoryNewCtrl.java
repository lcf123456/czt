/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.wms;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ztel.app.service.PubService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.wms.InventoryLineVoService;
import com.ztel.app.service.wms.InventorySumVoService;
import com.ztel.app.service.wms.InventoryVoService;
import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.ATSCellInfoDetailVo;
import com.ztel.app.vo.wms.InventoryLineVo;
import com.ztel.app.vo.wms.InventorySumVo;
import com.ztel.app.vo.wms.InventoryVo;
import com.ztel.app.vo.wms.ItemstockVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @since 2017年2月26日
 *盘点信息
 */
@Controller
@RequestMapping("/wms/inventorynew")
public class InventoryNewCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(InventoryNewCtrl.class);

	@Autowired
	private OperationlogService operationlogService = null;
	
	@Autowired
	private InventoryVoService inventoryVoService = null;
	
	@Autowired
	private InventoryLineVoService inventoryLineVoService = null;
	
	@Autowired
	private InventorySumVoService inventorySumVoService = null;
	
	@Autowired
	private PubService pubService = null;
	
	
	@RequestMapping("toInventoryNew")
	public String index(HttpServletRequest request) {
		return "/wms/v_inventory_new";
	}
	
	/**
	 * 获取各个区域的盘点信息
	 * @param inBoundVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getInventoryInfo")
	 @ResponseBody
	 public Map<String, Object> getInventoryInfo( HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 String searchDate=request.getParameter("searchdate");
		 String orderdate=request.getParameter("orderdate");
		 List<InventorySumVo>list=inventorySumVoService.selectInventoryList(searchDate,orderdate);
		
		 result.put("rows",list);  
		 result.put("total",list.size());  
		 
		return result;
	}
	/**
	 * 盘点信息查看
	 * @param inBoundVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getInventoryInfoView")
	@ResponseBody
	public Map<String, Object> getInventoryInfoView( HttpServletRequest request) throws Exception{
		Map<String, Object> result=new HashMap<String, Object>();  
		
		String searchDate=request.getParameter("searchdate");
		String inventoryid=request.getParameter("inventoryid");
		List<InventorySumVo>list=inventorySumVoService.selectInventoryListView(inventoryid,searchDate);
		
		result.put("rows",list);  
		result.put("total",list.size());  
		
		return result;
	}
	/**
	 * 获取盘点列表 带翻页信息
	 * @param inBoundVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getInventoryPageList")
	@ResponseBody
	public Map<String, Object> getInventoryPageList(InventoryVo inventoryVo, HttpServletRequest request) throws Exception{
		Map<String, Object> result=new HashMap<String, Object>();  
		
		Pagination<?> page = this.getPagination(request);
		if (inventoryVo!=null) {
			page.setParam(inventoryVo);
		}
		List<InventoryVo> inventoryVoList = new ArrayList<InventoryVo>();
		inventoryVoList = inventoryVoService.selectInventoryPageList(page);
		
		result.put("rows",inventoryVoList);  
		result.put("total",page.getTotalCount());  
		
		return result;
	}
	
	/**
	 * 新增盘点主表
	 * @param idLst
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="doInventoryAdd",method=RequestMethod.POST)
	 // @ResponseBody
	 public  void doInventoryAdd(InventoryVo inventoryVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 //System.out.println(idLst.size());
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/wms/inventorynew/doInventoryAdd", "日清日结", "新增", "");
			 
			 inventoryVo.setCreatename(userVo.getUsername());
			 inventoryVo.setCreateid(new BigDecimal(userVo.getId()));
			 
			 inventoryVoService.doInventoryAdd(inventoryVo);
			 
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
	 * 查看盘点主表的数量
	 * @param idLst
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="getInventoryCount")
	// @ResponseBody
	public  void getInventoryCount(InventoryVo inventoryVo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();  
		int total=0;
		//System.out.println(idLst.size());
		try {
			
//			inventoryVo.setCreatename(userVo.getUsername());
//			inventoryVo.setCreateid(new BigDecimal(userVo.getId()));
			
			total=inventoryVoService.getInventoryCount(inventoryVo);
			
			map.put("msg", "成功");
			//total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
			total=0;
		}
		map.put("total", total);
		
		//直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
		String result = JSON.toJSONString(map);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(result);  
	}
	
	public static Object[] getDTOArray(HttpServletRequest request,String jsonString, Class clazz) {  
		Object[] obj=null;
		try {
			String json = new String(request.getParameter(jsonString).getBytes("ISO-8859-1"),"UTF-8");
			JSONArray array = JSONArray.parseArray(json);  
		    obj = new Object[array.size()];  
		    for (int i = 0; i < array.size(); i++) {  
		        JSONObject jsonObject = array.getJSONObject(i);  
		        obj[i] = JSONObject.toJavaObject(jsonObject, clazz);
		    }  
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return obj;  
	}  
	
	/**
	 * 盘点明细信息保存
	 * @param request
	 * @return
	 */
	 @RequestMapping(value="doInventoryInfoComplete")
	 @ResponseBody
	 public  Map<String, Object> doInventoryInfoComplete(@RequestBody Map<String,Object> models,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 int total=0;
		 String inventoryId=request.getParameter("inventoryid");
		 String createtime=request.getParameter("createtime");
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 try{
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/wms/inventorynew/doInventoryInfoComplete", "日清日结", "日清日结", "");
			 //ATSCellInfoDetailVo[]ATSCellObjs=(ATSCellInfoDetailVo[]) getDTOArray(request,"atscell",ATSCellInfoDetailVo.class);
			 //TFoodJhtzMainVO    tFoodJhtzMainVO=JSON.parseObject(models.get("main").toString(),TFoodJhtzMainVO.class); //获取出来的json字符串转换成相对应的对象
			 
			 ItemstockVo itemstockVo=new ItemstockVo();
			 itemstockVo.setCreatetime(sdf.parse(createtime));
			 itemstockVo.setCreateid(userVo.getId());
			 itemstockVo.setCreatename(userVo.getUsername());
			 itemstockVo.setInventoryid(new BigDecimal(inventoryId));
			 itemstockVo.setId(new BigDecimal(pubService.getSequence("s_wms_itemstock")));
			 
			 //取盘点数据
		     List<InventorySumVo>  inventoryList=new ArrayList<InventorySumVo>();
		     inventoryList=JSON.parseArray(models.get("newdata").toString(), InventorySumVo.class);//获取出来的json list形式的字符串转换成list形式的对象
		     
		     inventoryLineVoService.doInventoryCompleteNew(inventoryId, inventoryList ,itemstockVo);
			 map.put("msg", "成功");
		 }catch(Exception e){
			 map.put("inboundid", -1);
			 map.put("msg", "失败");
			 e.printStackTrace();
		 }
		 map.put("total", total);
		 return map;
	 }
	 

		/**
		 * 查看盘点明细
		 * @param inBoundVo
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="getInventoryLine")
		 @ResponseBody
		 public Map<String, Object> getInventoryLine(InventoryLineVo inventoryLineVo, HttpServletRequest request) throws Exception{
			 Map<String, Object> result=new HashMap<String, Object>();  
			 
			
			List<InventoryLineVo> inventoryLineVoList = new ArrayList<InventoryLineVo>();
			inventoryLineVoList = inventoryLineVoService.getInventoryInfoByCond(inventoryLineVo);
			
			 result.put("rows",inventoryLineVoList);  
			 result.put("total",inventoryLineVoList.size());  
			 
			return result;
		}
		
}
	
    
