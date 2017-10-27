/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.wms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.wms.ConsignorService;
import com.ztel.app.vo.sq.StarinfoVo;
import com.ztel.app.vo.sys.VehicleVo;
import com.ztel.app.vo.wms.ConsignorVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author sn
 * @since 2017年8月6日
 */
@Controller
@RequestMapping("/wms/consignor")
public class ConsignorCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(ConsignorCtrl.class);
	
	@Autowired
	private ConsignorService consignorService = null;
	
	@RequestMapping("toConsignor")
	public String index(HttpServletRequest request) {
		
		return "/wms/v_consignor";
	}
	
	
	/**
	  * 获取货户信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getConsignorList")
	 @ResponseBody
	 public  Map<String, Object> getConsignorList(ConsignorVo consignorVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (consignorVo!=null) {
			// System.out.println("consignorVo="+consignorVo.getId()); 
			 page.setParam(consignorVo);
		}
		 
		 List<ConsignorVo> paras = consignorService.getConsignorList(consignorVo);
		 System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 
	 /**
	  * 获取货户信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getConsignorListForComb")
	 @ResponseBody
	 public  List<HashMap<String, String>> getConsignorListForComb(ConsignorVo consignorVo,HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();

		 List<ConsignorVo> treeList = consignorService.getConsignorList(consignorVo);

		 if (treeList!=null&&treeList.size()>0) {
			 HashMap<String, String> map=new HashMap<String, String>();
			 map.put("code", "0000");
			 map.put("company", "所有");
			 boxList.add(map);
			 for(int i=0;i<treeList.size();i++){
				 ConsignorVo vo=treeList.get(i);
				 map=new HashMap<String, String>();
				 map.put("code", vo.getCode());
				 map.put("company", vo.getComapany()+"");
				 //System.out.println(vo.getId().toString()+"-------------");
				 //System.out.println(vo.getText()+"-------------");
				 boxList.add(map);
			 }
		}
		 return boxList;
	 }
	 	
//	 @RequestMapping("getConsignorPageList")
//	 @ResponseBody
//	 public  Map<String, Object> getConsignorPageList(ConsignorVo consignorVo,HttpServletRequest request) throws Exception {
//		 Pagination<?> page = this.getPagination(request);
//		 Map<String, Object> result=new HashMap<String, Object>();  
//		 if (consignorVo!=null) {
//			 page.setParam(consignorVo);
//		}
//		 List<ConsignorVo> paras = consignorService.getConsignorPageList(page);
//		 //System.out.println(paras.size());
//		 //System.out.println(paras.get(0).getParanameE());
//		 result.put("rows",paras);  
//		 result.put("total",page.getTotalCount());  
//
//		 return result;
//	 }
}
	
    
