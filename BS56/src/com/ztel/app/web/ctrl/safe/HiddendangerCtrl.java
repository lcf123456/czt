package com.ztel.app.web.ctrl.safe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.safe.HiddendangerService;
import com.ztel.app.service.safe.TypeinfoService;
import com.ztel.app.service.sq.CigfactoryService;
import com.ztel.app.service.sys.BusinessRoleService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.safe.FireFacilitiesVo;
import com.ztel.app.vo.safe.HiddendangerVo;
import com.ztel.app.vo.sys.BlockcustomerVo;
import com.ztel.app.vo.sys.DeptVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.ATSCellInfoDetailVo;
import com.ztel.framework.util.DateUtil;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;
/**
 * @author yy
 * @since 2017年10月11日
 *隐患事件
 */
@Controller
@RequestMapping("/safe/hiddendanger")
public class HiddendangerCtrl extends BaseCtrl {
	private static Logger logger = LogManager.getLogger(HiddendangerCtrl.class);
	
	@Autowired
	private HiddendangerService hiddendangerService = null;

	@Autowired
	private OperationlogService operationlogService = null;
	@Autowired
	private TypeinfoService typeinfoService = null;
	
	@RequestMapping("toDangercreate")
	public String index(HttpServletRequest request) {
			
		return "/safe/v_dangercreate";
	}
	@RequestMapping("toDangerverify")
	public String index1(HttpServletRequest request) {
			
		return "/safe/v_dangerverify";
	}
	@RequestMapping("toDangerrectify")
	public String index2(HttpServletRequest request) {
			
		return "/safe/v_dangerrectify";
	}
	
	
	
	
	/**
	  * 获取隐患记录信息列表
	  * @return
	  * @throws Exception
	*/
	 @RequestMapping(value="getDangercreate")
	 @ResponseBody
	 public  Map<String, Object> getDangercreate(HiddendangerVo hiddendangerVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 Map<String, Object> result=new HashMap<String, Object>();  
		 if (hiddendangerVo!=null) {
			 page.setParam(hiddendangerVo);
		}
		 List<HiddendangerVo> paras = hiddendangerService.searchDangercreatPageList(page);
		 System.out.println(paras.size());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  
		 return result;
	 }	
	 /**
	  * 获取隐患核实信息列表
	  * @return
	  * @throws Exception
	*/
	 @RequestMapping(value="getDangerverify")
	 @ResponseBody
	 public  Map<String, Object> getDangerverify(HiddendangerVo hiddendangerVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 Map<String, Object> result=new HashMap<String, Object>();  
		 if (hiddendangerVo!=null) {
			 page.setParam(hiddendangerVo);
		}
		 List<HiddendangerVo> paras = hiddendangerService.searchDangerverifyPageList(page);
		 System.out.println(paras.size());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  
		 return result;
	 }	
	 /**
	  * 获取隐患整改信息列表
	  * @return
	  * @throws Exception
	*/
	 @RequestMapping(value="getDangerrectify")
	 @ResponseBody
	 public  Map<String, Object> getDangerrectify(HiddendangerVo hiddendangerVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 Map<String, Object> result=new HashMap<String, Object>();  
		 if (hiddendangerVo!=null) {
			 page.setParam(hiddendangerVo);
		}
		 List<HiddendangerVo> paras = hiddendangerService.searchDangerrectifyPageList(page);
		 System.out.println(paras.size());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  
		 return result;
	 }	
	 /**
	  * 获取隐患类型名称下拉列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="getCtypeCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getCtypeCombobox(HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		boxList=typeinfoService.getCtypeCombobox();
		 return boxList;
	 }
	 /**
	  * 删除隐患整改信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doHangerrectifyDel",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> doHangerrectifyDel(@RequestParam("id") List<Integer> ids,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
		 if (ids!=null&&ids.size()>0) {
			 total = ids.size();
		}
		 try {
		
			 hiddendangerService.delHangerrectify(ids);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/safe/hiddendanger/doHangerrectifyDel", "隐患整改", "删除", "");
			 map.put("msg", "成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 
		 return map;
	 }
	 /**
	  * 修改隐患整改信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doHangerrectifyUpdate",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> doHangerrectifyUpdate(HiddendangerVo hiddendangerVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try { 
			 if(hiddendangerVo.getStatus()!=null && hiddendangerVo.getStatus().equals("未排除") )
		 {
			 hiddendangerVo.setStatus("0"); 
		 }
		 else if(hiddendangerVo.getStatus()!=null && hiddendangerVo.getStatus().equals("已排除"))
		 {
			 hiddendangerVo.setStatus("10"); 
		 }
			 if(hiddendangerVo.getDangerstatus()!=null && hiddendangerVo.getDangerstatus().equals("无效隐患") )
			 {
				 hiddendangerVo.setDangerstatus("20"); 
			 }
			 else if(hiddendangerVo.getDangerstatus()!=null && hiddendangerVo.getDangerstatus().equals("有效隐患"))
			 {
				 hiddendangerVo.setDangerstatus("10"); 
			 }
			 hiddendangerService.updateHangerrectify(hiddendangerVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/safe/hiddendanger/doHangerrectifyUpdate", "隐患整改", "修改", "");
			 map.put("msg", "成功");
			 total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		map.put("total", total);
		// String result = JSON.toJSONString(map);
		// response.setContentType("text/html;charset=UTF-8");
		// response.getWriter().write(result);
		 return map;
	 }
	 /**
	  * 修改隐患记录信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doDangerCreateUpdate",method=RequestMethod.POST)
	 @ResponseBody
	 public   Map<String, Object> doDangerCreateUpdate(HiddendangerVo hiddendangerVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try { 
			 hiddendangerService.updateHangercreate(hiddendangerVo);
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/safe/hiddendanger/doDangerCreateUpdate", "隐患记录", "修改", "");
			 map.put("msg", "成功");
			 total=1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		map.put("total", total);
		// String result = JSON.toJSONString(map);
		// response.setContentType("text/html;charset=UTF-8");
		// response.getWriter().write(result);
		 return map;
	 }
	 /**
	  * 审核隐患核实信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doDangerVerify",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void doDangerVerify(HiddendangerVo hiddendangerVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		// operationlogService.insertLog(userVo, "/safe/hiddendanger/doDangerVerify", "隐患核实", "审核", "");
		//DeptVo deptVo = (DeptVo)request.getSession().getAttribute("deptVo");
		 Map<String, Object> map=new HashMap<String, Object>();  
		 
		 int total=0;
		 String handlestatus = "";
	        if(
	        		hiddendangerVo!=null&&hiddendangerVo.getHandlestatus()!=null&&!hiddendangerVo.getHandlestatus().equals("")){
	        	handlestatus = hiddendangerVo.getHandlestatus();
	        }
		 try { hiddendangerVo.setVerifymen(userVo.getId());//核实人员
		// hiddendangerVo.setDeptid(deptVo.getId());
		 hiddendangerVo.setVerifydate(DateUtil.getDateyyyy_mm_dd_hh_mi_s());//核实日期
		 hiddendangerVo.setHandlestatus("20");//处理标识
			
		 hiddendangerService.verifyDanger(hiddendangerVo);
	
			 map.put("msg", "成功");
			 total=1; 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);
		 //return map;
	 }
	 /**
	  * 处理隐患整改信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doDangerRectify",method=RequestMethod.POST)
	 //@ResponseBody
	 public   void doDangerRectify(HiddendangerVo hiddendangerVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/safe/hiddendanger/doDangerRectify", "隐患整改", "处理", "");
		 Map<String, Object> map=new HashMap<String, Object>();  
		 
		 int total=0;
		 String handlestatus = "";
	        if(
	        		hiddendangerVo!=null&&hiddendangerVo.getHandlestatus()!=null&&!hiddendangerVo.getHandlestatus().equals("")){
	        	handlestatus = hiddendangerVo.getHandlestatus();
	        }
		 try { hiddendangerVo.setRectifyid(userVo.getId());//整改记录人员
		 hiddendangerVo.setHandlestatus("30");//处理标识
		 hiddendangerVo.setDangerstatus("10");//隐患标识
			
		 hiddendangerService.handleDanger(hiddendangerVo);
			 map.put("msg", "成功");
			 total=1; 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
			map.put("msg", "失败");
		}
		 map.put("total", total);
		 String result = JSON.toJSONString(map);
		 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write(result);
		 //return map;
	 }
	 /**
	  * 隐患记录新增信息
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="doHangercreateNew",method=RequestMethod.POST)
	// @ResponseBody
	 public   void doHangercreateNew(HiddendangerVo hiddendangerVo,HttpServletResponse response,HttpServletRequest request) throws Exception {
		 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
		 operationlogService.insertLog(userVo, "/safe/hiddendanger/doHangercreateNew", "隐患记录", "新增", "");
		 Map<String, Object> map=new HashMap<String, Object>();  
		 int total=0;
        
		 try { hiddendangerVo.setCreateid(userVo.getId());//记录人
		 //hiddendangerVo.setStatus("0");
		// hiddendangerVo.setDangerstatus("10");
		 hiddendangerVo.setHandlestatus("10");
		 hiddendangerService.insertHangercreate(hiddendangerVo);
			 map.put("msg", "成功");
			 total=1;
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
}
