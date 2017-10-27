package com.ztel.app.web.ctrl.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.account.InvoiceExportVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.account.InvoiceExportVo;
import com.ztel.framework.web.ctrl.BaseCtrl;
@Controller
@RequestMapping("/account/invoiceexport")
public class InvoiceExportVoCtrl extends BaseCtrl {
	
	
	@Autowired
	private InvoiceExportVoService invoiceExportVoService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
		
	@RequestMapping("toInvoiceExport")
	public String index1(HttpServletRequest request) {
			
		return "/account/v_invoiceexport";
	}
	
	@RequestMapping("getInvoiceExportList")
	@ResponseBody
	public  Map<String, Object> getInvoiceExportList(HttpServletRequest request,InvoiceExportVo invoiceExportVo)
	{

		 Map<String, Object> result=new HashMap<String, Object>();  
		 List<InvoiceExportVo> paras = invoiceExportVoService.getInvoiceExportList(invoiceExportVo);
		 result.put("rows",paras);  
		 result.put("total",paras.size());  

		 return result;
	}
	
	@RequestMapping("getOrderDetail")
	@ResponseBody
	public  Map<String, Object> getOrderDetail(HttpServletRequest request,InvoiceExportVo invoiceExportVo)
	{
		
		Map<String, Object> result=new HashMap<String, Object>();  
		List<InvoiceExportVo> paras = invoiceExportVoService.getOrderDetail(invoiceExportVo);
		result.put("rows",paras);  
		result.put("total",paras.size());  
		
		return result;
	}
	
	/**
	 * 导出数据到txt
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doExportDatal")
	 @ResponseBody
	 public  void doExportDatal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 //取参数
		 String begdate=request.getParameter("begdate");
		 String enddate=request.getParameter("enddate");
		 String billtype=request.getParameter("billtype");
		 //取要导出的id串
		 String ids=request.getParameter("ids");
		 //将id字符串转换成int数组
		 String[]arr=ids.split(",");
		 int len=arr.length;
		 String[]idarr=new String[len];
		 for(int i=0;i<len;i++){
			 idarr[i]=arr[i];
		 }
		 
		 //多参数用map传值
		 Map<String,Object> map=new HashMap<>();
		 map.put("billtype", billtype);
		 map.put("ids", idarr);
		 map.put("begdate", begdate);
		 map.put("enddate", enddate);
		 try{
			 
			     invoiceExportVoService.doExportDataToTxt(map, response);
//				 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
//				 operationlogService.insertLog(userVo, "/cost/suppliesimp/doInsertSuppliesImp", "物资管理", "新增", "");
				 map.put("msg", "成功");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();  
				map.put("msg", "失败");
			}
			 
			 //直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
//			 String result = JSON.toJSONString(map);
//			 response.setContentType("text/html;charset=UTF-8");
//			 response.getWriter().write(result);  
		}
}
