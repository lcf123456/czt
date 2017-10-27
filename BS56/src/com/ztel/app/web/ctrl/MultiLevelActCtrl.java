/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.DeviceService;
import com.ztel.app.vo.DeviceType;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author Zeal
 * @since 2017年2月26日
 * 多级联动测试
 */
@Controller
public class MultiLevelActCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(MultiLevelActCtrl.class);
	
	@Autowired
	private DeviceService deviceService = null;
	
	@RequestMapping("/multi")
	public String index(HttpServletRequest request) {
		
		return "/index/multiLevelAct";
	}
	
	/**
	  * 获取一级列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getProvinces")
	 @ResponseBody
	 public  Map<String, Object> getOne() throws Exception {
		 Map<String, Object> result=new HashMap<String, Object>();  
		 List<DeviceType> ones = deviceService.searchOneLevelTypeList();
		 if(ones!=null&&ones.size()>0)
		 {
			 for(int i=0;i<ones.size();i++)
			 {
				 DeviceType  dt = (DeviceType)ones.get(i);
				 System.out.println(dt.toString());
			 }
		 }
		 result.put("ones",ones);  
	        return result;  
	 }
	 /**
	  * 获取二级列表
	  * @param province
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value = "getCities")
	 @ResponseBody
	 public Map<String, Object> getTwo(@RequestParam(value = "tId") String tId) throws Exception {
		 Map<String, Object> result=new HashMap<String, Object>();  
		 List<DeviceType> twos = deviceService.searchTwolLevelTypeByIDList(tId);
		 if(twos!=null&&twos.size()>0)
		 {
			 for(int i=0;i<twos.size();i++)
			 {
				 DeviceType  dt = (DeviceType)twos.get(i);
				 System.out.println(dt.toString());
			 }
		 }
		 result.put("twos",twos);  
	        return result;  
	 }
	    // 再往下级的获取方式和getCities方法都相同，所以此处略过
	
	 /**
	  * 获取三级列表
	  * @param province
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value = "getCounties")
	 @ResponseBody
	 public Map<String, Object> getThree(@RequestParam(value = "tId") String tId) throws Exception {
		 Map<String, Object> result=new HashMap<String, Object>();  
		 List<DeviceType> threes = deviceService.searchThreelLevelTypeByIDList(tId);
		 if(threes!=null&&threes.size()>0)
		 {
			 for(int i=0;i<threes.size();i++)
			 {
				 DeviceType  dt = (DeviceType)threes.get(i);
				 System.out.println(dt.toString());
			 }
		 }
		 result.put("threes",threes);  
	        return result;  
	 }
	
    
}
