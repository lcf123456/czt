/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.wms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.PubService;
import com.ztel.app.service.wms.CigarettedamagedLineService;
import com.ztel.app.service.wms.CigarettedamagedService;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.CigarettedamagedLineVo;
import com.ztel.app.vo.wms.CigarettedamagedVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author Zeal
 * @since 2017年2月26日
 *入库单
 */
@Controller
@RequestMapping("/wms/cigarettedamaged")
public class CigarettedamagedCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(CigarettedamagedCtrl.class);
	
	@Autowired
	private CigarettedamagedService cigarettedamagedService = null;
	
	@Autowired
	private CigarettedamagedLineService  cigarettedamagedLineService = null;
	
	@Autowired
	private PubService pubService = null;
	
	@RequestMapping("toCigarettedamaged")
	public String index(HttpServletRequest request) {
		String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常  30：抽检)
		request.setAttribute("damagedtype", damagedtype);
		return "/wms/v_cigarettedamaged";
	}
	
	
	@RequestMapping("toCigaretteabnormal")
	public String toCigaretteabnormal(HttpServletRequest request) {
		String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常  30：抽检)
		request.setAttribute("damagedtype", damagedtype);
		return "/wms/v_cigaretteabnormal";
	}
	
	@RequestMapping("toCigaretteinspection")
	public String toCigaretteinspection(HttpServletRequest request) {
		String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常  30：抽检)
		request.setAttribute("damagedtype", damagedtype);
		return "/wms/v_cigaretteinspection";
	}
	
	@RequestMapping(value="getCigarettedamagedPageList")
	 @ResponseBody
	 public Map<String, Object> getCigarettedamagedPageList(CigarettedamagedVo cigarettedamagedVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 String keyword = cigarettedamagedVo.getKeyword();
		 //判断keyword是否是数字
		 if(StringUtils.isNumeric(keyword)){//是数字
			 cigarettedamagedVo.setInboundid(new BigDecimal(keyword));
			 cigarettedamagedVo.setKeyword("");
		 }
		 String damagedtype =request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常  30：抽检)
		 if(damagedtype==null||damagedtype.equals(""))damagedtype="10";
			 cigarettedamagedVo.setDamagedtype(new BigDecimal(damagedtype));
		Pagination<?> page = this.getPagination(request);
		if (cigarettedamagedVo!=null) {
			 page.setParam(cigarettedamagedVo);
		}
		List<CigarettedamagedVo> cigarettedamagedVoList = new ArrayList<CigarettedamagedVo>();
		cigarettedamagedVoList = cigarettedamagedService.selectCigarettedamagedPageList(page);
		
		 result.put("rows",cigarettedamagedVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	
	@RequestMapping(value="getCigarettedamagedLineList")
	 @ResponseBody
	 public List<CigarettedamagedLineVo> getCigarettedamagedLineList(HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 String inboundid = request.getParameter("inboundid");
		 CigarettedamagedLineVo cigarettedamagedLineVo = new CigarettedamagedLineVo();
		 cigarettedamagedLineVo.setInboundid(new BigDecimal(inboundid));
	
		List<CigarettedamagedLineVo> cigarettedamagedLineVoList = new ArrayList<CigarettedamagedLineVo>();
		cigarettedamagedLineVoList = cigarettedamagedLineService.selectListByCond(cigarettedamagedLineVo);
		
		return cigarettedamagedLineVoList;
	}
	
	 /**
	  * 新增来烟破损
	  * @return
	  */
	 @RequestMapping(value="doSave")
	 @ResponseBody
	 public  Map<String, Object> doSave(CigarettedamagedLineVo cigarettedamagedLineVo,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String otherqty = request.getParameter("otherqty");//破损数量（件）
		 String actualqty = request.getParameter("actualqtynew");//实际破损数量(条数)
		 BigDecimal inboundid = cigarettedamagedLineVo.getInboundid();
		 String inbounddetailid = request.getParameter("inbounddetailid2");
		 String cigarettecode = request.getParameter("cigarettecode2");
		 String damagedtype = request.getParameter("damagedtype");
		 if(damagedtype==null || damagedtype.equals(""))damagedtype="10";
		 
		 String abid = request.getParameter("abid");//id
		 Long id = 0L;
		 if(abid!=null&&!abid.equals("0")){//第二次插入
			 id = new Long(abid);
		 }
		 else
			 {//首次插入
			 id = pubService.getSequence("S_WMS_CIGARETTEDAMAGED");
			 }
		 
		 CigarettedamagedVo cigarettedamagedVo = new CigarettedamagedVo();
		 	cigarettedamagedVo.setId(new BigDecimal(id));
			cigarettedamagedVo.setInboundid(inboundid);
	        cigarettedamagedVo.setQty(new BigDecimal(otherqty));
	        cigarettedamagedVo.setCreatetime(new Date());
	        cigarettedamagedVo.setActualqty(new BigDecimal(actualqty));
	        cigarettedamagedVo.setDamagedtype(new BigDecimal(damagedtype));
	        UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 if(userVo!=null&&userVo.getUsername().trim().length()>0){
				 cigarettedamagedVo.setCreateuser(userVo.getId());
			 }
			 
	        cigarettedamagedLineVo.setDamagedqty(new BigDecimal(otherqty));
	        cigarettedamagedLineVo.setInbounddetailid(new BigDecimal(inbounddetailid));
	        cigarettedamagedLineVo.setCigarettecode(cigarettecode);
	        cigarettedamagedLineVo.setActualqty(new BigDecimal(actualqty));
		 try{
			 cigarettedamagedService.insertCigarettedamaged(cigarettedamagedLineVo, cigarettedamagedVo);
		 
		 map.put("msg", "新增成功");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 新增称重异常    20171103抽检新增共用
	  * @return
	  */
	 @RequestMapping(value="doSaveabnormal")
	 @ResponseBody
	 public  Map<String, Object> doSaveabnormal(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String otherqty = request.getParameter("otherqty");//破损数量（件）
		 String actualqty = request.getParameter("actualqtynew");//实际破损数量(条数)
		 String cigarettenamenew = request.getParameter("cigarettenamenew");//卷烟名称
		 String cigarettecodenew = request.getParameter("cigarettecodenew");//卷烟编码
		 
		 String damagedtype = request.getParameter("damagedtype");
		 if(damagedtype==null || damagedtype.equals(""))damagedtype="20";
		 
		 String abid = request.getParameter("abid");//id
		 Long id = 0L;
		 if(abid!=null&&!abid.equals("0")){//第二次插入
			 id = new Long(abid);
		 }
		 else
			 {//首次插入
			 id = pubService.getSequence("S_WMS_CIGARETTEDAMAGED");
			 }
		 
		 CigarettedamagedVo cigarettedamagedVo = new CigarettedamagedVo();
		 	cigarettedamagedVo.setId(new BigDecimal(id));
		 	cigarettedamagedVo.setInboundid(new BigDecimal(id));
			//cigarettedamagedVo.setInboundid(inboundid);
	        cigarettedamagedVo.setQty(new BigDecimal(otherqty));
	        cigarettedamagedVo.setCreatetime(new Date());
	        cigarettedamagedVo.setActualqty(new BigDecimal(actualqty));
	        cigarettedamagedVo.setDamagedtype(new BigDecimal(damagedtype));
	        UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 if(userVo!=null&&userVo.getUsername().trim().length()>0){
				 cigarettedamagedVo.setCreateuser(userVo.getId());
			 }
			 
			CigarettedamagedLineVo cigarettedamagedLineVo  = new CigarettedamagedLineVo();
	        cigarettedamagedLineVo.setDamagedqty(new BigDecimal(otherqty));
	        cigarettedamagedLineVo.setInboundid(new BigDecimal(id));
	        cigarettedamagedLineVo.setInbounddetailid(new BigDecimal("0"));
	        cigarettedamagedLineVo.setCigarettename(cigarettenamenew);
	        cigarettedamagedLineVo.setCigarettecode(cigarettecodenew);
	        cigarettedamagedLineVo.setActualqty(new BigDecimal(actualqty));
	        cigarettedamagedLineVo.setBoxqty(new BigDecimal("0"));
		 try{
			 cigarettedamagedService.insertCigarettedamaged(cigarettedamagedLineVo, cigarettedamagedVo);
		 
		 map.put("msg", "新增成功");
		 map.put("abid", id+"");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 审核
	  * @return
	  */
	 @RequestMapping(value="doAudit")
	 @ResponseBody
	 public  Map<String, Object> doAudit(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String checkflag = request.getParameter("checkflag");
		 String inboundid = request.getParameter("inboundid");//入库单号
		 String checkdescribe = request.getParameter("checkdescribe");
		 
		 CigarettedamagedVo cigarettedamagedVo = new CigarettedamagedVo();
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 cigarettedamagedVo.setCheckuser(userVo.getId());
			 cigarettedamagedVo.setCheckusername(userVo.getUsername());
		 }
		 cigarettedamagedVo.setInboundid(new BigDecimal(inboundid));
		 cigarettedamagedVo.setCheckflag(checkflag);
		 cigarettedamagedVo.setCheckdescribe(checkdescribe);
		 cigarettedamagedVo.setChecktime(new Date());
		 try{
			 cigarettedamagedService.doAudit(cigarettedamagedVo);
		 
		 map.put("msg", "审核成功");
		 }catch(Exception e){
			 map.put("msg", "审核失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 称重异常审核   20171103抽检新增共用
	  * @return
	  */
	 @RequestMapping(value="doAuditabnormal")
	 @ResponseBody
	 public  Map<String, Object> doAuditabnormal(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String checkflag = request.getParameter("checkflag");
		 String inboundid = request.getParameter("inboundid");//入库单号
		 String checkdescribe = request.getParameter("checkdescribe");
		 String damagedtype = request.getParameter("damagedtype");
		 if(damagedtype==null||"".equals(damagedtype))damagedtype="20";
		 
		 CigarettedamagedVo cigarettedamagedVo = new CigarettedamagedVo();
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 cigarettedamagedVo.setCheckuser(userVo.getId());
			 cigarettedamagedVo.setCheckusername(userVo.getUsername());
		 }
		 cigarettedamagedVo.setInboundid(new BigDecimal(inboundid));
		 cigarettedamagedVo.setCheckflag(checkflag);
		 cigarettedamagedVo.setCheckdescribe(checkdescribe);
		 cigarettedamagedVo.setChecktime(new Date());
		 cigarettedamagedVo.setDamagedtype(new BigDecimal(damagedtype));
		 try{
			 cigarettedamagedService.doAuditabnormal(cigarettedamagedVo);
		 
		 map.put("msg", "审核成功");
		 }catch(Exception e){
			 map.put("msg", "审核失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	
	 /**
	  * 删除
	  * @return
	  */
	 @RequestMapping(value="doDel")
	 @ResponseBody
	 public  Map<String, Object> doDel(HttpServletRequest request)
	 {
		 Map<String, Object> resultMap=new HashMap<String, Object>();  
			
		 String inboundid = request.getParameter("inboundid");
//		 CigarettedamagedVo cigarettedamagedVo = new CigarettedamagedVo();
//		 cigarettedamagedVo.setInboundid(new BigDecimal(inboundid));
//		 cigarettedamagedVo.setDelstatus(new Short("0"));
		 try{
			 cigarettedamagedService.doDel(new BigDecimal(inboundid));
		 resultMap.put("msg", "删除成功！");
		 }catch(Exception e){
			 resultMap.put("msg", "删除失败！");
		 }
		 resultMap.put("total", 1);
		 return resultMap;
	 }
	 
	 /**
	  * 删除
	  * @return
	  */
	 @RequestMapping(value="doSelectByInboundid")
	 @ResponseBody
	 public  CigarettedamagedVo doSelectByInboundid(HttpServletRequest request)
	 {
		 CigarettedamagedVo cigarettedamagedVo = new  CigarettedamagedVo();
		 String inboundid = request.getParameter("inboundid");
		 try{
			 cigarettedamagedVo =  cigarettedamagedService.selectByInboundid(new BigDecimal(inboundid));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return cigarettedamagedVo;
	 }
}
	
    
