package com.ztel.app.web.ctrl.cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.cost.SPLTypeServcie;
import com.ztel.app.vo.cost.SPLTypeInfoVo;
import com.ztel.app.vo.sys.MenuInfoVo;

@Controller
@RequestMapping("/cost/SPLTypeInfo")
public class SPLTypeInfoCtrl {
	@Autowired
	private SPLTypeServcie sPLTypeServcie = null;
	
	@RequestMapping("toSPLTypeInfo")
	public String toSPLTypeInfo(HttpServletRequest request) {
		
		return "/cost/v_spltype";
	}

	/**
	  * 根据系统模块名称获取栏目信息
	  * @return
	  */
	 @RequestMapping(value="getSPLTypeInfoList")
	 @ResponseBody
	 public  List<SPLTypeInfoVo> getSPLTypeInfoList(String id)
	 {
		 if(id==null||id.equals(""))id="0";
		 //System.out.println("menuCtrl getSysMenuRight :sysid----"+sysid+",id----"+id);
		 List<SPLTypeInfoVo> SPLTypeInfoList=sPLTypeServcie.getSPLTypeList(id);
		 return SPLTypeInfoList;
	 }
	 
	 /**
	  * 新增栏目信息
	  * @return
	  */
	 @RequestMapping(value="doAddSPLTypeInfo")
	 @ResponseBody
	 public  Map<String, Object> doAddSPLTypeInfo(SPLTypeInfoVo sPLTypeInfoVo)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();
		 //System.out.println("--------------"+sPLTypeInfoVo.toString());
		 try{
			 sPLTypeServcie.doAddSPLTypeInfo(sPLTypeInfoVo);
			 map.put("msg", "新增成功");
		 }
		 catch(Exception e){
			 map.put("msg", "新增失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	 }
	 
	 /**
	  * 修改栏目信息
	  * @return
	  */
	 @RequestMapping(value="doEditSPLTypeInfo")
	 @ResponseBody
	 public  Map<String, Object> doEditSPLTypeInfo(SPLTypeInfoVo sPLTypeInfoVo)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 //System.out.println("id----"+menuinfo.getId()+",name="+menuinfo.getMenuname());
		// List<Menuinfo> menuList=menuinfoService.searchMenuinfoList(id);
		 int resutl =  sPLTypeServcie.doEditSPLTypeInfo(sPLTypeInfoVo);
		 if(resutl==1)
		 {
			 map.put("total", "1");
			 map.put("msg", "修改成功");
		 }
		 else{
			 map.put("total", "1");
			 map.put("msg", "修改失败");
		 }
		 
		 return map;
	 }
	 
	 /**
	  * 修改栏目信息
	  * @return
	  */
	 @RequestMapping(value="doDelSPLTypeInfo")
	 @ResponseBody
	 public  Map<String, Object> doDelSPLTypeInfo(Integer id)
	 {
		 Map<String, Object> map=new HashMap<String, Object>();  
			
		// List<Menuinfo> menuList=menuinfoService.searchMenuinfoList(id);
		 if(id==null)id=0;
		 int result =  sPLTypeServcie.doDelSPLTypeInfo(id);
		 if(result==1)
		 {
			 map.put("total", "1");
			 map.put("msg", "删除成功");
		 }
		 else{
			 map.put("total", "0");
			 map.put("msg", "对不起，该类型有剩余库存，不允许删除！");
		 }
		 return map;
	 }
	 
 
     /**
      * 获取物资类别下拉列表,领料申请
      * @return
      * @throws Exception
      */
     @RequestMapping("getSPLTypesCombobox")
     @ResponseBody
     public List<HashMap<String, String>> getSPLTypesCombobox(@RequestParam("clevel") int clevel,@RequestParam("fid") int fid,HttpServletRequest request) {
    	 // TODO Auto-generated method stub
    	 String reqType = request.getParameter("reqType");
    	 if(reqType==null)reqType="";
    	 String typename = request.getParameter("typename");
    	 SPLTypeInfoVo splTypeInfoVo=new SPLTypeInfoVo();
    	 splTypeInfoVo.setClevel(clevel);
    	
    	 String listtype = request.getParameter("listtype");
    	 
    	 List<HashMap<String, String>> boxList=new ArrayList<>();
    	 if(reqType.equals("forComb")){
    		 splTypeInfoVo.setFid(fid);
    		
	    	 if(clevel==4){
	    		 if(listtype!=null){
	        		 splTypeInfoVo.setIsproduce(listtype);
	    		 }
	    		 boxList = sPLTypeServcie.getSPLTypeInfoComboboxnForApply(splTypeInfoVo);
	    	 }else{
	    		 boxList=sPLTypeServcie.getSPLTypeInfoCombobox(splTypeInfoVo);
	    	 }
    	 }else
    	 {
    		 if(listtype!=null){
        		 splTypeInfoVo.setIsproduce(listtype);
    		 }
    		 splTypeInfoVo.setTypename(typename);
    		 boxList = sPLTypeServcie.getSPLTypeInfoComboboxnForApply(splTypeInfoVo);
    	 }
    	 
    	 return boxList;
     }
}
