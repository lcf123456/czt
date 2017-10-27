package com.ztel.app.web.ctrl.safe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztel.app.service.safe.FireFacilitiesService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.safe.FireFacilitiesVo;
import com.ztel.app.vo.wms.ATSCellInfoDetailVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;
/**
 * @author yy
 * @since 2017年10月11日
 *设施台账
 */
@Controller
@RequestMapping("/safe/firefacilities")
public class FireFacilitiesCtrl extends BaseCtrl {
	private static Logger logger = LogManager.getLogger(FireFacilitiesCtrl.class);
	
	@Autowired
	private FireFacilitiesService firefacilitiesService = null;
	
	@RequestMapping("toFireFacilities")
	public String index(HttpServletRequest request) {
			
		return "/safe/v_facilities";
	}
	
	
	
	
	/**
	  * 获取列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value="getFireFacilities")
	 @ResponseBody
	 public  Map<String, Object> getFireFacilities(FireFacilitiesVo firefacilitiesVo,HttpServletRequest request) throws Exception {
		 Pagination<?> page = this.getPagination(request);
		 Map<String, Object> result=new HashMap<String, Object>();  
		 if (firefacilitiesVo!=null) {
			 page.setParam(firefacilitiesVo);
		}
		 List<FireFacilitiesVo> paras = firefacilitiesService.searchFireFacilitiesPageList(page);
		 System.out.println(paras.size());
		 result.put("rows",paras);  
		 result.put("total",page.getTotalCount());  
		 return result;
	 }	
	 
	 
}
