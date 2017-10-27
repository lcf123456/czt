package com.ztel.app.web.ctrl.cost;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.PubService;
import com.ztel.app.service.cost.SPLApplyListLineService;
import com.ztel.app.service.cost.SPLApplyListService;
import com.ztel.app.service.cost.SPLConsumeServcie;
import com.ztel.app.service.cost.SPLTypeConsumeServcie;
import com.ztel.app.service.cost.SPLTypeServcie;
import com.ztel.app.vo.cost.SPLApplyListLineVo;
import com.ztel.app.vo.cost.SPLApplyListVo;
import com.ztel.app.vo.cost.SPLConsumeVo;
import com.ztel.app.vo.cost.SPLTypeConsumeVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;
/**
 * 部门领料汇总
 * @author NJ
 *
 */
@Controller
@RequestMapping("/cost/deptconsume")
public class SPLTypeConsumeCtrl extends BaseCtrl  {
	
	private static Logger logger = LogManager.getLogger(SPLTypeConsumeCtrl.class);
	
	//用于初始化数据的时候，进行数据类型转换，String类型转为Date类型
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@Autowired
	SPLTypeConsumeServcie splTypeConsumeServcie = null;
	
	@RequestMapping("toDeptConsume")
	public String index(HttpServletRequest request) {
		return "/cost/v_deptconsume";
	}

	/**
	  * 出库明细
	  * @return
	  */
	 @RequestMapping(value="getSPLTypeConsumeByDept")
	 @ResponseBody
	 public  Map<String, Object> getSPLTypeConsumeByDept(SPLTypeConsumeVo splTypeConsumeVo,HttpServletRequest request) throws Exception {

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 List paras = splTypeConsumeServcie.getSPLConsumeByDept(splTypeConsumeVo);
		 System.out.println(paras.size());
		 //System.out.println(paras.get(0).getParanameE());
		 result.put("rows",paras);  
		 result.put("total",paras.size());  

		 return result;
	 }
}
