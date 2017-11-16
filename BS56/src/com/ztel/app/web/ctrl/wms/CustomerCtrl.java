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
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.wms.CustomerService;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.CustomerVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author Zeal
 * @since 2017年2月26日
 * 多级联动测试
 */
@Controller
@RequestMapping("/wms/customer")
public class CustomerCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(CustomerCtrl.class);
	
	@Autowired
	private CustomerService customerService = null;

	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toCustomer")
	public String index(HttpServletRequest request) {
		return "/wms/v_customer";
	}
	
	@RequestMapping("toPrepayCustomer")
	public String toPrepayCustomer(HttpServletRequest request) {
		return "/account/v_prepaycustomer";
	}
	
	@RequestMapping("toBillCustomer")
	public String toBillCustomer(HttpServletRequest request) {
		return "/account/v_billcustomer";
	}
	
	@RequestMapping("tocustomerseqadjust")
	public String tocustomerseqadjust(HttpServletRequest request) {
		return "/wms/v_customerseqadjust";
	}
	
	/**
	  * 获取零售户信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getCustomerList")
	 @ResponseBody
	 public  Map<String, Object> getCustomersPageList(CustomerVo customerVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (customerVo!=null) {
			// System.out.println("customerVo="+customerVo.getId()); 
			 page.setParam(customerVo);
		}
		 
		 List<CustomerVo> paras = customerService.getCustomersPageList(page);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 /**
	  * 获取零售户信息列表-排序
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getCustomerseqList")
	 @ResponseBody
	 public  Map<String, Object> getCustomerseqPageList(CustomerVo customerVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (customerVo!=null) {
			 page.setParam(customerVo);
		}
		 
		 List<CustomerVo> paras = customerService.getCustomerseqPageList(page);
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 
	 /**
	  * 零售户顺序更新
	  * @return
	  */
	 @RequestMapping(value="doUpdateseq")
	 @ResponseBody
	 public  Map<String, Object> doUpdateseq(HttpServletRequest request)
	 {
		 Map<String, Object> resultMap=new HashMap<String, Object>();  
			
		 String id = request.getParameter("id");
		 
		 CustomerVo customerVo = new CustomerVo();
		 if(id!=null)
			 customerVo.setId(id);
			 String addressseq = request.getParameter("addressseq");
			 if(addressseq==null)addressseq="10000";
			 customerVo.setAddressseq(new BigDecimal(addressseq));
		 try{
			 customerService.updatecustomerseqByPrimarykey(customerVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/wms/customer/doUpdateseq", "顺序调整", "顺序调整", "");
			 resultMap.put("msg", "成功！");
		 }catch(Exception e){
			 resultMap.put("msg", "失败！");
		 }
		 resultMap.put("total", 1);
		 return resultMap;
	 }
	 	
	 /**
	  * 查看零售户信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doviewCustomer",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void doviewCustomer(CustomerVo customervo,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
       
		 try {
			 customerService.viewCustomerVo(customervo);
			 map.put("msg", "成功");
			 total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);
		 //return map;
	 }
	 
	 /**
	  * 根据条件获取零售户信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getCustomersByCond")
	 @ResponseBody
	 public  Map<String, Object> getCustomersByCond(CustomerVo customerVo,HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 List<CustomerVo> paras = new ArrayList<CustomerVo>();
		 if((customerVo.getParam()!=null&&!"".equals(customerVo.getParam()))||(customerVo.getRoutecode()!=null&&!"".equals(customerVo.getRoutecode())))paras=customerService.getListByCond(customerVo);
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",paras.size());  

		 return result;
	 }
	 
	 
	 /**
	  * 获取零售户信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getPrepayCustomers")
	 @ResponseBody
	 public  Map<String, Object> getPrepayCustomers(CustomerVo customerVo,HttpServletRequest request) throws Exception {
		 
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 List<CustomerVo> paras = customerService.getPrepayCustomers(customerVo);
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",paras.size());  
		 
		 return result;
	 }
	 
	@RequestMapping("doCustomerAdd")
	@ResponseBody
		public Map<String, Object> doCustomerAdd(HttpServletRequest request,CustomerVo customerVo)
		{
			
			Map<String, Object> map=new HashMap<String, Object>();
			 
			 try{
				 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
				 operationlogService.insertLog(userVo, "/wms/customer/doCustomerAdd", "预付款一级客户", "新增", "");
				 
				 customerService.doCustomerAdd(customerVo);
				 
				 map.put("msg", "成功");
			 }catch(Exception e){
				 map.put("msg", "失败");
				 e.printStackTrace();
			 }
			 map.put("total", "1");
			 return map;
		}
	
	/**
	 * 添加二级预付款客户
	 * @param request
	 * @param 
	 * @return
	 */
	@RequestMapping("doCustomerPLAdd")
	@ResponseBody
	public Map<String, Object> doCustomerPLAdd(HttpServletRequest request,@RequestParam("id")List<String> ids)
	{
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		try{
				 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
				 operationlogService.insertLog(userVo, "/wms/customer/doCustomerAdd", "预付款二级客户", "新增", "");
			
			customerService.doCustomerPLAdd(ids);
			
			map.put("msg", "成功");
		}catch(Exception e){
			map.put("msg", "失败");
			e.printStackTrace();
		}
		map.put("total", "1");
		return map;
	}
	
	/**
	 * 添加发票客户
	 * @param request
	 * @param 
	 * @return
	 */
	@RequestMapping("doBillCustomerPLAdd")
	@ResponseBody
	public Map<String, Object> doBillCustomerPLAdd(HttpServletRequest request,@RequestParam("id")List<String> ids,@RequestParam("billtype")String billtype)
	{
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		try{
//			UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
//			operationlogService.insertLog(userVo, "/wms/customer/doBillCustomerPLAdd", "发票客户", "新增", "");
			
			customerService.doBillCustomerPLAdd(billtype, ids);
			
			map.put("msg", "成功");
		}catch(Exception e){
			map.put("msg", "失败");
			e.printStackTrace();
		}
		map.put("total", "1");
		return map;
	}
	
	/**
	 * 添加预付款客户
	 * @param request
	 * @param 
	 * @return
	 */
	@RequestMapping("doDelPrepayCustomer")
	@ResponseBody
	public Map<String, Object> doDelPrepayCustomer(HttpServletRequest request,@RequestParam("id")List<String> ids)
	{
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		try{
				 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
				 operationlogService.insertLog(userVo, "/wms/customer/doDelPrepayCustomer", "预付款客户", "删除", "");
			
			     customerService.doDelPrepayCustomer(ids);
			
			     map.put("msg", "成功");
		}catch(Exception e){
			map.put("msg", "失败");
			e.printStackTrace();
		}
		map.put("total", "1");
		return map;
	}
	
	/**
	  * 获取发票零售户信息列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getBillCustomersPageList")
	 @ResponseBody
	 public  Map<String, Object> getBillCustomersPageList(CustomerVo customerVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (customerVo!=null) {
			// System.out.println("customerVo="+customerVo.getId()); 
			 page.setParam(customerVo);
		}
		 
		 List<CustomerVo> paras = customerService.getBillCustomersPageList(page);
		 //System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 
		
		/**
		 * 发票客户删除
		 * @param request
		 * @param 
		 * @return
		 */
		@RequestMapping("doDelBillCustomer")
		@ResponseBody
		public Map<String, Object> doDelBillCustomer(HttpServletRequest request,@RequestParam("id")List<String> ids)
		{
			
			Map<String, Object> map=new HashMap<String, Object>();
			
			try{
				customerService.doDelBillCustomer(ids);
				UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
				operationlogService.insertLog(userVo, "/wms/customer/doDelBillCustomer", "发票客户", "删除", "");
				map.put("msg", "成功");
			}catch(Exception e){
				map.put("msg", "失败");
				e.printStackTrace();
			}
			map.put("total", "1");
			return map;
		}

		/**
		  * 预付款一级客户下拉框
		  * @return
		  */
		 @RequestMapping(value="getPrepaycustomerCombobox")
		 @ResponseBody
		 public  List<HashMap<String, String>> getPrepaycustomerCombobox() 
		 {
			 List<HashMap<String, String>> boxList=new ArrayList<>();

				 boxList =  customerService.getPrepaycustomerCombobox();
			 return boxList;
		 }

}
	
    
