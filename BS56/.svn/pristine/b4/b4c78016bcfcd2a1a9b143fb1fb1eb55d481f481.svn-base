package com.ztel.app.web.ctrl.gis;

import java.util.ArrayList;
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

import com.ztel.app.service.gis.OrderlineinfoService;
import com.ztel.app.vo.gis.CurrlocationVo;
import com.ztel.app.vo.gis.OrderlineinfoVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.framework.web.ctrl.BaseCtrl;

/**
 * 工业来烟接口数据车辆订单数据
 * @author lcf
 *
 */
@Controller
@RequestMapping("/gis/orderlineinfo")
public class OrderlineinfoCtrl  extends BaseCtrl{
	private static Logger logger = LogManager.getLogger(OrderlineinfoCtrl.class);
	
	@Autowired 
	private OrderlineinfoService orderlineinfoService = null;
	
	@RequestMapping("toOrderlineinfo")
	public String toCurrlocation(HttpServletRequest request) {
		return "/gis/v_orderlineinfo";
	}
	
	@RequestMapping(value="getOrderlineinfoPageList")
	 @ResponseBody
	 public Map<String, Object> getOrderlineinfoPageList(HttpServletRequest request) throws Exception{
		 Map<String, Object> result=new HashMap<String, Object>();  
		 
//		 String keyword = cigarettedamagedVo.getKeyword();
		 String status = request.getParameter("status");
		 String keyword = request.getParameter("keyword");
		 if(status==null||status.equals(""))status="00";

		Pagination<?> page = this.getPagination(request);
		OrderlineinfoVo orderlineinfoVo = new OrderlineinfoVo();
		orderlineinfoVo.setIsArrived(status);
		if(keyword!=null&&!keyword.equals(""))orderlineinfoVo.setKeyword(keyword);
		if (orderlineinfoVo!=null) {
			 page.setParam(orderlineinfoVo);
		}
		List<OrderlineinfoVo> orderlineinfoVoList = new ArrayList<OrderlineinfoVo>();
		orderlineinfoVoList = orderlineinfoService.getOrderlineinfoPageList(page);
		
		 result.put("rows",orderlineinfoVoList);  
		 result.put("total",page.getTotalCount());  
		 
		return result;
	}
}
