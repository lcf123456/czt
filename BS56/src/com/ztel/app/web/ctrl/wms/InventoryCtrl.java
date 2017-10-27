/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.wms;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import com.ztel.app.service.wms.InventoryVoService;
import com.ztel.app.service.wms.StorageAreaInOutService;
import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.ATSCellInfoDetailVo;
import com.ztel.app.vo.wms.InventoryLineVo;
import com.ztel.app.vo.wms.InventoryVo;
import com.ztel.app.vo.wms.StorageAreaInOutVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @since 2017年2月26日
 *盘点信息
 */
@Controller
@RequestMapping("/wms/inventory")
public class InventoryCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(InventoryCtrl.class);

	@Autowired
	private OperationlogService operationlogService = null;
	
	@Autowired
	private InventoryVoService inventoryVoService = null;
	
	
	@Autowired
	private InventoryLineVoService inventoryLineVoService = null;
	
	@Autowired
	private StorageAreaInOutService storageAreaInOutService = null;
	
	@Autowired
	private PubService  pubService = null;
	
	@RequestMapping("toInventory")
	public String index(HttpServletRequest request) {
		return "/wms/v_inventory";
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
			 operationlogService.insertLog(userVo, "/wms/inventory/doInventoryAdd", "盘点信息主表新增", "新增", "");
			 
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
		 try{
			 //UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 //operationlogService.insertLog(userVo, "/wms/inventory/doInventoryInfoComplete", "盘点明细信息", "新增", "");
			 //ATSCellInfoDetailVo[]ATSCellObjs=(ATSCellInfoDetailVo[]) getDTOArray(request,"atscell",ATSCellInfoDetailVo.class);
			 //TFoodJhtzMainVO    tFoodJhtzMainVO=JSON.parseObject(models.get("main").toString(),TFoodJhtzMainVO.class); //获取出来的json字符串转换成相对应的对象
			 
			 //取立库盘点数据
		     List<ATSCellInfoDetailVo>  ATSCellList=new ArrayList<ATSCellInfoDetailVo>();
		     ATSCellList=JSON.parseArray(models.get("atscell").toString(), ATSCellInfoDetailVo.class);//获取出来的json list形式的字符串转换成list形式的对象
			 //取散烟区数据
		     List<InventoryLineVo>  inoutList=new ArrayList<InventoryLineVo>();
		     inoutList=JSON.parseArray(models.get("scattered").toString(), InventoryLineVo.class);//获取出来的json list形式的字符串转换成list形式的对象
		     //取分拣区数据
		     List<SortTroughVo>  troughList=new ArrayList<SortTroughVo>();
		     troughList=JSON.parseArray(models.get("sorting").toString(), SortTroughVo.class);//获取出来的json list形式的字符串转换成list形式的对象
		     //取重力式货架数据
		     List<SortTroughVo>  shelfList=new ArrayList<SortTroughVo>();
		     shelfList=JSON.parseArray(models.get("shelf").toString(), SortTroughVo.class);//获取出来的json list形式的字符串转换成list形式的对象
		     
		     inventoryLineVoService.doInventoryComplete(inventoryId, ATSCellList, inoutList, troughList, shelfList);
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
	
    
