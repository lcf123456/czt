package com.ztel.app.web.ctrl.gis;

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

import com.ztel.app.service.gis.GroupinfoVoService;
import com.ztel.app.service.gis.TruckseqVoService;
import com.ztel.app.vo.gis.TruckseqVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * 工业来烟
 * @author lcf
 *
 */
@Controller
@RequestMapping("/gis/truckseq")
public class TruckseqCtrl  extends BaseCtrl{
	private static Logger logger = LogManager.getLogger(CurrlocationCtrl.class);
	
	@Autowired 
	private TruckseqVoService truckseqVoService = null;
	
	@Autowired
	private GroupinfoVoService groupinfoVoService = null;
	
	/**
	 * 来烟排队
	 * @param request
	 * @return
	 */
	@RequestMapping("toTruckseq")
	public String toTruckseq(HttpServletRequest request) {
		String requesttype = request.getParameter("requesttype");//入园、入单、排队调整
		request.setAttribute("requesttype", requesttype);
		return "/gis/v_truckseq";
	}
	
	/**
	 * 今日装卸
	 * @param request
	 * @return
	 */
	@RequestMapping("toTruckseqtoday")
	public String toTruckseqtoday(HttpServletRequest request) {
		return "/gis/v_truckseqtoday";
	}
	
	/**
	 * 装卸效率
	 * @param request
	 * @return
	 */
	@RequestMapping("toEfficiency")
	public String toEfficiency(HttpServletRequest request) {
		return "/gis/v_truckseqefficiency";
	}
	
	/**
	 * 装卸效率
	 * @param request
	 * @return
	 */
	@RequestMapping("toGroupEfficiency")
	public String toGroupEfficiency(HttpServletRequest request) {
		return "/gis/v_truckseqgroupefficiency";
	}
	
	@RequestMapping(value="getTruckseqPageList")
	 @ResponseBody
	 public Map<String, Object> getTruckseqPageList(HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
//		 String keyword = cigarettedamagedVo.getKeyword();
		 String status = request.getParameter("status");
		 String keyword = request.getParameter("keyword");
		 if(status==null)status="2";//默认显示园内数据

		Pagination<?> page = this.getPagination(request);
		TruckseqVo truckseqVo = new TruckseqVo();
		if(!status.equals("")){
			truckseqVo.setStatus(new BigDecimal(status));
		}
		
		if(keyword!=null&&!keyword.equals(""))truckseqVo.setKeyword(keyword);
		if (truckseqVo!=null) {
			 page.setParam(truckseqVo);
		}
		List<TruckseqVo> truckseqVoList = new ArrayList<TruckseqVo>();
		truckseqVoList = truckseqVoService.getTruckseqPageList(page);
		
		 result.put("rows",truckseqVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	 
	/**
	 * 今日装卸
	 * @param request
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping(value="getTruckseqTodayPageList")
	 @ResponseBody
	 public Map<String, Object> getTruckseqTodayPageList(HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 

		Pagination<?> page = this.getPagination(request);
		TruckseqVo truckseqVo = new TruckseqVo();
		if (truckseqVo!=null) {
			 page.setParam(truckseqVo);
		}
		List<TruckseqVo> truckseqVoList = new ArrayList<TruckseqVo>();
		truckseqVoList = truckseqVoService.getTruckseqTodayPageList(page);
		
		 result.put("rows",truckseqVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	 
	 /**
		 * 今日装卸
		 * @param request
		 * @return
		 * @throws Exception
		 */
		 @RequestMapping(value="getTruckseqEfficiencyPageList")
		 @ResponseBody
		 public Map<String, Object> getTruckseqEfficiencyPageList(HttpServletRequest request) throws Exception{
			 Map<String, Object> result=new HashMap<String, Object>();  
			 

			Pagination<?> page = this.getPagination(request);
			
			String searchbegtime = request.getParameter("searchtime");//搜索开始时间
			String searchendtime = request.getParameter("searchtime2");//搜索结束时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			if(searchbegtime==null)searchbegtime=DateUtil.getyyyy_mm_01();
			if(searchendtime==null)searchendtime=DateUtil.getyyyy_mm_dd();
			
			TruckseqVo truckseqVo = new TruckseqVo();
			if(searchbegtime!=null && searchendtime != null){
				Date searchtimeD = sdf.parse(searchbegtime);
				Date searchtime2D = sdf.parse(searchendtime);
				truckseqVo.setSearchbegtime(searchtimeD);
				truckseqVo.setSearchendtime(searchtime2D);
			}
			if (truckseqVo!=null) {
				 page.setParam(truckseqVo);
			}
			List<TruckseqVo> truckseqVoList = new ArrayList<TruckseqVo>();
			truckseqVoList = truckseqVoService.getTruckseqEfficiencyPageList(page);
			
			 result.put("rows",truckseqVoList);  
			 result.put("total",page.getTotalCount());  
			 
			return result;
		}
	
	 /**
	  * 更新
	  * @return
	  */
	 @RequestMapping(value="doUpdate")
	 @ResponseBody
	 public  Map<String, Object> doUpdate(HttpServletRequest request)
	 {
		 Map<String, Object> resultMap=new HashMap<String, Object>();  
			
		 String opType = request.getParameter("opType");//操作类型
		 String id = request.getParameter("id");
		 
		 TruckseqVo truckseqVo = new TruckseqVo();
		 if(id!=null)
		 truckseqVo.setId(new BigDecimal(id));
		 
		 if(opType!=null&&opType.equals("enterZone")){//入园
			 truckseqVo.setStatus(new BigDecimal("10"));
			 truckseqVo.setArrivetime(new Date());
		 }
		 else if(opType!=null&&opType.equals("enterBill")){//交单enterQueue
			 String groupcode = request.getParameter("groupcode1");
			 truckseqVo.setGroupcode(groupcode);
			 truckseqVo.setStatus(new BigDecimal("30"));
			 truckseqVo.setBilltime(new Date());
		 }
		 else if(opType!=null&&opType.equals("enterQueue")){//排队
			 String seq = request.getParameter("seq");
			 if(seq==null)seq="10000";
			 truckseqVo.setSeq(new BigDecimal(seq));
		 }
		 else if(opType!=null&&opType.equals("outAudit")){//出园审核
			 String groupcode = request.getParameter("groupcode1");
			 truckseqVo.setGroupcode(groupcode);
			 truckseqVo.setStatus(new BigDecimal("55"));
		 }
		 else if(opType!=null&&opType.equals("outZone")){//出园
			 truckseqVo.setStatus(new BigDecimal("60"));
			 truckseqVo.setBacktime(new Date());
		 }

		 try{
			 truckseqVoService.updateTruckseqByPrimarykey(truckseqVo);
			 
			 resultMap.put("msg", "成功！");
		 }catch(Exception e){
			 resultMap.put("msg", "失败！");
		 }
		 resultMap.put("total", 1);
		 return resultMap;
	 }
	 
	 /**
		 * 组装卸效率
		 * @param request
		 * @return
		 * @throws Exception
		 */
		 @RequestMapping(value="gettGroupEfficiencyList")
		 @ResponseBody
		 public Map<String, Object> gettGroupEfficiencyList(HttpServletRequest request) throws Exception{
			 Map<String, Object> result=new HashMap<String, Object>();  
			 

			Pagination<?> page = this.getPagination(request);
			
			String searchbegtime = request.getParameter("searchtime");//搜索开始时间
			String searchendtime = request.getParameter("searchtime2");//搜索结束时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			if(searchbegtime==null)searchbegtime=DateUtil.getyyyy_mm_01();
			if(searchendtime==null)searchendtime=DateUtil.getyyyy_mm_dd();
			
			TruckseqVo truckseqVo = new TruckseqVo();
			if(searchbegtime!=null && searchendtime != null){
				Date searchtimeD = sdf.parse(searchbegtime);
				Date searchtime2D = sdf.parse(searchendtime);
				truckseqVo.setSearchbegtime(searchtimeD);
				truckseqVo.setSearchendtime(searchtime2D);
			}
			
			if (truckseqVo!=null) {
				 page.setParam(truckseqVo);
			}
			List<TruckseqVo> truckseqVoList = new ArrayList<TruckseqVo>();
			truckseqVoList = truckseqVoService.getGroupEfficiencyList(truckseqVo);
			
			 result.put("rows",truckseqVoList);  
			 result.put("total",page.getTotalCount());  
			 
			return result;
		}
		 
		 /**
			 * 组装卸效率明细列表
			 * @param request
			 * @return
			 * @throws Exception
			 */
			 @RequestMapping(value="getGroupEfficiencyDetailPageList")
			 @ResponseBody
			 public Map<String, Object> getGroupEfficiencyDetailPageList(HttpServletRequest request) throws Exception{
				 Map<String, Object> result=new HashMap<String, Object>();  
				 
				Pagination<?> page = this.getPagination(request);
				
				String groupcode = request.getParameter("groupcode");
				
				String searchbegtime = request.getParameter("searchtime");//搜索开始时间
				String searchendtime = request.getParameter("searchtime2");//搜索结束时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
				if(searchbegtime==null)searchbegtime=DateUtil.getyyyy_mm_01();
				if(searchendtime==null)searchendtime=DateUtil.getyyyy_mm_dd();
				
				TruckseqVo truckseqVo = new TruckseqVo();
				if(searchbegtime!=null && searchendtime != null){
					Date searchtimeD = sdf.parse(searchbegtime);
					Date searchtime2D = sdf.parse(searchendtime);
					truckseqVo.setSearchbegtime(searchtimeD);
					truckseqVo.setSearchendtime(searchtime2D);
				}
				truckseqVo.setGroupcode(groupcode);
				
				if (truckseqVo!=null) {
					 page.setParam(truckseqVo);
				}
				List<TruckseqVo> truckseqVoList = new ArrayList<TruckseqVo>();
				truckseqVoList = truckseqVoService.getTruckseqEfficiencyPageList(page);
				
				 result.put("rows",truckseqVoList);  
				 result.put("total",page.getTotalCount());  
				 
				return result;
			}
			 
}
