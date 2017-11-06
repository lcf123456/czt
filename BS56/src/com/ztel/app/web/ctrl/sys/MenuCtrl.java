/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.web.ctrl.sys;

import java.util.ArrayList;
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

import com.ztel.app.service.sys.MenuinfoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.Base;
import com.ztel.app.vo.sys.MenuInfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.web.annotation.PublicPage;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * @author Zeal
 * @since 2017年3月16日
 * 菜单控制
 */
@Controller
@RequestMapping("/sys/menu")
public class MenuCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(MenuCtrl.class);
	
	@Autowired
	private MenuinfoService menuinfoService = null;
	@Autowired
	private OperationlogService operationlogService = null;
	
	@RequestMapping("toMenu")
	public String index(HttpServletRequest request) {
		
		return "/sys/v_menu";
	}
	
	@RequestMapping("toMenuRight")
	public String toMenuRight(HttpServletRequest request) {
		String sysid= request.getParameter("sysid");
		request.setAttribute("sysid", sysid);
		return "/sys/v_menu_right";
	}
	
	/**
	 * 取系统模块名称：服务考评、成本管理、生产管理等
	 * @return
	 */
	 @RequestMapping(value="getSysMenu",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> getSysMenu()
	 {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 List menuList = new ArrayList<Base>();
		 Base base = new Base();
		 String[] sysId = Constant.belongSysId;
		 String[] sysName = Constant.belongSysName;
		 for(int i=0;i<sysId.length;i++)
		 {
			 base = new Base();
			 String id = sysId[i];
			 String name = sysName[i];
			 base.setId(id);
			 base.setName(name);
			 menuList.add(base);
		 }
		 map.put("rows", menuList);
		 return map;
	 }
	 
	 /**
	  * 取系统模块名称:下拉菜单显示所属系统,功能按钮
	  * @return
	  */
	 @RequestMapping(value="getSysMenuforCMBOX",method=RequestMethod.POST)
	 @ResponseBody
	 public   List getSysMenuforCMBOX()
	 {
		 List menuList = new ArrayList<Base>();
		 Base base = new Base();
		 String[] sysId = Constant.belongSysId;
		 String[] sysName = Constant.belongSysName;
		 for(int i=0;i<sysId.length;i++)
		 {
			 
			 base = new Base();
			 String id = sysId[i];
			 String name = sysName[i];
			 base.setId(id);
			 base.setText(name);
			 if(i==0)base.setSelected("true");
			 menuList.add(base);
		 }
		 return menuList;
	 }
	 
	 /**
	  * 根据系统模块名称获取栏目信息
	  * @return
	  */
	 /*
	 @RequestMapping(value="getSysMenuRight")
	 @ResponseBody
	 public   Map<String, Object> getSysMenuList()
	 {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 String id = "1";
		 List<Menuinfo> munuList=menuinfoService.searchMenuinfoList(id);
		 map.put("rows",munuList);  
		// System.out.println(JSON.toJSONString(map));
		 return map;
	 }
	 */
	
	 /**
	  * 根据系统模块名称获取栏目信息
	  * @return
	  */
	 @RequestMapping(value="getSysMenuRight")
	 @ResponseBody
	 public  List<MenuInfoVo> getSysMenuList(String sysid,String id)
	 {
		// System.out.println("sysid----"+sysid);
		// String sysid= request.getParameter("sysid");
		 //String id= request.getParameter("id");
		 if(sysid==null||sysid.equals(""))sysid="1";
		 if(id==null||id.equals(""))id="0";
		 //if(id==null||id.equals(""))sysid="0";//菜单id，对应数据库表中的fid
		 //System.out.println("menuCtrl getSysMenuRight :sysid----"+sysid+",id----"+id);
		 List<MenuInfoVo> menuList=menuinfoService.searchMenuinfoList(sysid,id);
		 return menuList;
	 }
	 
	 /**
	  * 根据系统模块名称获取栏目信息
	  * @return
	  */
	 @RequestMapping(value="getSysMenuRightByRole")
	 @ResponseBody
	 public  List<MenuInfoVo> getSysMenuList(String sysid,String id,String roleid)
	 {
		 if(sysid==null||sysid.equals(""))sysid="1";
		 if(id==null||id.equals(""))id="0";
		 if(roleid==null||roleid.equals("")||roleid.equals("null"))roleid="0";
		 //if(id==null||id.equals(""))sysid="0";//菜单id，对应数据库表中的fid
		//System.out.println("sysid----"+sysid+",id----"+id+",roleid----"+roleid);
		//List<MenuInfoVo> menuList= new ArrayList();
		 List<MenuInfoVo> menuList=menuinfoService.searchMenuinfoList(sysid,id,roleid);
		 return menuList;
	 }

	 /**
	  * 根据系统模块名称获取栏目信息
	  * @return
	  */
	 @RequestMapping(value="getMenuinfoListforTree")
	 @ResponseBody
	 public  List<MenuInfoVo> getMenuinfoListforTree(String sysid,String id)
	 {
		 if(sysid==null||sysid.equals(""))sysid="1";
		 if(id==null||id.equals(""))id="0";
		 //if(id==null||id.equals(""))sysid="0";//菜单id，对应数据库表中的fid
		 List<MenuInfoVo> menuList=menuinfoService.getMenuinfoListforTree(sysid,id);
		 return menuList;
	 }
	 
	 /**
	  * 修改栏目信息
	  * @return
	  */
	 @RequestMapping(value="doEditMenu")
	 @ResponseBody
	 public  Map<String, Object> doEditMenu(MenuInfoVo menuInfo,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 //System.out.println("id----"+menuinfo.getId()+",name="+menuinfo.getMenuname());
		// List<Menuinfo> menuList=menuinfoService.searchMenuinfoList(id);
		 menuinfoService.doEditMenu(menuInfo);
			UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/sys/menu/doEditMenu", "栏目管理", "修改", "");
		 map.put("total", "1");
		 map.put("msg", "修改成功");
		 return map;
	 }
	 
	 /**
	  * 新增栏目信息
	  * @return
	  */
	 @RequestMapping(value="doAddMenu")
	 @ResponseBody
	 public  Map<String, Object> doAddMenu(MenuInfoVo menuInfo,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 System.out.println("--------------"+menuInfo.toString());
		menuinfoService.doAddMenu(menuInfo);
		UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/sys/menu/doAddMenu", "栏目管理", "新增", "");
		 map.put("total", "1");
		 map.put("msg", "新增成功");
		 return map;
	 }
	 
	 /**
	  * 删除栏目信息
	  * @return
	  */
	 @RequestMapping(value="doDelMenu")
	 @ResponseBody
	 public  Map<String, Object> doDelMenu(String id,HttpServletRequest request)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 System.out.println("id----"+id);
		// List<Menuinfo> menuList=menuinfoService.searchMenuinfoList(id);
		 if(id==null)id="0";
		 menuinfoService.doDelMenu(id);
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/sys/menu/doDelMenu", "栏目管理", "删除", "");
		 map.put("total", "1");
		 map.put("msg", "修改成功");
		 return map;
	 }
}
