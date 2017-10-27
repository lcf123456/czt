package com.ztel.app.web.ctrl.produce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.produce.EquipmentFailureVoService;
import com.ztel.app.vo.produce.EquipmentFailureVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

@Controller
@RequestMapping("/produce/equipmentfailure")
public class EquipmentFailureVoCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(EquipmentFailureVoCtrl.class);
	
	@Autowired
	private EquipmentFailureVoService equipmentFailureVoService = null;
	
	@RequestMapping("toEquipmentFailure")
	public String index(HttpServletRequest request) {
		return "/produce/v_equipmentfailure";
	}
		
	@RequestMapping("getEquipmentFailureInfoPageList")
	 @ResponseBody
	public  Map<String, Object> getEquipmentFailureInfoPageList(HttpServletRequest request,EquipmentFailureVo vo)
	{
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		 //System.out.println("------");
		 if (vo!=null) {
			 //System.out.println("roleinfo="+roleinfo.getRolename()+","+roleinfo.getId()); 
			 page.setParam(vo);
		}
		 List<EquipmentFailureVo> paras = equipmentFailureVoService.getEquipmentFailureVoPageList(page);
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	}
	
}
