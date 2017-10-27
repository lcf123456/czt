package com.ztel.app.web.ctrl.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.account.DistributionModeVoService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.vo.account.DistributionModeVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.web.ctrl.BaseCtrl;
@Controller
@RequestMapping("/account/distributionmode")
public class DistributionModeVoCtrl extends BaseCtrl {
	
	
	@Autowired
	private DistributionModeVoService distributionModeVoService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
		
	@RequestMapping("toDistributionMode")
	public String index1(HttpServletRequest request) {
			
		return "/account/v_distributionmode";
	}
	
	@RequestMapping("getDistributionModes")
	@ResponseBody
	public  Map<String, Object> getDistributionModes(HttpServletRequest request)
	{

		 Map<String, Object> result=new HashMap<String, Object>();  
		 List<DistributionModeVo> paras = distributionModeVoService.getDIstributionModes();
		 result.put("rows",paras);  
		 result.put("total",paras.size());  

		 return result;
	}
	
	@RequestMapping("doChangeMode")
	@ResponseBody
	public Map<String, Object> doChangeMode(HttpServletRequest request,DistributionModeVo distributionModeVo)
	{
		
		Map<String, Object> map=new HashMap<String, Object>();
		 
		 try{
			 UserVo userVo = (UserVo)request.getSession().getAttribute("userVo");
			 operationlogService.insertLog(userVo, "/account/distributionmode/doChangeMode", "访配模式", "修改", "");
			 distributionModeVoService.doChangeMode(distributionModeVo);
			 
			 map.put("msg", "成功");
		 }catch(Exception e){
			 map.put("msg", "失败");
			 e.printStackTrace();
		 }
		 map.put("total", "1");
		 return map;
	}
	
}
