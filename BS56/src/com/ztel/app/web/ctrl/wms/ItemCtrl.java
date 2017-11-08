/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.wms;

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
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.wms.ConsignorService;
import com.ztel.app.service.wms.ItemService;
import com.ztel.app.vo.sq.StarinfoVo;
import com.ztel.app.vo.sys.BlockcustomerVo;
import com.ztel.app.vo.sys.DeptVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.sys.VehicleVo;
import com.ztel.app.vo.wms.ConsignorVo;
import com.ztel.app.vo.wms.ItemVo;
import com.ztel.app.vo.wms.LanewayVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author yy
 * @since 2017年9月11日
 */
@Controller
@RequestMapping("/wms/item")
public class ItemCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(ItemCtrl.class);
	@Autowired
	private OperationlogService operationlogService = null;
	@Autowired
	private ItemService itemService = null;
	@RequestMapping("toBrandinfo")
	public String index(HttpServletRequest request) {
		
		return "/wms/v_brandinfo";
	}
	@RequestMapping("toIteminfo")
	public String index1(HttpServletRequest request) {
		
		return "/wms/v_iteminfo";
	}
	
	/**
	  * 获取品牌信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getBrandinfoList")
	 @ResponseBody
	 public Map<String, Object> getBrandinfoList(ItemVo itemVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		Pagination<?> page = this.getPagination(request);
		if (itemVo!=null) {
			 page.setParam(itemVo);
		}
		List<ItemVo> itemVoList = new ArrayList<ItemVo>();
		itemVoList = itemService.getBrandinfoList(page);
		
		 result.put("rows",itemVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	 }
	 
	 /**
	  * 获取商品信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getIteminfoList")
	 @ResponseBody
	 public Map<String, Object> getIteminfoList(ItemVo itemVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		Pagination<?> page = this.getPagination(request);
		if (itemVo!=null) {
			 page.setParam(itemVo);
		}
		List<ItemVo> itemVoList = new ArrayList<ItemVo>();
		itemVoList = itemService.getIteminfoList(page);
		
		 result.put("rows",itemVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	 }
	 
	 /**
	  * 修改品牌信息
	  * @return
	  */
	 @RequestMapping(value="doEditBrandinfo",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> doEditBrandinfo(ItemVo itemVo,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try {
			 itemService.updateBrandinfo(itemVo);
			 map.put("msg", "成功");
			 total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		// String result = JSON.toJSONString(map);
		 //response.setContentType("text/html;charset=UTF-8");
		 //response.getWriter().write(result);
		 return map;
	 }
	 
	 /**
	  * 获取卷烟品牌下拉框
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getItemComboboxByCond")
	 @ResponseBody
	 public  List<HashMap<String, String>> getItemComboboxByCond(HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 ItemVo itemVo = new ItemVo();
		 boxList=itemService.getItemComboboxByCond(itemVo);
		 return boxList;
	 }

	 /**
	  * 新增商品信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doIteminfoNew",method=RequestMethod.POST)
	@ResponseBody
	 public   Map<String, Object> doIteminfoNew(ItemVo itemVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=1;
		 itemVo.setId(itemVo.getItemno());
		 itemVo.setShortname(itemVo.getItemname());
		 try { //itemVo.setRowstatus("10");
			 itemVo.setModifyuser(String.valueOf(userVo.getId())); //修改人
		 itemVo.setModifytime(DateUtil.getDateyyyy_mm_dd());//修改时间
		     itemService.insertIteminfo(itemVo);
		     UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(sessionUserVo, "/wms/item/doIteminfoNew", "商品信息", "新增", "");
			 map.put("msg", "成功");
			 total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 
		 //直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式

		// String result = JSON.toJSONString(map);
		// response.setContentType("text/html;charset=UTF-8");
		 //response.getWriter().write(result);  
		 return map;
	 } 	
	
	 /**
	  * 删除商品信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doIteminfoDel",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> doIteminfoDel(@RequestParam("id") List<String> ids,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
			 itemService.delIteminfo(ids);
			 UserVo sessionUserVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(sessionUserVo, "/wms/item/doIteminfoDel", "商品信息", "删除", "");
			 map.put("msg", "成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 
		 return map;
	 }
}
	
    
