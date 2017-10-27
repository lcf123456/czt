package com.ztel.app.web.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.cost.SupplierInfoVoService;
import com.ztel.app.service.gis.RegionVoService;
import com.ztel.app.service.sys.BaseTypeInfoService;
import com.ztel.app.service.sys.DeptVoService;
import com.ztel.app.service.sys.PostinfoService;
import com.ztel.app.service.sys.RoleinfoService;
import com.ztel.app.service.sys.RouteInfoService;
import com.ztel.app.service.sys.UserVoService;
import com.ztel.app.service.sys.VehicleVoService;
import com.ztel.app.service.wms.ConsignorService;
import com.ztel.app.service.wms.ItemVoService;
import com.ztel.app.vo.gis.RegionVo;
import com.ztel.app.vo.sys.BasetypeInfoVo;
import com.ztel.app.vo.sys.RouteInfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.ConsignorVo;
import com.ztel.app.vo.wms.ItemVo;
import com.ztel.app.web.ctrl.sys.UserCtrl;
import com.ztel.framework.web.ctrl.BaseCtrl;

@Controller
@RequestMapping("/comm/combobox")
public class ComboboxCtrl extends BaseCtrl{
	
	private static Logger logger = LogManager.getLogger(UserCtrl.class);
	
	@Autowired
	private DeptVoService deptVoService = null;
	
	@Autowired
	private UserVoService userVoService = null;
	
	@Autowired
	private PostinfoService postinfoService = null;
	
	@Autowired
	private BaseTypeInfoService baseTypeInfoService = null;
	
	@Autowired
	private RoleinfoService roleinfoService = null;
	
	@Autowired
	private VehicleVoService vehicleVoService = null;
	
	@Autowired
	private RegionVoService regionVoService = null;
	
	@Autowired
	private RouteInfoService routeInfoService = null;
	
	@Autowired
	private SupplierInfoVoService supplierInfoVoService = null;
	
	@Autowired
	private ItemVoService itemVoService = null;
	
	@Autowired
	private ConsignorService consignorService = null;
	
	/**
	  * 获取配送车辆下拉列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getVechilesCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getVechilesCombobox(HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 boxList=vehicleVoService.getVehiclesCombobox();
		 return boxList;
	 }
	
	/**
	  * 获取岗位下拉列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getPostCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getPostCombobox(HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 boxList=postinfoService.getPostCombobox();
		 return boxList;
	 }
	 /**
	  * 获取部门下拉列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getDeptCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getDeptCombobox(HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 boxList=deptVoService.getDeptCombobox();
		 return boxList;
	 }
	 /**
	  * 获取角色下拉列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getRolesCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getRolesCombobox(HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 boxList=roleinfoService.getRoleinfoCombobox();
		 return boxList;
	}
	 /**
	  * 获取Bastypeinfo表中信息的下拉列表  传入Typecode值
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getComboboxByTypeCode")
	 @ResponseBody
	 public  List<HashMap<String, String>> getComboboxByTypeCode(@RequestParam("typeCode") String typecode,HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 boxList=baseTypeInfoService.getBaseTypeinfoCombobox(typecode);
		 //System.out.println(boxList.size()+"------");
		 return boxList;
	 }
	 
	 /**
	  * 获取用户下拉列表  传入角色id
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getUserComboboxByRoleId")
	 @ResponseBody
	 public List<HashMap<String, String>> getUserComboboxByRoleId(@RequestParam("roleid") Integer roleid,HttpServletRequest request) {
			 // TODO Auto-generated method stub
			 List<UserVo> treeList=userVoService.getUserListByRoleId(roleid);
			 List<HashMap<String, String>> boxList=new ArrayList<>();
			 if (treeList!=null&&treeList.size()>0) {
				 for(int i=0;i<treeList.size();i++){
					 UserVo vo=treeList.get(i);
					 HashMap<String, String> map=new HashMap<String, String>();
					 map.put("id", vo.getId().toString());
					 map.put("username", vo.getUsername());
					 //System.out.println(vo.getId().toString()+"-------------");
					 //System.out.println(vo.getText()+"-------------");
					 boxList.add(map);
				 }
			}
			return boxList;
	 }
	 
	 /**
	  * 获取GIS区域下拉列表
	  * @return
	  * @throws Exception
	  */
     @RequestMapping("getRegionsCombobox")
	 @ResponseBody
	 public List<HashMap<String, String>> getRegionsCombobox(HttpServletRequest request) {
		 // TODO Auto-generated method stub
		 List<RegionVo> treeList=regionVoService.getRegionInfoList();
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 if (treeList!=null&&treeList.size()>0) {
			 for(int i=0;i<treeList.size();i++){
				 RegionVo vo=treeList.get(i);
				 HashMap<String, String> map=new HashMap<String, String>();
				 map.put("id", vo.getId()+"");
				 map.put("name", vo.getName());
				 //System.out.println(vo.getId().toString()+"-------------");
				 //System.out.println(vo.getText()+"-------------");
				 boxList.add(map);
			 }
		 }
		 return boxList;
	 }
     
     /**
      * 获取basetype类型下拉列表
      * @return
      * @throws Exception
      */
     @RequestMapping("getBaseTypesCombobox")
     @ResponseBody
     public List<HashMap<String, String>> getBaseTypesCombobox(HttpServletRequest request) {
    	 // TODO Auto-generated method stub
    	 List<BasetypeInfoVo> treeList=baseTypeInfoService.getTypeList();
    	 List<HashMap<String, String>> boxList=new ArrayList<>();
    	 if (treeList!=null&&treeList.size()>0) {
    		 for(int i=0;i<treeList.size();i++){
    			 BasetypeInfoVo vo=treeList.get(i);
    			 HashMap<String, String> map=new HashMap<String, String>();
    			 map.put("typecode", vo.getTypecode());
    			 map.put("typename", vo.getTypename());
    			 //System.out.println(vo.getTypecode().toString()+"-------------");
    			 //System.out.println(vo.getTypecode()+"-------------");
    			 boxList.add(map);
    		 }
    	 }
    	 return boxList;
     }
     
     /**
	  * 获取车组下拉列表  传入部门id
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getRoutesComboboxByDeptid")
	 @ResponseBody
	 public List<HashMap<String, String>> getRoutesComboboxByDeptid(@RequestParam("deptid") Integer deptid,HttpServletRequest request) {
			 // TODO Auto-generated method stub
			 List<RouteInfoVo> treeList=routeInfoService.getRoutesListByDeptid(deptid);
			 List<HashMap<String, String>> boxList=new ArrayList<>();
			 if (treeList!=null&&treeList.size()>0) {
				 for(int i=0;i<treeList.size();i++){
					 RouteInfoVo vo=treeList.get(i);
					 HashMap<String, String> map=new HashMap<String, String>();
	    			 map.put("driverid", vo.getDriverid()+"");
	    			 map.put("drivername", vo.getDrivername());
	    			 map.put("cashierid", vo.getCashierid()+"");
	    			 map.put("cashiername", vo.getCashiername());
					 map.put("routecode", vo.getRoutecode());
					 map.put("routename", vo.getRoutename());
					 //System.out.println(vo.getId().toString()+"-------------");
					 //System.out.println(vo.getText()+"-------------");
					 boxList.add(map);
				 }
			}
			return boxList;
	 }
	 
	 /**
	  * 获取卷烟下拉列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getItemCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getItemCombobox(ItemVo itemVo,HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 boxList=itemVoService.getItemComboboxByCond(itemVo);
		 return boxList;
	 }
	 
	 /**
	  * 获取货主下拉列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getConsignsorCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getConsignsorCombobox(ConsignorVo consignorVo,HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 boxList=consignorService.getConsignsorComboboxByCond(consignorVo);
		 return boxList;
	 }
}
