/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.wms;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.account.OperateVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.wms.StorageAreaInOutService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.account.OperateVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.InventoryLineVo;
import com.ztel.app.vo.wms.StorageAreaInOutVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author Zeal
 * @since 2017年2月26日
 * 多级联动测试
 */
@Controller
@RequestMapping("/wms/inout")
public class StorageAreaInOutCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(StorageAreaInOutCtrl.class);
	
	@Autowired
	private OperateVoService operateVoService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@Autowired
	private StorageAreaInOutService storageAreaInOutService = null;
	
	@RequestMapping("toRefund")
	public String index(HttpServletRequest request) {
		
		return "/wms/v_refund";
	}
	
	/**
	  * 获取退货暂存列表
	  * @return 
	  * @throws Exception
	  */
	 @RequestMapping("getOperatePageList")
	 @ResponseBody
	 public  Map<String, Object> getOperatePageList(OperateVo operateVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (operateVo!=null) {
			// System.out.println("customerVo="+customerVo.getId()); 
			 page.setParam(operateVo);
		}
		 
		 List<OperateVo> paras = operateVoService.getOperatePageList(page);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 

	 /**
	  * 订单退货
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doRefund",method=RequestMethod.POST)
	 // @ResponseBody
	 public   void doRefund(@RequestParam("id") List<Integer> idLst,@RequestParam("orderno") List<String> ordernoLst,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 //System.out.println(idLst.size()+"===="+ordernoLst.size());
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/wms/inout/doRefund", "订单退货", "退货", "");
			 
			 storageAreaInOutService.doRefund(idLst,ordernoLst);
			 
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
		 * 获散烟区库存信息
		 * @param atsCellInfoDetailVo
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="getInOutInfoSummaryByCond")
		 @ResponseBody
		 public Map<String, Object> getInOutInfoSummaryByCond(StorageAreaInOutVo storageAreaInOutVo,HttpServletRequest request) throws Exception{
			 Map<String, Object> result=new HashMap<String, Object>();  
			 
			 //最近一次盘点信息查询条件
			 InventoryLineVo inventoryLineVo=new InventoryLineVo();
			 inventoryLineVo.setInventorytype(new BigDecimal(10));
			 inventoryLineVo.setAreaid(new BigDecimal(Constant.storagearea_sy));
			 
			 //散烟汇总查询条件
			 storageAreaInOutVo.setAreaid(new BigDecimal(Constant.storagearea_sy));
			 
			 List<InventoryLineVo>  inOutList=new ArrayList<InventoryLineVo>();
			 inOutList=storageAreaInOutService.getInOutInfoSummaryByCond(storageAreaInOutVo, inventoryLineVo);
			
			 result.put("rows",inOutList);  
			 result.put("total",inOutList.size());  
			 
			return result;
		}

}
	
    
