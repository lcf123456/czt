package com.ztel.app.web.ctrl.wms;

import java.math.BigDecimal;
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

import com.ztel.app.service.PubService;
import com.ztel.app.service.produce.SortTroughService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.wms.MachinedamagedLineService;
import com.ztel.app.service.wms.Impl.MachinedamagedService;
import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.CigarettedamagedVo;
import com.ztel.app.vo.wms.MachinedamagedLineVo;
import com.ztel.app.vo.wms.MachinedamagedVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

@Controller
@RequestMapping("/wms/machinedamaged")
public class MachinedamagedCtrl extends BaseCtrl{
	private static Logger logger = LogManager.getLogger(MachinedamagedCtrl.class);
	
	@Autowired
	private MachinedamagedService machinedamagedService = null;
	
	@Autowired
	private  MachinedamagedLineService machinedamagedLineService = null;
	
	@Autowired
	private  SortTroughService sortTroughService = null;
	@Autowired
	private OperationlogService operationlogService = null;
	
	@Autowired
	PubService pubService = null;
	
	@RequestMapping("toMachinedamaged")
	public String index(HttpServletRequest request) {
		return "/wms/v_machinedamaged";
	}
	
	@RequestMapping(value="getMachinedamagedPageList")
	 @ResponseBody
	 public Map<String, Object> getMachinedamagedPageList(MachinedamagedVo machinedamagedVo, HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
		 String keyword = machinedamagedVo.getKeyword();

		 Pagination<?> page = this.getPagination(request);
		if (machinedamagedVo!=null) {
			 page.setParam(machinedamagedVo);
		}
		List<MachinedamagedVo> machinedamagedVoList = new ArrayList<MachinedamagedVo>();
		machinedamagedVoList = machinedamagedService.selectCigarettedamagedPageList(page);
		
		 result.put("rows",machinedamagedVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
	
	/**
	 * 根据fid取明细列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getMachinedamagedLineList")
	 @ResponseBody
	 public List<MachinedamagedLineVo> getMachinedamagedLineList(HttpServletRequest request) throws Exception{
		 String fid = request.getParameter("fid");
		 if(fid==null||fid.equals(""))fid="0";
		 MachinedamagedLineVo machinedamagedLineVo = new MachinedamagedLineVo();
		 machinedamagedLineVo.setFid(new BigDecimal(fid));
	
		List<MachinedamagedLineVo> machinedamagedLineVoList = new ArrayList<MachinedamagedLineVo>();
		machinedamagedLineVoList = machinedamagedLineService.selectListByCond(machinedamagedLineVo);
		
		return machinedamagedLineVoList;
	}
	
	@RequestMapping(value="getSortTroughVoList")
	 @ResponseBody
	 public List<SortTroughVo> getSortTroughVoList(HttpServletRequest request) throws Exception{
		 String cigarettetype = request.getParameter("cigarettetype");
		 if(cigarettetype==null||cigarettetype.equals(""))cigarettetype="0";
		 String groupno = request.getParameter("groupno");
		 if(groupno==null||groupno.equals(""))groupno="0";
		 String keyword = request.getParameter("keyword");
		 
		 SortTroughVo sortTroughVo = new SortTroughVo();
		 sortTroughVo.setCigarettetype(new BigDecimal(cigarettetype));
	
		 if(keyword!=null&&!keyword.equals("")){
			 sortTroughVo.setKeyword(keyword);
		 }
		List<SortTroughVo> sortTroughVoList = new ArrayList<SortTroughVo>();
		sortTroughVoList = sortTroughService.selectListByCond(sortTroughVo);
		
		return sortTroughVoList;
	}
	
	 /**
	  * 新增机损烟
	  * @return
	  */
	 @RequestMapping(value="doSave")
	 @ResponseBody
	 public  Map<String, Object> doSave(SortTroughVo sortTroughVo,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String mdid = request.getParameter("mdid");//id
		 String qty = request.getParameter("qty");//破损数量
		 
		 Long id = 0L;
		 if(mdid!=null&&!mdid.equals("0")){//第二次插入
			 id = new Long(mdid);
		 }
		 else
			 {//首次插入
			 id = pubService.getSequence("S_WMS_MACHINEDAMAGED");
			 }
		 Long userid = 0L;
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/wms/machinedamaged/doSave", "机损烟", "新增", "");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 userid = userVo.getId();
		 }
		 try{
			 machinedamagedService.insertMachinedamaged(sortTroughVo, new BigDecimal(qty),new BigDecimal(id),userid,mdid);
		 map.put("msg", "新增成功");
		 map.put("mdid", id+"");
		 }catch(Exception e){
			 map.put("msg", "新增失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 机损烟审核
	  * @return
	  */
	 @RequestMapping(value="doAudit")
	 @ResponseBody
	 public  Map<String, Object> doAudit(HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 String checkflag = request.getParameter("checkflag");
		 String mdid = request.getParameter("mdid");//破损主表id
		 String checkdescribe = request.getParameter("checkdescribe");
		 
		 MachinedamagedVo machinedamagedVo = new MachinedamagedVo();
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/wms/machinedamaged/doAudit", "机损烟", "审核", "");
		 if(userVo!=null&&userVo.getUsername().trim().length()>0){
			 machinedamagedVo.setCheckuser(userVo.getId());
			 machinedamagedVo.setCheckusername(userVo.getUsername());
		 }
		 machinedamagedVo.setId(new BigDecimal(mdid));
		 machinedamagedVo.setCheckflag(checkflag);
		 machinedamagedVo.setCheckdescribe(checkdescribe);
		 machinedamagedVo.setChecktime(new Date());
		 try{
			 machinedamagedService.doAudit(machinedamagedVo);
		 
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
			
		 String mdid = request.getParameter("mdid");
		 MachinedamagedVo machinedamagedVo = new MachinedamagedVo();
		 machinedamagedVo.setId(new BigDecimal(mdid));
		 machinedamagedVo.setDelstatus("0");
		 try{
			 machinedamagedService.doUpdate(machinedamagedVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/wms/machinedamaged/doDel", "机损烟", "删除", "");
		 resultMap.put("msg", "删除成功！");
		 }catch(Exception e){
			 resultMap.put("msg", "删除失败！");
		 }
		 resultMap.put("total", 1);
		 return resultMap;
	 }
}
