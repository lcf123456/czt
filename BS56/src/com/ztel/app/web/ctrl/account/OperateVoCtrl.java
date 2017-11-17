package com.ztel.app.web.ctrl.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.account.OperateVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.wms.ShipOrderService;
import com.ztel.app.vo.account.OperateVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.ShipOrderVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;
@Controller
@RequestMapping("/account/operate")
public class OperateVoCtrl extends BaseCtrl {
	
	@Autowired
	private OperateVoService operateVoService = null;
	
	@Autowired
	private ShipOrderService shipOrderService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
		
	@RequestMapping("toOperateRefund")
	public String index1(HttpServletRequest request) {
			
		return "/account/v_operate_refund";
	}
	
	@RequestMapping("toOperateStorage")
	public String toOperateStorage(HttpServletRequest request) {
		
		return "/account/v_operate_storage";
	}
	
	@RequestMapping("toOperateImp")
	public String toOperateImp(HttpServletRequest request) {
		
		return "/account/v_operate_imp";
	}
	
	@RequestMapping("toOperateAudit")
	public String toOperateAudit(HttpServletRequest request) {
		
		return "/account/v_operate_audit";
	}
	
	@RequestMapping("toOperateReceive")
	public String toOperateReceive(HttpServletRequest request) {
		
		return "/account/v_operate_receive";
	}
	
	/**
	  * 获取退货、暂存列表
	  * @return 
	  * @throws Exception
	  */
	 @RequestMapping("getOperatePageList")
	 @ResponseBody
	 public  Map<String, Object> getOperatePageList(OperateVo operateVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		
		 if (operateVo!=null) {
			 page.setParam(operateVo);
		}
		 
		 List<OperateVo> paras = operateVoService.getOperatePageList(page);
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	 }
	 
	 @RequestMapping("getShiporderByCondition")
		@ResponseBody
		public  Map<String, Object> getShiporderByCondition(HttpServletRequest request,ShipOrderVo shipOrderVo)
		{
			
			Map<String, Object> result=new HashMap<String, Object>();  
			List<ShipOrderVo> paras = new ArrayList<>();
			if(shipOrderVo!=null&&shipOrderVo.getRoutecode()!=null&&!"".equals(shipOrderVo.getRoutecode())&&shipOrderVo.getOrderdatestr()!=null&&!"".equals(shipOrderVo.getOrderdatestr())){
				System.out.println("keywd==="+shipOrderVo.getKeywd());
				paras=shipOrderService.selectShiporderByCondition(shipOrderVo);
			}
			
			result.put("rows",paras);  
			result.put("total",paras.size());  
			
			return result;
		}
	 
	 /**
	  * 删除
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doDelOperate",method=RequestMethod.POST)
	 // @ResponseBody
	 public   void doDelOperate(@RequestParam("id") List<Integer> idLst,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 //System.out.println(idLst.size()+"===="+ordernoLst.size());
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/account/operate/doDelOperate", "车组退货/暂存", "删除", "");
			 
			 operateVoService.doOperateDelete(idLst);
			 
			 map.put("msg", "成功");
			 total=idLst.size();
		 } catch (Exception e) {
			 // TODO: handle exception
			 e.printStackTrace();  
			 map.put("msg", "失败");
		 }
		 map.put("total", total);
		 
		 //直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);  
	 }
	 
	 /**
	  * 小仓入库
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doOperateImp",method=RequestMethod.POST)
	 // @ResponseBody
	 public   void doOperateImp(@RequestParam("id") List<Integer> idLst,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 //System.out.println(idLst.size()+"===="+ordernoLst.size());
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/account/operate/doOperateImp", "核算管理小仓入库", "入库", "");
			 
			 operateVoService.doOperateStatusUpdate(idLst, "", "30");
			 
			 map.put("msg", "成功");
			 total=idLst.size();
		 } catch (Exception e) {
			 // TODO: handle exception
			 e.printStackTrace();  
			 map.put("msg", "失败");
		 }
		 map.put("total", total);
		 
		 //直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);  
	 }
	
	 /**
	  * 信息确认（确认为退货或还是暂存）
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doOperateAudit",method=RequestMethod.POST)
	 // @ResponseBody
	 public   void doOperateAudit(@RequestParam("id") List<Integer> idLst,@RequestParam("ctype") String ctype,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 //System.out.println(idLst.size()+"===="+ordernoLst.size());
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/account/operate/doOperateAudit", "分部主任审核定性", "定性", "");
			 
			 operateVoService.doOperateStatusUpdate(idLst, ctype ,"35");
			 
			 map.put("msg", "成功");
			 total=idLst.size();
		 } catch (Exception e) {
			 // TODO: handle exception
			 e.printStackTrace();  
			 map.put("msg", "失败");
		 }
		 map.put("total", total);
		 
		 //直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);  
	 }
	 
	 /**
	  * 散煙區接收
	  * @return
	  * @throws Exception
	  */                                    
	 @RequestMapping(value="doReceive",method=RequestMethod.POST)
	 // @ResponseBody
	 public   void doReceive(@RequestParam("id") List<Integer> idLst,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 //System.out.println(idLst.size()+"===="+ordernoLst.size());
		 try {
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/account/operate/doReceive", "退货入库", "散烟区接收", "");
			 
			 operateVoService.doOperateStatusUpdate(idLst, "", "60");
			 
			 map.put("msg", "成功");
			 total=idLst.size();
		 } catch (Exception e) {
			 // TODO: handle exception
			 e.printStackTrace();  
			 map.put("msg", "失败");
		 }
		 map.put("total", total);
		 
		 //直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);  
	 }
	 

	 
	@RequestMapping("doOperateAdd")
	@ResponseBody
	public Map<String, Object> doOperateAdd(HttpServletRequest request,OperateVo operateVo)
	{
		
		Map<String, Object> map=new HashMap<String, Object>();
		 
		 try{
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/account/operate/doOperateAdd", "车组退货/暂存", "新增", "");
			 operateVo.setCreateid(userVo.getId());
			 operateVo.setCreatename(userVo.getUsername());
			 operateVoService.doOperateAdd(operateVo);
			 
			 map.put("msg", "成功");
		 }catch(Exception e){
			 map.put("msg", "失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	}
	
}
