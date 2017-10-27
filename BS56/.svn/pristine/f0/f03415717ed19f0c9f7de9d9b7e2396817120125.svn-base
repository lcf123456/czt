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

import com.ztel.app.service.produce.SortEfficiencyVoService;
import com.ztel.app.vo.produce.SortEfficiencyVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

@Controller
@RequestMapping("/produce/sortefficiency")
public class SortEfficiencyVoCtrl extends BaseCtrl {
	
	private static Logger logger = LogManager.getLogger(SortEfficiencyVoCtrl.class);
	
	@Autowired
	private SortEfficiencyVoService sortEfficiencyVoService = null;
	
	@RequestMapping("toSortEfficiency")
	public String index(HttpServletRequest request) {
		return "/produce/v_sortefficiency";
	}
		
	@RequestMapping("getSortEfficiencyInfoPageList")
	 @ResponseBody
	public  Map<String, Object> getSortEfficiencyInfoPageList(HttpServletRequest request,SortEfficiencyVo vo)
	{
		 Pagination<?> page = this.getPagination(request);

		 Map<String, Object> result=new HashMap<String, Object>();  
		 //System.out.println("------");
		 if (vo!=null) {
			 //System.out.println("roleinfo="+roleinfo.getRolename()+","+roleinfo.getId()); 
			 page.setParam(vo);
		}
		 List<SortEfficiencyVo> paras = sortEfficiencyVoService.getortEfficiencyVoPageList(page);
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  

		 return result;
	}
	
}
