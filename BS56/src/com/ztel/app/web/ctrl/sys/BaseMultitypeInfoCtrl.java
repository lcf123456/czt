package com.ztel.app.web.ctrl.sys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.sys.BaseMultitypeInfoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.sys.BaseMultitypeInfoVo;
import com.ztel.app.vo.sys.UserVo;

@Controller
@RequestMapping("/sys/basemultitypeinfo")
public class BaseMultitypeInfoCtrl {
	
	@Autowired
	private BaseMultitypeInfoService baseMultitypeInfoService = null;
	@Autowired
	private OperationlogService operationlogService = null;

	@RequestMapping("toBaseMultitype")
	public String toBaseMultitype(HttpServletRequest request) {
		
		return "/sys/v_basemultitypeinfo";
	}
	
	/**
	  * 根据系统模块名称获取栏目信息
	  * @return
	  */
	 @RequestMapping(value="getBaseMultitypeInfoList")
	 @ResponseBody
	 public  List<BaseMultitypeInfoVo> getBaseMultitypeInfoList(String id,HttpServletRequest request)
	 {
		 if(id==null||id.equals(""))id="0";//对应父id
		 String typeid = request.getParameter("typeid");//记录的id
		 
		 BaseMultitypeInfoVo baseMultitypeInfoVo = new BaseMultitypeInfoVo();
		 baseMultitypeInfoVo.setParentid(new BigDecimal(id));
		 
		 if(typeid!=null&&!typeid.equals("")&&id.equals("0")){
			 baseMultitypeInfoVo.setId(new BigDecimal(typeid));
		 }
		 
		 List<BaseMultitypeInfoVo> menuList=baseMultitypeInfoService.searchBaseMultitypeInfoList(baseMultitypeInfoVo);
		 return menuList;
	 }
	 
	 /**
	  * 新增多级类别信息
	  * @return
	  */
	 @RequestMapping(value="doAddMultitype")
	 @ResponseBody
	 public  Map<String, Object> doAddMultitype(BaseMultitypeInfoVo baseMultitypeInfoVo,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		// System.out.println("--------------"+menuInfo.toString());
		 try
		 {
			 baseMultitypeInfoService.doAddMultitype(baseMultitypeInfoVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/sys/basemultitypeinfo/doAddMultitype", "多级类别管理信息", "新增", "");
			 map.put("msg", "新增成功");
		 }
		 catch(Exception e){
			 map.put("msg", "新增失败，请联系管理员！");
		 }
		
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 修改多级类别信息
	  * @return
	  */
	 @RequestMapping(value="doEditMultitype")
	 @ResponseBody
	 public  Map<String, Object> doEditMultitype(BaseMultitypeInfoVo baseMultitypeInfoVo,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 try{
			 baseMultitypeInfoService.doEditMultitype(baseMultitypeInfoVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/sys/basemultitypeinfo/doEditMultitype", "多级类别管理信息", "修改", "");
			 map.put("msg", "修改成功");
		 }catch(Exception e){
			 map.put("msg", "修改失败");
		 }
		 
		 map.put("total", "1");
		 
		 return map;
	 }
	 
	 /**
	  * 删除多级类别信息
	  * @return
	  */
	 @RequestMapping(value="doDelMultitype")
	 @ResponseBody
	 public  Map<String, Object> doDelMultitype(String id,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/sys/basemultitypeinfo/doDelMultitype", "多级类别管理信息", "删除", "");
		 System.out.println("id----"+id);
		// List<Menuinfo> menuList=menuinfoService.searchMenuinfoList(id);
		 if(id==null)id="0";
		 BaseMultitypeInfoVo baseMultitypeInfoVo = new BaseMultitypeInfoVo();
		 baseMultitypeInfoVo.setId(new BigDecimal(id));
		 baseMultitypeInfoVo.setDelstatus(new BigDecimal("0"));
		 baseMultitypeInfoService.doEditMultitype(baseMultitypeInfoVo);
		 map.put("total", "1");
		 map.put("msg", "删除成功");
		 return map;
	 }
	 
	 /**
	  * 下拉框
	  * @return
	  */
	 @RequestMapping(value="getMultitypeOneLevelCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getMultitypeOneLevelCombobox() 
	 {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 BaseMultitypeInfoVo baseMultitypeInfoVo = new BaseMultitypeInfoVo();
		 baseMultitypeInfoVo.setTypelevel(new BigDecimal("1"));
			 boxList =  baseMultitypeInfoService.getMultitypeCombobox(baseMultitypeInfoVo);
		 return boxList;
	 }
	 /**
	  * 下拉框
	  * @return
	  */
	 @RequestMapping(value="getMultitypeComboboxByCond")
	 @ResponseBody
	 public  List<HashMap<String, String>> getMultitypeComboboxByCond(BaseMultitypeInfoVo baseMultitypeInfoVo,HttpServletRequest request) 
	 {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 boxList =  baseMultitypeInfoService.getMultitypeCombobox(baseMultitypeInfoVo);
		 return boxList;
	 }
}
