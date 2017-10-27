package com.ztel.app.web.ctrl.cost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.PubService;
import com.ztel.app.service.cost.SPLApplyListLineService;
import com.ztel.app.service.cost.SPLApplyListService;
import com.ztel.app.service.cost.SPLTypeServcie;
import com.ztel.app.service.sys.DeptVoService;
import com.ztel.app.vo.cost.SPLApplyListLineVo;
import com.ztel.app.vo.cost.SPLApplyListVo;
import com.ztel.app.vo.sys.DeptVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

@Controller
@RequestMapping("/cost/SPLApplyList")
public class SPLApplyListCtrl extends BaseCtrl  {
	@Autowired
	private DeptVoService deptVoService = null;
	@Autowired
	private SPLApplyListService sPLApplyListService = null;
	@Autowired 
	private SPLApplyListLineService sPLApplyListLineService = null;
	
	@Autowired
	SPLTypeServcie sPLTypeServcie = null;
	@Autowired
	PubService pubService = null;
	
	@RequestMapping("toSPLApplyList")
	public String toSPLApplyList(HttpServletRequest request) {
		String listtype = request.getParameter("listtype");//生产领料或一般领料
		request.setAttribute("listtype", listtype);
		return "/cost/v_splapplylist";
	}

	/**
	  * 根据系统模块名称获取栏目信息
	  * @return
	  */
	 @RequestMapping(value="getSPLApplyList")
	 @ResponseBody
	 public  Map<String, Object> getSPLApplyList(SPLApplyListVo sPLApplyListVo,HttpServletRequest request)
	 {
		 Map<String, Object> result=new HashMap<String, Object>();  
		 Pagination<?> page = this.getPagination(request);
			
		 if (sPLApplyListVo!=null) {
			 page.setParam(sPLApplyListVo);
		}
		 //System.out.println("menuCtrl getSysMenuRight :sysid----"+sysid+",id----"+id);
		 List<SPLApplyListVo> SPLTypeInfoList=sPLApplyListService.getApplyListList(page);
		 result.put("rows",SPLTypeInfoList);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 
	 /**
	  * 新增领料申请
	  * @return
	  */
	 @RequestMapping(value="doSave")
	 @ResponseBody
	 public  Map<String, Object> doSave(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String reqType = request.getParameter("reqType");//请求类型 first首次 second非首次
		 String listidStr = request.getParameter("listid");
		 if(listidStr == null)listidStr="0";
		 if(reqType==null)reqType="first";
		 String applyuserid= request.getParameter("applyid");
		 String deptid= request.getParameter("deptid");
		 String typeid= request.getParameter("typeid");
		 String purpose= request.getParameter("purpose");
		 String surplusqty= request.getParameter("surplusqty");//可用数量/剩余库存
		 String applyqty= request.getParameter("applyqty");//申请数量
		 String listtype = "10";  //10：一般领料   20：生产领料
		 String applytype = "10";//10:申请领用，20：申请退库
		 
		 SPLApplyListVo sPLApplyListVo = new SPLApplyListVo();
		 Long listid = 0L;
		 if(reqType.equals("first"))
		 {
			 listid = pubService.getSequence("S_COST_SPLAPPLYLIST");
		 }
		 else{
			 listid = Long.parseLong(listidStr);
		 }
		 sPLApplyListVo.setId(new BigDecimal(listid));
		 sPLApplyListVo.setApplyid(Long.parseLong(applyuserid));
		 sPLApplyListVo.setPurpose(purpose);
		 sPLApplyListVo.setDeptid(Integer.parseInt(deptid));
		 sPLApplyListVo.setListtype(listtype);
		 sPLApplyListVo.setApplytype(applytype);
		 sPLApplyListVo.setAuditflag("0");
		 
		 SPLApplyListLineVo sPLApplyListLineVo = new SPLApplyListLineVo();
		 sPLApplyListLineVo.setApplyqty(new BigDecimal(applyqty));
		 sPLApplyListLineVo.setTypeid(new BigDecimal(typeid));
		 sPLApplyListLineVo.setPreapplyqty(new BigDecimal(surplusqty));
		//menuinfoService.doAddMenu(menuInfo);
		 //先取序列S_COST_SPLAPPLYLIST值
		 try{
		 sPLApplyListService.doAddApplyList(sPLApplyListVo, sPLApplyListLineVo,reqType);
		 
		 map.put("listid", listid);
		 map.put("msg", "新增成功");
		 }catch(Exception e){
			 map.put("listid", 0);
			 map.put("msg", "新增成功");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 新增领料申请,发料时仓管员可以选择物资进行发料，一般申请为先进先出，只仓管员可以选择发料物资
	  * @return
	  */
	 @RequestMapping(value="doSaveForConsume")
	 @ResponseBody
	 public  Map<String, Object> doSaveForConsume(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String splid  = request.getParameter("splid");
		 String listidStr = request.getParameter("listid");
		 if(listidStr == null)listidStr="0";
		 String typeid= request.getParameter("typeid");
		 String surplusqty= request.getParameter("surplusqty");//可用数量/剩余库存
		 String applyqty= request.getParameter("applyqty");//申请数量
		 String price= request.getParameter("price");//设备单价
		 
		 Long listid = 0L;
			 listid = Long.parseLong(listidStr);
		
		 BigDecimal applyqtyB = new BigDecimal(applyqty);
	     BigDecimal priceB = new BigDecimal(price);
	     BigDecimal amount = applyqtyB.multiply(priceB);//总金额
		 
		 SPLApplyListLineVo sPLApplyListLineVo = new SPLApplyListLineVo();
		 sPLApplyListLineVo.setListid(new BigDecimal(listidStr));
		 sPLApplyListLineVo.setSplid(Integer.parseInt(splid));
		 sPLApplyListLineVo.setApplyqty(applyqtyB);
		 sPLApplyListLineVo.setTypeid(new BigDecimal(typeid));
		 sPLApplyListLineVo.setPreapplyqty(new BigDecimal(surplusqty));
		 sPLApplyListLineVo.setPrice(priceB);
		 sPLApplyListLineVo.setAmount(amount);
		//menuinfoService.doAddMenu(menuInfo);
		 //先取序列S_COST_SPLAPPLYLIST值
		 try{
		 sPLApplyListService.doAddSPLApplyListLineVo(sPLApplyListLineVo);
		 
		 map.put("listid", listid);
		 map.put("msg", "新增成功");
		 }catch(Exception e){
			 map.put("listid", 0);
			 map.put("msg", "新增成功");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 根据申请单id获取
	  * @return
	  */
	 @RequestMapping(value="getSPLApplyListLine")
	 @ResponseBody
	 public  Map<String, Object> getSPLApplyListLineList(HttpServletRequest request)
	 {
		 Map<String, Object> result=new HashMap<String, Object>();  
		 String listid = request.getParameter("listid");
		 if(listid==null)listid="0";
		 SPLApplyListLineVo sPLApplyListLineVo = new SPLApplyListLineVo();
		 sPLApplyListLineVo.setListid(new BigDecimal(listid));
		 List<SPLApplyListLineVo> sPLApplyListLineVoList = sPLApplyListLineService.getApplyListLine(sPLApplyListLineVo);
		 
		 String isLegal = "0"; //判断发料是否合法，即申请数量小于剩余数量，默认小于或等于
		 //判断现在需要发料的申请数量是否小于剩余数量
		 if(sPLApplyListLineVoList!=null&&sPLApplyListLineVoList.size()>0){
			 for(int i=0;i<sPLApplyListLineVoList.size();i++){
				 SPLApplyListLineVo sPLApplyListLineVo2 = sPLApplyListLineVoList.get(i);
				// BigDecimal sub = sPLApplyListLineVo2.getApplyqty().subtract(sPLApplyListLineVo2.getQuantity());//申请数量-剩余数量
				 if(sPLApplyListLineVo2.getApplyqty().compareTo(sPLApplyListLineVo2.getQuantity())==1){//申请数量大于剩余数量
					 isLegal = "1";
					 break;
				 }
			 }
		 }
		 result.put("rows",sPLApplyListLineVoList);  
		 result.put("total",sPLApplyListLineVoList.size()); 
		 result.put("islegal",isLegal);  
		 return result;
	 }
	 
	 /**
	  * 根据系统模块名称获取栏目信息
	  * @return
	  */
	 @RequestMapping(value="doAudit")
	 @ResponseBody
	 public  Map<String, Object> doAudit(HttpServletRequest request)
	 {
		 Map<String, Object> resultMap=new HashMap<String, Object>();  
		 Date mngauditdate = new Date();
			
		 String auditflag = request.getParameter("auditflag");
		 String listid = request.getParameter("listid");
		 String mngid = request.getParameter("mngid");
		 String mngsuggestion = request.getParameter("mngsuggestion");
		 SPLApplyListVo  sPLApplyListVo = new SPLApplyListVo();
		 sPLApplyListVo.setId(new BigDecimal(listid));
		 sPLApplyListVo.setAuditflag(auditflag);
		 sPLApplyListVo.setMngid(Long.parseLong(mngid));
		 sPLApplyListVo.setMngauditdate(mngauditdate);
		 sPLApplyListVo.setMngsuggestion(mngsuggestion);
		 
		 try{
		 sPLApplyListService.doUpdateApplyList(sPLApplyListVo);
		 resultMap.put("msg", "审核成功！");
		 }catch(Exception e){
			 resultMap.put("msg", "审核失败！");
		 }

		 return resultMap;
	 }
	 
	 /**
	  * 根据系统模块名称获取栏目信息
	  * @return
	  */
	 @RequestMapping(value="doDel")
	 @ResponseBody
	 public  Map<String, Object> doDel(HttpServletRequest request)
	 {
		 Map<String, Object> resultMap=new HashMap<String, Object>();  
		 Date mngauditdate = new Date();
			
		 String listid = request.getParameter("listid");
		 String auditflag = request.getParameter("auditflag");
		 SPLApplyListVo  sPLApplyListVo = new SPLApplyListVo();
		 sPLApplyListVo.setId(new BigDecimal(listid));
		 sPLApplyListVo.setDelstatus("0");
		 sPLApplyListVo.setAuditflag(auditflag);
		 try{
		 sPLApplyListService.doDelApplyList(sPLApplyListVo);
		 resultMap.put("msg", "删除成功！");
		 }catch(Exception e){
			 resultMap.put("msg", "删除失败！");
		 }
		 resultMap.put("total", 1);
		 return resultMap;
	 }
	
	 /**
	  * 获取部门下拉列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getDeptComboboxByCond")
	 @ResponseBody
	 public  List<HashMap<String, String>> getDeptComboboxByCond(HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 DeptVo deptVo = new DeptVo();
		 deptVo.setDeptlevel((short)2);
		 boxList=deptVoService.getDeptComboboxByCond(deptVo);
		 return boxList;
	 }
}
