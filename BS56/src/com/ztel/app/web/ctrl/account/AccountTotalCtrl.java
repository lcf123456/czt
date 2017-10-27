package com.ztel.app.web.ctrl.account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.account.AccountTotalService;
import com.ztel.app.service.account.TimebydmVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.account.AccountTotalVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;
@Controller
@RequestMapping("/account/accounttotal")
public class AccountTotalCtrl extends BaseCtrl {
	
	@Autowired
	private AccountTotalService accountTotalService = null;
	
	@Autowired
	private TimebydmVoService timebydmVoService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
		
	@RequestMapping("toAccountTotal")
	public String toOperateStorage(HttpServletRequest request) {
		
		return "/account/v_accounttotal";
	}
	
	@RequestMapping("toVehicleAmount")
	public String toVehicleAmount(HttpServletRequest request) {
		
		return "/account/v_vehicleamount";
	}
	
	    @RequestMapping("getAccountTotalPageList")
		@ResponseBody
		public  Map<String, Object> getAccountTotalPageList(HttpServletRequest request,AccountTotalVo accountTotalVo)
		{
	    	Pagination<?> page = this.getPagination(request);
			Map<String, Object> result=new HashMap<String, Object>();  
			if (accountTotalVo!=null) {
				if(accountTotalVo.getOrderdate() == null){
					String type = request.getParameter("type");
					if(type==null||type.equals(""))type="0";
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					String dateresult = timebydmVoService.getTimebydm(sdf.format(new Date()), type);
					 
//					System.out.println("dateresult="+new Date());
//					 System.out.println("dateresult="+dateresult);
					 try {
						accountTotalVo.setOrderdate(sdf.parse(dateresult));
//						System.out.println("Orderdate="+accountTotalVo.getOrderdate());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				page.setParam(accountTotalVo);
			}
			List<AccountTotalVo> paras = new ArrayList<>();
			paras=accountTotalService.selectAccountTotalList(page);
			
			result.put("rows",paras);  
			 result.put("total",page.getTotalCount());  
			
			return result;
		}
//	 
//	 /**
//	  * 删除
//	  * @return
//	  * @throws Exception
//	  */                                    
//	 @RequestMapping(value="doDelOperate",method=RequestMethod.POST)
//	 // @ResponseBody
//	 public   void doDelOperate(@RequestParam("id") List<Integer> idLst,HttpServletRequest request,HttpServletResponse response) throws Exception {
//		 Map<String, Object> map=new HashMap<String, Object>();  
//		 int total=0;
//		 //System.out.println(idLst.size()+"===="+ordernoLst.size());
//		 try {
//			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
//			 operationlogService.insertLog(userVo, "/account/operate/doDelOperate", "车组退货", "删除", "");
//			 
//			 operateVoService.doOperateDelete(idLst);
//			 
//			 map.put("msg", "成功");
//			 total=idLst.size();
//		 } catch (Exception e) {
//			 // TODO: handle exception
//			 e.printStackTrace();  
//			 map.put("msg", "失败");
//		 }
//		 map.put("total", total);
//		 
//		 //直接使用注解@ResponseBody，框架自动返回json串，但是form形式提交的返回json在IE在会出现下载json的提示，所以修改成设置response的形式
//		 String result = JSON.toJSONString(map);
//		 response.setContentType("text/html;charset=UTF-8");
//		 response.getWriter().write(result);  
//	 }
//	
	@RequestMapping("doAccountTotalUpdate")
	@ResponseBody
	public Map<String, Object> doAccountTotalUpdate(HttpServletRequest request,AccountTotalVo accountTotalVo)
	{
		
		Map<String, Object> map=new HashMap<String, Object>();
		 
		 try{
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/account/accounttotal/doAccountTotalUpdate", "货款核算", "修改", "");
			 
			 accountTotalService.doAccountTotalUpdate(accountTotalVo);
				
			 
			 map.put("msg", "成功");
		 }catch(Exception e){
			 map.put("msg", "失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	}
	
}
