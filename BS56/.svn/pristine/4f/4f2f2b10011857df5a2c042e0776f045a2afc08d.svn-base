package com.ztel.app.web.ctrl.safe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.safe.HazardsLevelService;
import com.ztel.app.service.safe.HazardsService;
import com.ztel.app.service.safe.TypeinfoService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.safe.HazardsControlVo;
import com.ztel.app.vo.safe.HazardsVo;
import com.ztel.app.vo.wms.ATSCellInfoDetailVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;
/**
 * @author yy
 * @since 2017年10月18日
 *危险源
 */
@Controller
@RequestMapping("/safe/hazards")
public class HazardsCtrl extends BaseCtrl {
	private static Logger logger = LogManager.getLogger(HazardsCtrl.class);
	
	@Autowired
	private HazardsService hazardsService = null;
	@Autowired
	private TypeinfoService typeinfoService = null;
	@Autowired
	private HazardsLevelService hazardslevelService = null;
	@RequestMapping("toHazards")
	public String index(HttpServletRequest request) {
			
		return "/safe/v_hazards";
	}
	@RequestMapping("toHazardsAudit")
	public String index1(HttpServletRequest request) {
			
		return "/safe/v_hazardsaudit";
	}
	@RequestMapping("toHazardsControl")
	public String index2(HttpServletRequest request) {
			
		return "/safe/v_hazardscontrol";
	}
	@RequestMapping("toHazardsStat")
	public String index3(HttpServletRequest request) {
			
		return "/safe/v_hazardsstat";
	}
	
	
	
	
	/**
	  * 获取风险源清单列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="getHazards")
	 @ResponseBody
	 public  Map<String, Object> getHazards(HazardsVo hazardsVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 Map<String, Object> result=new HashMap<String, Object>();  
		 if (hazardsVo!=null) {
			 page.setParam(hazardsVo);
		}
		 List<HazardsVo> paras = hazardsService.searchHazardsPageList(page);
		 System.out.println(paras.size());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  
		 return result;
	 }	
	 
	 /**
	  * 获取风险源评估列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="getHazardsAudit")
	 @ResponseBody
	 public  Map<String, Object> getHazardsAudit(HazardsVo hazardsVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 Map<String, Object> result=new HashMap<String, Object>();  
		 if (hazardsVo!=null) {
			 page.setParam(hazardsVo);
		}
		 List<HazardsVo> paras = hazardsService.searchHazardsAuditPageList(page);
		 System.out.println(paras.size());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  
		 return result;
	 }	
	 /**
	  * 获取风险源控制列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="getHazardsControl")
	 @ResponseBody
	 public  Map<String, Object> getHazardsControl(HazardsVo hazardsVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 Map<String, Object> result=new HashMap<String, Object>();  
		 if (hazardsVo!=null) {
			 page.setParam(hazardsVo);
		}
		 List<HazardsVo> paras = hazardsService.searchHazardsControlPageList(page);
		 System.out.println(paras.size());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  
		 return result;
	 }	 
	 /**
	  * 获取风险源统计列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="getHazardsStat")
	 @ResponseBody
	 public  Map<String, Object> getHazardsStat(HazardsVo hazardsVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 Map<String, Object> result=new HashMap<String, Object>();  
		 if (hazardsVo!=null) {
			 page.setParam(hazardsVo);
		}
		 List<HazardsVo> paras = hazardsService.searchHazardsStatPageList(page);
		 System.out.println(paras.size());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  
		 return result;
	 }	 
/**
 * 获取危险源类型名称下拉列表
 * @return
 * @throws Exception
 */
@RequestMapping(value="getHazardstypeCombobox")
@ResponseBody
public  List<HashMap<String, String>> getHazardstypeCombobox(HttpServletRequest request) throws Exception {
	 List<HashMap<String, String>> boxList=new ArrayList<>();
	boxList=typeinfoService.getHazardstypeCombobox();
	 return boxList;
}

/**
 * 获取风险等级下拉列表
 * @return
 * @throws Exception
 */
@RequestMapping(value="getClevelCombobox")
@ResponseBody
public  List<HashMap<String, String>> getClevelCombobox(HttpServletRequest request) throws Exception {
	 List<HashMap<String, String>> boxList=new ArrayList<>();
	boxList=hazardslevelService.getClevelCombobox();
	 return boxList;
}
}