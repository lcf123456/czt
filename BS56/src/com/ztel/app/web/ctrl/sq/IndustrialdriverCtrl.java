package com.ztel.app.web.ctrl.sq;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ztel.app.service.sq.CigfactoryService;
import com.ztel.app.service.sq.IndustrialdriverService;
import com.ztel.app.service.sq.impl.CigfactoryServiceImpl;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.sq.CigfactoryVo;
import com.ztel.app.vo.sq.IndustrialdriverVo;
import com.ztel.app.vo.sq.TouchFeedbackVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.util.StringUtils;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;
/** 
 * 工业司机信息
 * @author admin
 *
 */
	@Controller
	@RequestMapping("/sq/industrialdriver")
	public class IndustrialdriverCtrl extends BaseCtrl {
		
		private static Logger logger = LogManager.getLogger(IndustrialdriverCtrl.class);
		
		@Autowired
		private IndustrialdriverService industrialdriverService = null;
		@Autowired
		private OperationlogService operationlogService = null;
		@Autowired
		private CigfactoryService cigfactoryService = null;
		
		@RequestMapping("/toIndustrialdrivers")
		public String index(HttpServletRequest request) {
			
			return "/sq/v_industrialdriver";
		}
		@RequestMapping(value="/toIndustrialdriverNew",method=RequestMethod.GET)
		public String popWindow() throws Exception{
			return "/sq/v_industrialdriver_new";
		}
		
		/**
		  * 获取角色列表
		  * @return
		  * @throws Exception
		  */
		 @RequestMapping("getIndustrialdrivers")
		 @ResponseBody
		 public  Map<String, Object> getIndustrialdriverList(String drivername,/*DataGridModel dgm,*/IndustrialdriverVo industrialdriver,HttpServletRequest request) throws Exception {
			 Pagination<?> page = this.getPagination(request);
//			// System.out.println("重置之前numPerPage="+page.getNumPerPage()+","+page.getSortColumn()+",isSortAsc="+page.isSortAsc()); 
//			 //按照DataGrid的分页重新设置分页参数
//			 if (dgm!=null&&dgm.getSort()!=null&&dgm.getSort()!="") {
//				 page.setSortKey(dgm.getSort());//设置排序字段
//				
//				 boolean isSortAsc=false;
//				 if(dgm.getOrder()!=null&&dgm.getOrder()!=""&&dgm.getOrder().equals("asc"))isSortAsc=true;
//				 page.setSortAsc(isSortAsc);//设置是否正序排练
//				// System.out.println("dgm sort= "+dgm.getSort()+",order ="+dgm.getOrder()+",isSortAsc="+isSortAsc+",pageNum="+dgm.getPage()+",getRows="+dgm.getRows()); 
//				 page.setNumPerPage(dgm.getRows());//设置每页显示记录数
//			}

			 Map<String, Object> result=new HashMap<String, Object>();  
				
			 if (industrialdriver!=null) {
				 System.out.println("industrialdriver="+industrialdriver.getDrivername()+","+industrialdriver.getId()); 
				 page.setParam(industrialdriver);
			}
			 List<IndustrialdriverVo> ones = industrialdriverService.searchIndustrialdriverList(page);
			 
			 int totals=0;

			 result.put("rows",ones);  
			 result.put("total",page.getTotalCount());  

			 return result;
		 }
		 	
		 /**
		  * 删除工业司机信息
		  * @return
		  * @throws Exception
		  */
		 @RequestMapping(value="doindustrialdriverdelete",method=RequestMethod.POST)
		 @ResponseBody
		 public   Map<String, Object> Industrialdriverdelete(@RequestParam("id") List<Integer> id,HttpServletRequest request) throws Exception {
			 Map<String, Object> map=new HashMap<String, Object>();  
			 int total=1;
			 if (id!=null&&id.size()>0) {
				 total = id.size();
			}
			 try {
				 industrialdriverService.deleteIndustrialdriverById(id);
				 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
				 operationlogService.insertLog(userVo, "/sq/industrialdriver/doindustrialdriverdelete", "工业司机信息", "删除", "");
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
		  * 新增工业司机信息
		  * @return
		  * @throws Exception
		  */
		 @RequestMapping(value="doindustrialdriverNew",method=RequestMethod.POST)
		// @ResponseBody
		 public   void industrialdriverNew(IndustrialdriverVo industrialdriver,HttpServletResponse response,HttpServletRequest request) throws Exception {
			 Map<String, Object> map=new HashMap<String, Object>();  
			 int total=1;
	        
			// System.out.println("industrialdriverid="+industrialdriver.getId()+",driverName="+industrialdriver.getDrivername()+",remarks="+industrialdriver.getRemarks());
			 try {
				// CigfactoryVo cvo = CigfactoryService.get(industrialdriver.getFactoryid());
				//industrialdriver.setFactoryname(cvo.getFacXXX()); 
				industrialdriverService.newIndustrialdriver(industrialdriver);
				UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
				 operationlogService.insertLog(userVo, "/sq/industrialdriver/doindustrialdriverNew", "工业司机信息", "新增", "");
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

			 //return result;
		 }
		 
		 /**
		  * 修改工业司机信息
		  * @return
		  * @throws Exception
		  */
		 @RequestMapping(value="doindustrialdriverUpdate",method=RequestMethod.POST)
		 //@ResponseBody
		 public   void industrialdriverUpdate(IndustrialdriverVo industrialdriver,HttpServletResponse response,HttpServletRequest request) throws Exception {
			 Map<String, Object> map=new HashMap<String, Object>(); 
			 int total=0;
	        
			 System.out.println("industrialdriverid="+industrialdriver.getId()+",driverName="+industrialdriver.getDrivername()+",id="+industrialdriver.getId());
			 try {
				 industrialdriverService.updateIndustrialdriver(industrialdriver);
				 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
				 operationlogService.insertLog(userVo, "/sq/industrialdriver/doindustrialdriverUpdate", "工业司机信息", "修改", "");
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
	  * 获取厂家名称下拉列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("getCigfactoryCombobox")
	 @ResponseBody
	 public  List<HashMap<String, String>> getCigfactoryCombobox(HttpServletRequest request) throws Exception {
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		boxList=cigfactoryService.getCigfactoryCombobox();
		 return boxList;
	 }
	 
	 }

