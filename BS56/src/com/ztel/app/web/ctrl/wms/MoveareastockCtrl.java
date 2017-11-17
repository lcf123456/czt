/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.wms;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.PubService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.wms.ItemService;
import com.ztel.app.service.wms.MoveareastockLineService;
import com.ztel.app.service.wms.MoveareastockService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.MachinedamagedVo;
import com.ztel.app.vo.wms.MoveareastockLineVo;
import com.ztel.app.vo.wms.MoveareastockVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author Zeal
 * @since 2017年2月26日
 * 区间移库
 */
@Controller
@RequestMapping("/wms/moveareastock")
public class MoveareastockCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(MoveareastockCtrl.class);
	
	@Autowired
	private MoveareastockService moveareastockService = null;
	@Autowired
	private OperationlogService operationlogService = null;
	@Autowired
	private MoveareastockLineService MoveareastockLineService = null;
	
	@Autowired
	private PubService pubService = null;
	
	@Autowired
	private ItemService itemService = null;
	
	//@Autowired
	//private OutBoundLineService  inBoundLineService = null;
	
	/**
	 * 散烟区至分拣
	 * @param request
	 * @return
	 */
	@RequestMapping("tomovefromsytofj")
	public String tomovefromsytofj(HttpServletRequest request) {
		String movetype = request.getParameter("movetype");//移库类别 10：散烟区至分拣 20：散烟区至立库 30：通用移库
		request.setAttribute("movetype", movetype);
		request.setAttribute("areaid", Constant.storagearea_sy);
		request.setAttribute("targetareaid", Constant.storagearea_fj);
		return "/wms/v_movefromsytofj";
	}
	
	/**
	 * 散烟区至立库
	 * @param request
	 * @return
	 */
	@RequestMapping("tomovefromsytolk")
	public String tomovefromsytolk(HttpServletRequest request) {
		String movetype = request.getParameter("movetype");//移库类别 10：散烟区至分拣 20：散烟区至立库 30：通用移库
		request.setAttribute("movetype", movetype);
		request.setAttribute("areaid", Constant.storagearea_sy);
		request.setAttribute("targetareaid", Constant.storagearea_lk);
		return "/wms/v_movefromsytolk";
	}
	
	/**
	 * 通用移库
	 * @param request
	 * @return
	 */
	@RequestMapping("tomoveuniversal")
	public String tomoveuniversal(HttpServletRequest request) {
		String movetype = request.getParameter("movetype");//移库类别 10：散烟区至分拣 20：散烟区至立库 30：通用移库
		request.setAttribute("movetype", movetype);
		request.setAttribute("areaid", Constant.storagearea_sy);
		request.setAttribute("targetareaid", Constant.storagearea_lk);
		return "/wms/v_moveuniversal";
	}
	
	/**
	 * 获取移库列表-通用移库
	 * @param outBoundVo 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getMoveareastockPageList")
	 @ResponseBody
	 public Map<String, Object> getMoveareastockPageList(HttpServletRequest request) throws Exception{
		Pagination<?> page = this.getPagination(request);
		 Map<String, Object> map=new HashMap<String, Object>();   
		String movetype = request.getParameter("movetype");
		if(movetype==null)movetype="10";
		
		String searchtime = request.getParameter("searchtime");
		String searchtime2 = request.getParameter("searchtime2");
		String keyword = request.getParameter("keyword");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		MoveareastockVo moveareastockVo = new MoveareastockVo();
		if(searchtime!=null&&!searchtime.equals("")){
			Date searchtimeD = sdf.parse(searchtime);
			Date searchtime2D = sdf.parse(searchtime2);
			moveareastockVo.setBegintime(searchtimeD);
			moveareastockVo.setEndtime(searchtime2D);
		}
		
		if(keyword!=null&&!keyword.equals(""))moveareastockVo.setKeyword(keyword);
		
		moveareastockVo.setMovetype(movetype);;
			
			if (moveareastockVo!=null) {
				 page.setParam(moveareastockVo);
			}
		List<MoveareastockVo> moveareastockVoList = new ArrayList<MoveareastockVo>();
		moveareastockVoList = moveareastockService.selectMoveareastockPageList(page);
		 
		map.put("rows", moveareastockVoList);
		map.put("total",page.getTotalCount());  
		return map;
	}
	
	/**
	 * 检查移库的品牌数量是否合理：需要不大于库存数量
	 * @param request
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping(value="doCheckqty",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> doCheckqty(HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 String cigarettecode = request.getParameter("cigarettecode");//卷烟品牌
		 String qtynew = request.getParameter("qtynew");//调拨数量
		 String areaid = request.getParameter("areaid");//区域id
		 String cellno = request.getParameter("cellno");//分拣通道号
		 if(cellno==null)cellno="";

		 try {
			 BigDecimal actualqty = moveareastockService.doCheckqty(cigarettecode,areaid,cellno);//对应区域实际库存数量（=日清日结尾数+调拨数）
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/wms/moveareastock/doCheckqty", "通用移库", "录入", "");
			 map.put("msg", "成功");
			 map.put("actualqty", actualqty+"");
		} catch (Exception e) {
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 
		 return map;
	 }
	
	 /**
	  * 移库保存-散烟区至分拣区录入
	  * @return
	  */
	 @RequestMapping(value="doSave")
	 @ResponseBody
	 public  Map<String, Object> doSave(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 try{
		 String cigarettecode = request.getParameter("cigarettecode");//卷烟编码
		 String cigarettename = request.getParameter("cigarettename");//卷烟名称
		 String troughnum = request.getParameter("troughnum_secondok");//通道号
		 String qty = request.getParameter("qtynew");//移库数量
		 

		 String jtsize = request.getParameter("jtsize");//件条比率
		 String barcode = request.getParameter("barcode");//件码
		 String obid = request.getParameter("obid");//前台传过来的id，用于判断第一次或第二次插入
		 String areaid = request.getParameter("areaid");//区域id
		 String targetareaid = request.getParameter("targetareaid");//区域id
		 String movetype = request.getParameter("movetype");
		 
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//		    Date outtimeD = sdf.parse(outtime);
		    
		 Long id = 0L;
		 if(obid!=null&&!obid.equals("0")){//第二次插入
			 id = new Long(obid);
		 }
		 else
			 {//首次插入
			 id = pubService.getSequence("S_WMS_MOVEAREASTOCK");
			 }
		 Long userid = 0L;
		 String username = "";
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/wms/moveareastock/doSave", "散烟区至分拣区/至立库", "录入", "");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
			 username = userVo.getUsername();
		 }
		
		 //条转件：
		 BigDecimal barqty = new BigDecimal(qty);
		 BigDecimal jtsizeDecimal = new BigDecimal(jtsize);
		 BigDecimal boxqty = (barqty.divide(jtsizeDecimal)).setScale(2,BigDecimal.ROUND_HALF_UP);
		 
		 //移库主表数据初始化
		 MoveareastockVo moveareastockVo = new MoveareastockVo();
		 moveareastockVo.setId(new BigDecimal(id));
		 moveareastockVo.setSourceareaid(new BigDecimal(areaid));
		 moveareastockVo.setTargetareaid(new BigDecimal(targetareaid));
		 moveareastockVo.setCreateuser(userid);
		 moveareastockVo.setCreateusername(username);
		 moveareastockVo.setMovetype(movetype);
		 moveareastockVo.setAuditflag(new BigDecimal("10"));
		 moveareastockVo.setBarqty(barqty);
		 moveareastockVo.setBoxqty(boxqty);
		 
		 //移库明细数据初始化
		 MoveareastockLineVo moveareastockLineVo = new MoveareastockLineVo();
		 moveareastockLineVo.setParentid(new BigDecimal(id));
		 moveareastockLineVo.setBarcode(barcode);
		 moveareastockLineVo.setCigarettecode(cigarettecode);
		 moveareastockLineVo.setCigarettename(cigarettename);
		 moveareastockLineVo.setBarqty(barqty);
		 moveareastockLineVo.setBoxqty(boxqty);
		 if(troughnum!=null&&!troughnum.equals(""))moveareastockLineVo.setTroughnum(troughnum);
		 

		 moveareastockService.insertMoveareastock(moveareastockVo,moveareastockLineVo,obid);
	
		 map.put("msg", "新增成功");
		 map.put("obid", id+"");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 通用移库保存
	  * @return
	  */
	 @RequestMapping(value="doSaveUniversal")
	 @ResponseBody
	 public  Map<String, Object> doSaveUniversal(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 try{
			 String obid = request.getParameter("obid");//前台传过来的id，用于判断第一次或第二次插入
			 String areaid = request.getParameter("areaid");//区域id
			 String targetareaid = request.getParameter("targetareaid");//区域id
			 String movetype = request.getParameter("movetype");
			 String jtsize = request.getParameter("jtsize");//件条比率
			 String barcode = request.getParameter("barcode");//件码
			 
			 //原区域为散烟或立库
		 String cigarettecode = request.getParameter("cigarettecode");//卷烟编码
		 String cigarettename = request.getParameter("cigarettename");//卷烟名称
		 String qty = request.getParameter("qtynew");//移库数量
		 

		 
		 //原区域为分拣或重力式货架
		 String cigarettecode_secondok = request.getParameter("cigarettecode_secondok");//卷烟编码
		 String cigarettename_secondok = request.getParameter("cigarettename_secondok");//卷烟名称
		 String troughnum = request.getParameter("troughnum_secondok");//通道号
		 String move_qty = request.getParameter("move_qty");//移库数量
		 
		 //目标区域为分拣或重力式货架
		 String cigarettecode_secondok2 = request.getParameter("cigarettecode_secondok2");//卷烟编码
		 String cigarettename_secondok2 = request.getParameter("cigarettename_secondok2");//卷烟名称
		 String troughnum_secondok2 = request.getParameter("troughnum_secondok2");//通道编号
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//		    Date outtimeD = sdf.parse(outtime);
		    
		 Long id = 0L;
		 if(obid!=null&&!obid.equals("0")){//第二次插入
			 id = new Long(obid);
		 }
		 else
			 {//首次插入
			 id = pubService.getSequence("S_WMS_MOVEAREASTOCK");
			 }
		 Long userid = 0L;
		 String username = "";
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/wms/moveareastock/doSaveUniversal", "通用移库", "录入", "");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
			 username = userVo.getUsername();
		 }
		 
		 if(areaid.equals(Constant.storagearea_fj)||areaid.equals(Constant.storagearea_zlshj)){//立库或重力式货架卷烟及移库数量信息
				cigarettecode = cigarettecode_secondok2;//卷烟编码
				cigarettename = cigarettename_secondok;//卷烟名称
				qty = move_qty;//移库数量
			}
		 
		//条转件：
		 BigDecimal barqty = new BigDecimal(qty);
		 BigDecimal jtsizeDecimal = new BigDecimal(jtsize);
		 BigDecimal boxqty = (barqty.divide(jtsizeDecimal)).setScale(2,BigDecimal.ROUND_HALF_UP);
		 
			if(areaid.equals(Constant.storagearea_lk)||areaid.equals(Constant.storagearea_zlshj)){//立库或重力式货架输入为件，需要转条
				boxqty = new BigDecimal(qty);
				barqty = boxqty.multiply(jtsizeDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			
		 
		 
		 //移库主表数据初始化
		 MoveareastockVo moveareastockVo = new MoveareastockVo();
		 moveareastockVo.setId(new BigDecimal(id));
		 moveareastockVo.setSourceareaid(new BigDecimal(areaid));
		 moveareastockVo.setTargetareaid(new BigDecimal(targetareaid));
		 moveareastockVo.setCreateuser(userid);
		 moveareastockVo.setCreateusername(username);
		 moveareastockVo.setMovetype(movetype);
		 moveareastockVo.setAuditflag(new BigDecimal("10"));
		 moveareastockVo.setBarqty(barqty);
		 moveareastockVo.setBoxqty(boxqty);
		 
		 //移库明细数据初始化
		 MoveareastockLineVo moveareastockLineVo = new MoveareastockLineVo();
		 moveareastockLineVo.setParentid(new BigDecimal(id));
		 moveareastockLineVo.setBarcode(barcode);
		 moveareastockLineVo.setCigarettecode(cigarettecode);
		 moveareastockLineVo.setCigarettename(cigarettename);
		 moveareastockLineVo.setBarqty(barqty);
		 moveareastockLineVo.setBoxqty(boxqty);
		 if(troughnum!=null&&!troughnum.equals(""))moveareastockLineVo.setTroughnum(troughnum);
		 if(troughnum_secondok2!=null&&!troughnum_secondok2.equals(""))moveareastockLineVo.setTargettroughnum(troughnum_secondok2);
		 
		 
		 moveareastockService.insertMoveareastock(moveareastockVo,moveareastockLineVo,obid);
		 
		 map.put("msg", "新增成功");
		 map.put("obid", id+"");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
		 * 获取移库明细列表
		 * @param outBoundVo 
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="getMoveareastockLineList")
		 @ResponseBody
		 public List<MoveareastockLineVo>  getMoveareastockLineList(HttpServletRequest request) throws Exception{
			 List<MoveareastockLineVo> result=new ArrayList<MoveareastockLineVo>(); 
			 String parentid = request.getParameter("parentid");
			 result = MoveareastockLineService.getMoveareastockLineListByFid(new BigDecimal(parentid));
			return result;
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
			 String operationType = request.getParameter("operationType");//操作类型，散烟区到分拣、散烟区到立库为10 ，通用移库为20
			 if(operationType==null)operationType="10";
			 String checkflag = request.getParameter("checkflag");
			 String id = request.getParameter("id");//破损主表id
			 String checkdescribe = request.getParameter("checkdescribe");
			 String movetype = request.getParameter("movetype");
			 
			 MoveareastockVo moveareastockVo = new MoveareastockVo();
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/wms/moveareastock/doAudit", "移库", "收货/出库", "");
			 if(userVo!=null&&userVo.getUsername().trim().length()>0){
				 if(checkflag.equals("20"))
				 {
					 moveareastockVo.setOutcheckuser(userVo.getId());
					 moveareastockVo.setOutcheckusername(userVo.getUsername()); 
					 moveareastockVo.setOutchecktime(new Date());
				 }else
				 {
					 moveareastockVo.setReceivecheckuser(userVo.getId());
					 moveareastockVo.setReceivecheckusername(userVo.getUsername());
					 moveareastockVo.setReceivechecktime(new Date());
				 }
				 
			 }
			 moveareastockVo.setId(new BigDecimal(id));
			 moveareastockVo.setAuditflag(new BigDecimal(checkflag));
			 moveareastockVo.setMovetype(movetype);

			 try{
				 if(operationType!=null&&operationType.equals("10")){
					 moveareastockService.doAudit(moveareastockVo);
				 }else{
					 moveareastockService.doAuditUniversal(moveareastockVo);
				 }
				 
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
				
			 String id = request.getParameter("id");
			 try{
				 moveareastockService.doDel(new BigDecimal(id));
				 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
				 operationlogService.insertLog(userVo, "/wms/moveareastock/doDel", "移库", "删除", "");
			 resultMap.put("msg", "删除成功！");
			 }catch(Exception e){
				 resultMap.put("msg", "删除失败！");
			 }
			 resultMap.put("total", 1);
			 return resultMap;
		 }
		 
}
