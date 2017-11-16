/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.perform;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.PubService;
import com.ztel.app.service.perform.DeptmonthLineService;
import com.ztel.app.service.perform.DeptmonthsumService;
import com.ztel.app.service.perform.UserlevelService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.perform.DeptmonthLineVo;
import com.ztel.app.vo.perform.DeptmonthLineVoTmp;
import com.ztel.app.vo.perform.DeptmonthsumVo;
import com.ztel.app.vo.perform.UserlevelVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author lcf
 * @since 2017年2月26日
 * 绩效考核
 */
@Controller
@RequestMapping("/perform/deptmonthsum")
public class DeptmonthsumCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(DeptmonthsumCtrl.class);
	
	@Autowired
	private DeptmonthsumService deptmonthsumService = null;
	
	@Autowired
	private DeptmonthLineService deptmonthLineService = null;
	
	@Autowired
	private UserlevelService userlevelService = null;
	
	@Autowired
	private PubService pubService = null;


	@Autowired
	private OperationlogService operationlogService = null;
	
	/**
	 * 绩效考核
	 * @param request
	 * @return
	 */
	@RequestMapping("todeptmonthsum")
	public String index(HttpServletRequest request) {
		//String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常)
		//request.setAttribute("damagedtype", damagedtype);
		return "/perform/v_deptmonthsum";
	}
	
	/**
	 * 绩效考核弹出新增窗口
	 * @param request
	 * @return
	 */
	@RequestMapping("todeptmonthsumadd")
	public String todeptmonthsumadd(HttpServletRequest request) {
		//String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常)
		//request.setAttribute("damagedtype", damagedtype);
		return "/perform/v_deptmonthsumadd";
	}
	
	/**
	 * 绩效考核弹出修改窗口
	 * @param request
	 * @return
	 */
	@RequestMapping("todeptmonthsumedit")
	public String todeptmonthsumedit(HttpServletRequest request) {
		String obid = request.getParameter("obid");//主表id
		request.setAttribute("obid", obid);
		return "/perform/v_deptmonthsumedit";
	}
	
	/**
	 * 部长评(它评)
	 * @param request
	 * @return
	 */
	@RequestMapping("todeptmonthsumauditone")
	public String todeptmonthsumauditone(HttpServletRequest request) {
		String obid = request.getParameter("obid");//主表id
		request.setAttribute("obid", obid);
		return "/perform/v_deptmonthsumauditone";
	}
	
	/**
	 * 总经理评(总评)
	 * @param request
	 * @return
	 */
	@RequestMapping("todeptmonthsumaudittwo")
	public String todeptmonthsumaudittwo(HttpServletRequest request) {
		String obid = request.getParameter("obid");//主表id
		request.setAttribute("obid", obid);
		return "/perform/v_deptmonthsumaudittwo";
	}
	
	/**
	 * 部长考核(部长自评)
	 * @param request
	 * @return
	 */
	@RequestMapping("todeptmonthsumdeptmng")
	public String todeptmonthsumdeptmng(HttpServletRequest request) {
		//String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常)
		//request.setAttribute("damagedtype", damagedtype);
		return "/perform/v_deptmonthsumdeptmng";
	}

	/**
	 * 部长考核弹出新增窗口
	 * @param request
	 * @return
	 */
	@RequestMapping("todeptmonthsumdeptmngadd")
	public String todeptmonthsumdeptmngadd(HttpServletRequest request) {
		//String damagedtype = request.getParameter("damagedtype");//破损类别(10：来烟破损，20：称重异常)
		//request.setAttribute("damagedtype", damagedtype);
		return "/perform/v_deptmonthsumdeptmngadd";
	}
	
	/**
	 * 部长考核弹出修改窗口
	 * @param request
	 * @return
	 */
	@RequestMapping("todeptmonthsumdeptmngedit")
	public String todeptmonthsumdeptmngedit(HttpServletRequest request) {
		String obid = request.getParameter("obid");//主表id
		request.setAttribute("obid", obid);
		return "/perform/v_deptmonthsumdeptmngedit";
	}
	
	/**
	 * 部长考核总经理评(总评)
	 * @param request
	 * @return
	 */
	@RequestMapping("todeptmonthsumdeptmngaudittwo")
	public String todeptmonthsumdeptmngaudittwo(HttpServletRequest request) {
		String obid = request.getParameter("obid");//主表id
		request.setAttribute("obid", obid);
		return "/perform/v_deptmonthsumdeptmngaudittwo";
	}
	
	/**
	 * 查看本人或者所管辖人员的考评
	 * @param deptmonthsumVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getDeptmonthsumPageList")
	 @ResponseBody
	 public Map<String, Object> getDeptmonthsumPageList(DeptmonthsumVo deptmonthsumVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 Long userid = 0L;
		 int deptid = 0;
		 String username = "";
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
			 deptid = userVo.getDeptid();
		 }
		 String useridstr = userid+",";
		 List<UserlevelVo> userlevelVoList = userlevelService.getUserlevelList(userid);//获取所管辖人员的id
		 if(userlevelVoList!=null&&userlevelVoList.size()>0){
			 for(int i=0;i<userlevelVoList.size();i++){
				 UserlevelVo userlevelVo =userlevelVoList.get(i);
				 useridstr = useridstr + userlevelVo.getUserid()+",";
			 }
		 }
		 useridstr ="("+ useridstr.substring(0,useridstr.length()-1)+")";
		 deptmonthsumVo.setUseridstr(useridstr);
		// String keyword = transverseAssessVo.getKeyword();
		 String searchtime = request.getParameter("searchtime");
		 if(searchtime==null)searchtime=DateUtil.getyyyy_mm()+"-01";
		 //if(searchtime==null)searchtime="2017-02-01";
		 String searchtime2 = request.getParameter("searchtime2");
		 if(searchtime2==null)searchtime2=DateUtil.getyyyy_mm_dd();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 if(searchtime!=null&&!searchtime.equals("")){
				Date searchtimeD = sdf.parse(searchtime);
				Date searchtime2D = sdf.parse(searchtime2);
				deptmonthsumVo.setBegintime(searchtimeD);
				deptmonthsumVo.setEndtime(searchtime2D);
		 }
		 String ctype = request.getParameter("ctype");
		 if(ctype!=null&&!ctype.equals(""))deptmonthsumVo.setCtype(new Short(ctype));
		Pagination<?> page = this.getPagination(request);
		if (deptmonthsumVo!=null) {
			 page.setParam(deptmonthsumVo);
		}
		List<DeptmonthsumVo> deptmonthsumVoList = new ArrayList<DeptmonthsumVo>();
		deptmonthsumVoList = deptmonthsumService.selectDeptmonthsumPageList(page);
		
		 result.put("rows",deptmonthsumVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	
	 /**
	  * 新增绩效考核
	  * @return
	  */
	 @RequestMapping(value="doSave")
	 @ResponseBody
	 public  Map<String, Object> doSave(HttpServletRequest request) throws Exception
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String scoremonth_new = request.getParameter("scoremonth_new");//考核日期
		 if(scoremonth_new==null||scoremonth_new.equals(""))scoremonth_new=DateUtil.getyyyy_mm()+"-01";
		 if(scoremonth_new.length()==10)scoremonth_new = scoremonth_new.substring(0, 7);
		 
		 String matters_new = request.getParameter("matters_new");//需注意工作事项
		 String unfinished_new = request.getParameter("unfinished_new");//未完成事项说明
		 String ctype = request.getParameter("ctype");//10：副部长考核  20：部长考核
		 String scoresum  = request.getParameter("scoresum");//自评后的合计
		 if(scoresum==null)scoresum="0";
		 String tmpscore  = request.getParameter("tmpscore");//未加入考核的分数 
		 if(tmpscore==null)tmpscore="0";

		 
		 String obid = request.getParameter("obid");//id
		 Long id = 0L;
		 if(obid!=null&&!obid.equals("0")&&!obid.equals("")){//第二次插入
			 id = new Long(obid);
		 }
		 else
			 {//首次插入
			 id = pubService.getSequence("S_PERFORM_DEPTMONTHSUM");
			 }
		 
		 Long userid = 0L;
		 int deptid = 0;
		 String username = "";
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/perform/deptmonthsum/doSave", "经理考核/部长考核", "新增", "");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
			 deptid = userVo.getDeptid();
			 username = userVo.getUsername();
		 }
		 //考核主表
		 DeptmonthsumVo deptmonthsumVo = new DeptmonthsumVo();
		 deptmonthsumVo.setId(id);
		 deptmonthsumVo.setScoremonth(scoremonth_new);//得分年月
		 deptmonthsumVo.setCreatetime(new Date());
		 deptmonthsumVo.setSelfscore(new BigDecimal(scoresum));
		 deptmonthsumVo.setSelfid(userid);
		 deptmonthsumVo.setCtype(new Short(ctype));//10：副部长考核  20：部长考核
		 deptmonthsumVo.setStatus(new Short("10"));//10,自评未完成  20,自评完成   30:部长评分完成  40,总经理评分完成
		 deptmonthsumVo.setBelongdept(new Long((long)deptid));
		 deptmonthsumVo.setUnfinished(unfinished_new);
		 deptmonthsumVo.setMatter(matters_new);
		 
		//因为前台传值的局限性，要不传值为未修改前的rows数据，要不只传仅修改的数据，所以需要特殊处理合并
		List<DeptmonthLineVo> addList = new ArrayList<DeptmonthLineVo>();
		 List<DeptmonthLineVo> deptmonthLineVoList = new ArrayList<DeptmonthLineVo>();
		 String updated  = request.getParameter("updaterows");
		 String allrows  = request.getParameter("allrows");
		 if(updated != null && allrows != null){
			  	    //把json字符串转换成对象
			  	    List<DeptmonthLineVoTmp> listUpdated = JSON.parseArray(updated, DeptmonthLineVoTmp.class);
			  	  List<DeptmonthLineVoTmp> listAllrows = JSON.parseArray(allrows, DeptmonthLineVoTmp.class);
			  	  for(int i=0;i<listAllrows.size();i++){
			  		DeptmonthLineVoTmp DeptmonthaddVo_all = listAllrows.get(i);
			  		if(listUpdated!=null&&listUpdated.size()>0){
			  			for(int j=0;j<listUpdated.size();j++){
				  			DeptmonthLineVoTmp DeptmonthaddVo_update = listUpdated.get(j);
				  			if(DeptmonthaddVo_all.getId()==DeptmonthaddVo_update.getId())
				  				DeptmonthaddVo_all=DeptmonthaddVo_update;
				  		}
			  		}
				  		
				  		//addList.add(DeptmonthaddVo_all);
				  		DeptmonthLineVo deptmonthLineVo1 = new DeptmonthLineVo();
				  		String id1=DeptmonthaddVo_all.getId();
				  		String finishnote1 = DeptmonthaddVo_all.getFinishnote();
				  		String note1 = DeptmonthaddVo_all.getNote();
				  		String selfscore1 = DeptmonthaddVo_all.getSelfscore();
				  		String ctype1 = DeptmonthaddVo_all.getCtype();
				  		String evalitemid1 = DeptmonthaddVo_all.getId();
				  		String weight1 = DeptmonthaddVo_all.getWeight();
				  		deptmonthLineVo1.setId(new Long(id1));
				  		deptmonthLineVo1.setFinishnote(finishnote1);
				  		deptmonthLineVo1.setNote(note1);
				  		deptmonthLineVo1.setSelfscore(new BigDecimal(selfscore1));
				  		deptmonthLineVo1.setCtype(new BigDecimal(ctype1));
				  		deptmonthLineVo1.setEvalitemid(new Long(evalitemid1));
				  		deptmonthLineVo1.setWeight(new BigDecimal(weight1));
				  		addList.add(deptmonthLineVo1);
			  	  }
			  	}
	  	    if(addList!=null&&addList.size()>0){
	  	    	for(int i=0;i<addList.size();i++){
	  	    		DeptmonthLineVo deptmonthaddVo = addList.get(i);
	  	    		DeptmonthLineVo deptmonthLineVo = new DeptmonthLineVo();
	  	    		deptmonthLineVo.setFid(id);
	  	    		deptmonthLineVo.setCtype(deptmonthaddVo.getCtype());
	  	    		deptmonthLineVo.setEvalitemid(deptmonthaddVo.getId().longValue());
	  	    		deptmonthLineVo.setFinishnote(deptmonthaddVo.getFinishnote());
	  	    		deptmonthLineVo.setSelfscore(deptmonthaddVo.getSelfscore());
	  	    		deptmonthLineVo.setNote(deptmonthaddVo.getNote());
	  	    		deptmonthLineVo.setWeight(deptmonthaddVo.getWeight());
	  	    		deptmonthLineVo.setTmpscore(new BigDecimal(tmpscore));
	  	    		deptmonthLineVoList.add(deptmonthLineVo);
	  	    	}
	  	    }

		 try{
			// transverseAssessService.insertTransverseAssess(transverseAssessVo);
			 deptmonthsumService.insertDeptmonthsum(deptmonthsumVo, deptmonthLineVoList,obid);
		 map.put("msg", "新增成功");
		 map.put("obid", id+"");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 map.put("obid", "0");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 修改或审核时根据id取主考核表信息
	  * @return
	  */
	 @RequestMapping(value="getDeptmonthsumByid")
	 @ResponseBody
	 public  DeptmonthsumVo getDeptmonthsumByid(HttpServletRequest request) throws Exception
	 {
		 DeptmonthsumVo result = new DeptmonthsumVo();
		 String obid = request.getParameter("obid");//主表id
		 
		 if(obid==null || obid.equals("")){obid="0";}
		 DeptmonthsumVo deptmonthsumVo = new DeptmonthsumVo();
		 try{
			 deptmonthsumVo.setId(new Long(obid));
			 result =  deptmonthsumService.selectDeptmonthsumByCond(deptmonthsumVo);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return result;
	 }
	 
	 /**
	  * 修改或审核时根据id取主考核表id获取明细信息
	  * @return
	  */
	 @RequestMapping(value="getDeptmonthListByid")
	 @ResponseBody
	 public  List<DeptmonthLineVo> getDeptmonthListByid(HttpServletRequest request) throws Exception
	 {
		 List<DeptmonthLineVo> resultList = new ArrayList<DeptmonthLineVo>();
		 String obid = request.getParameter("obid");//主表id
		 
		 if(obid==null || obid.equals("")){obid="0";}
		 DeptmonthLineVo deptmonthLineVo = new DeptmonthLineVo();
		 try{
			 deptmonthLineVo.setFid(new Long(obid));
			 resultList = deptmonthLineService.selectDeptmonthLineByCond(deptmonthLineVo);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return resultList;
	 }
	 
	 
	 /**
	  * 修改绩效考核
	  * @return
	  */
	 @RequestMapping(value="doEdit")
	 @ResponseBody
	 public  Map<String, Object> doEdit(HttpServletRequest request) throws Exception
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String obid = request.getParameter("obid");//主表id
		 if(obid==null || obid.equals("")){obid="0";}
		 
		 String matters_new = request.getParameter("matters_new");//需注意工作事项
		 String unfinished_new = request.getParameter("unfinished_new");//未完成事项说明
		 
		 String scoresum  = request.getParameter("scoresum");//自评后的合计
		 if(scoresum==null)scoresum="0";

		Long id =new Long(obid);
		 //考核主表
		 DeptmonthsumVo deptmonthsumVo = new DeptmonthsumVo();
		 deptmonthsumVo.setId(id);
		 deptmonthsumVo.setSelfscore(new BigDecimal(scoresum));
		 deptmonthsumVo.setUnfinished(unfinished_new);
		 deptmonthsumVo.setMatter(matters_new);
		 
		//因为前台传值的局限性，要不传值为未修改前的rows数据，要不只传仅修改的数据，所以需要特殊处理合并
		List<DeptmonthLineVo> addList = new ArrayList<DeptmonthLineVo>();
		 List<DeptmonthLineVo> deptmonthLineVoList = new ArrayList<DeptmonthLineVo>();
		 String updated  = request.getParameter("updaterows");
		 String allrows  = request.getParameter("allrows");
		 if(updated != null && allrows != null){
			  	    //把json字符串转换成对象
			  	    List<DeptmonthLineVoTmp> listUpdated = JSON.parseArray(updated, DeptmonthLineVoTmp.class);
			  	  List<DeptmonthLineVoTmp> listAllrows = JSON.parseArray(allrows, DeptmonthLineVoTmp.class);
			  	  for(int i=0;i<listAllrows.size();i++){//针对上面json串转换总是抛出BigDecimal转换异常，所以先用字符变量接受，再进行转换
			  		DeptmonthLineVoTmp DeptmonthaddVo_all = listAllrows.get(i);
				  		for(int j=0;j<listUpdated.size();j++){
				  			DeptmonthLineVoTmp DeptmonthaddVo_update = listUpdated.get(j);
				  			if(DeptmonthaddVo_all.getId()==DeptmonthaddVo_update.getId())
				  				DeptmonthaddVo_all=DeptmonthaddVo_update;
				  		}
				  		//addList.add(DeptmonthaddVo_all);
				  		DeptmonthLineVo deptmonthLineVo1 = new DeptmonthLineVo();
				  		String id1=DeptmonthaddVo_all.getId();
				  		String finishnote1 = DeptmonthaddVo_all.getFinishnote();
				  		String note1 = DeptmonthaddVo_all.getNote();
				  		String selfscore1 = DeptmonthaddVo_all.getSelfscore();
				  		deptmonthLineVo1.setId(new Long(id1));
				  		deptmonthLineVo1.setFinishnote(finishnote1);
				  		deptmonthLineVo1.setNote(note1);
				  		deptmonthLineVo1.setSelfscore(new BigDecimal(selfscore1));
				  		addList.add(deptmonthLineVo1);
			  	  }
			  	}
	  	    if(addList!=null&&addList.size()>0){
	  	    	for(int i=0;i<addList.size();i++){
	  	    		DeptmonthLineVo deptmonthaddVo = addList.get(i);
	  	    		DeptmonthLineVo deptmonthLineVo = new DeptmonthLineVo();
	  	    		deptmonthLineVo.setId(deptmonthaddVo.getId());
	  	    		//deptmonthLineVo.setFid(id);
	  	    		//deptmonthLineVo.setCtype(deptmonthLineVo.getCtype());
	  	    		//deptmonthLineVo.setEvalitemid(deptmonthaddVo.getId().longValue());
	  	    		deptmonthLineVo.setFinishnote(deptmonthaddVo.getFinishnote());
	  	    		deptmonthLineVo.setSelfscore(deptmonthaddVo.getSelfscore());
	  	    		deptmonthLineVo.setNote(deptmonthaddVo.getNote());
	  	    		//deptmonthLineVo.setWeight(deptmonthaddVo.getWeight());
	  	    		deptmonthLineVoList.add(deptmonthLineVo);
	  	    	}
	  	    }

		 try{
			// transverseAssessService.insertTransverseAssess(transverseAssessVo);
			 deptmonthsumService.updateDeptmonthsum(deptmonthsumVo, deptmonthLineVoList);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/perform/deptmonthsum/doEdit", "经理考核/部长考核", "修改", "");
		 map.put("msg", "修改成功");
		 map.put("obid", id+"");
		 }catch(Exception e){
			 map.put("msg", "修改失败");
			 map.put("obid", "0");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 提交 
	  * @return
	  */
	 @RequestMapping(value="doPost")
	 @ResponseBody
	 public  Map<String, Object> doPost(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 String obid = request.getParameter("obid");//主表id
		 if(obid==null || obid.equals("")){obid="0";}
		Long id =new Long(obid);
		 //考核主表
		 DeptmonthsumVo deptmonthsumVo = new DeptmonthsumVo();
		 deptmonthsumVo.setId(id);
		 deptmonthsumVo.setStatus(new Short("20"));
		 try{
			 deptmonthsumService.updateDeptmonthsumByCond(deptmonthsumVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/perform/deptmonthsum/doPost", "经理考核/部长考核", "提交", "");
		 map.put("msg", "提交成功");
		 }catch(Exception e){
			 map.put("msg", "提交失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 绩效考核它评
	  * @return
	  */
	 @RequestMapping(value="doAuditOne")
	 @ResponseBody
	 public  Map<String, Object> doAuditOne(HttpServletRequest request) throws Exception
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String obid = request.getParameter("obid");//主表id
		 if(obid==null || obid.equals("")){obid="0";}
		 
		 
		 String scoresum  = request.getParameter("scoresum");//它评后的合计
		 if(scoresum==null)scoresum="0";

		 String deptmngrating  = request.getParameter("deptmngrating");//部长评级
		 if(deptmngrating==null||deptmngrating.equals(""))deptmngrating="A";
		 
		 Long userid = 0L;
		 int deptid = 0;
		 String username = "";
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/perform/deptmonthsum/doAuditOne", "经理考核", "它评", "");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
			 deptid = userVo.getDeptid();
			 username = userVo.getUsername();
		 }
		 
		Long id =new Long(obid);
		 //考核主表
		 DeptmonthsumVo deptmonthsumVo = new DeptmonthsumVo();
		 deptmonthsumVo.setId(id);
		 deptmonthsumVo.setDeptmngscore(new BigDecimal(scoresum));
		 deptmonthsumVo.setDeptmngid(userid);
		 deptmonthsumVo.setDeptmngrating(deptmngrating);//部长评级
		 deptmonthsumVo.setStatus(new Short("30"));//部长评分完成
		 
		//因为前台传值的局限性，要不传值为未修改前的rows数据，要不只传仅修改的数据，所以需要特殊处理合并
		List<DeptmonthLineVo> addList = new ArrayList<DeptmonthLineVo>();
		 List<DeptmonthLineVo> deptmonthLineVoList = new ArrayList<DeptmonthLineVo>();
		 String updated  = request.getParameter("updaterows");
		 String allrows  = request.getParameter("allrows");
		 if(updated != null && allrows != null){
			  	    //把json字符串转换成对象
			  	    List<DeptmonthLineVoTmp> listUpdated = JSON.parseArray(updated, DeptmonthLineVoTmp.class);
			  	  List<DeptmonthLineVoTmp> listAllrows = JSON.parseArray(allrows, DeptmonthLineVoTmp.class);
			  	  for(int i=0;i<listAllrows.size();i++){//针对上面json串转换总是抛出BigDecimal转换异常，所以先用字符变量接受，再进行转换
			  		DeptmonthLineVoTmp DeptmonthaddVo_all = listAllrows.get(i);
			  		if(listUpdated!=null&&listUpdated.size()>0){
			  			for(int j=0;j<listUpdated.size();j++){
				  			DeptmonthLineVoTmp DeptmonthaddVo_update = listUpdated.get(j);
				  			if(DeptmonthaddVo_all.getId()==DeptmonthaddVo_update.getId())
				  				DeptmonthaddVo_all=DeptmonthaddVo_update;
				  		}
			  		}
				  		//addList.add(DeptmonthaddVo_all);
				  		DeptmonthLineVo deptmonthLineVo1 = new DeptmonthLineVo();
				  		String id1=DeptmonthaddVo_all.getId();
				  		String finishnote1 = DeptmonthaddVo_all.getFinishnote();
				  		String note1 = DeptmonthaddVo_all.getNote();
				  		String selfscore1 = DeptmonthaddVo_all.getSelfscore();
				  		String otherscore1 = DeptmonthaddVo_all.getOtherscore();
				  		deptmonthLineVo1.setId(new Long(id1));
				  		//deptmonthLineVo1.setFinishnote(finishnote1);
				  		deptmonthLineVo1.setNote(note1);
				  		deptmonthLineVo1.setOtherscore(new BigDecimal(otherscore1));
				  		addList.add(deptmonthLineVo1);
			  	  }
			  	}
	  	    if(addList!=null&&addList.size()>0){
	  	    	for(int i=0;i<addList.size();i++){
	  	    		DeptmonthLineVo deptmonthaddVo = addList.get(i);
	  	    		DeptmonthLineVo deptmonthLineVo = new DeptmonthLineVo();
	  	    		deptmonthLineVo.setId(deptmonthaddVo.getId());
	  	    		//deptmonthLineVo.setFid(id);
	  	    		//deptmonthLineVo.setCtype(deptmonthLineVo.getCtype());
	  	    		//deptmonthLineVo.setEvalitemid(deptmonthaddVo.getId().longValue());
	  	    		//deptmonthLineVo.setFinishnote(deptmonthaddVo.getFinishnote());
	  	    		deptmonthLineVo.setOtherscore(deptmonthaddVo.getOtherscore());
	  	    		deptmonthLineVo.setNote(deptmonthaddVo.getNote());
	  	    		//deptmonthLineVo.setWeight(deptmonthaddVo.getWeight());
	  	    		deptmonthLineVoList.add(deptmonthLineVo);
	  	    	}
	  	    }

		 try{
			// transverseAssessService.insertTransverseAssess(transverseAssessVo);
			 deptmonthsumService.updateDeptmonthsum(deptmonthsumVo, deptmonthLineVoList);
			 
		 map.put("msg", "新增成功");
		 map.put("obid", id+"");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 map.put("obid", "0");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 绩效考核它评
	  * @return
	  */
	 @RequestMapping(value="doAuditTwo")
	 @ResponseBody
	 public  Map<String, Object> doAuditTwo(HttpServletRequest request) throws Exception
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String obid = request.getParameter("obid");//主表id
		 if(obid==null || obid.equals("")){obid="0";}
		 

		 String genmngrating  = request.getParameter("genmngrating");//总经理评级
		 if(genmngrating==null||genmngrating.equals(""))genmngrating="A";
		 
		 Long userid = 0L;
		 int deptid = 0;
		 String username = "";
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/perform/deptmonthsum/doAuditTwo", "经理考核/部长考核", "总评", "");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
			 deptid = userVo.getDeptid();
			 username = userVo.getUsername();
		 }
		 
		Long id =new Long(obid);
		 //考核主表
		 DeptmonthsumVo deptmonthsumVo = new DeptmonthsumVo();
		 deptmonthsumVo.setId(id);
		 deptmonthsumVo.setGenmngid(new BigDecimal(userid));
		 deptmonthsumVo.setGenmngrating(genmngrating);//总经理评级
		 deptmonthsumVo.setGenmngdate(new Date());
		 deptmonthsumVo.setStatus(new Short("40"));
		 

		 try{
			// transverseAssessService.insertTransverseAssess(transverseAssessVo);
			 //deptmonthsumService.updateDeptmonthsum(deptmonthsumVo, deptmonthLineVoList);
			 deptmonthsumService.updateDeptmonthsumByCond(deptmonthsumVo);
		 map.put("msg", "新增成功");
		 map.put("obid", id+"");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 map.put("obid", "0");
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
			
		 String obid = request.getParameter("obid");//主表id
		 if(obid==null || obid.equals("")){obid="0";}

		 Long id =new Long(obid);
		 try{
			 deptmonthsumService.doDel(id);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/perform/deptmonthsum/doDel", "经理考核/部长考核", "删除", "");
		 resultMap.put("msg", "删除成功！");
		 }catch(Exception e){
			 resultMap.put("msg", "删除失败！");
		 }
		 resultMap.put("total", 1);
		 return resultMap;
	 }
	 

	
}
	
    
