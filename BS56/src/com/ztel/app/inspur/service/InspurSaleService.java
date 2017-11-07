package com.ztel.app.inspur.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.inspur.vo.InspurSalecustomerVo;
import com.ztel.app.vo.sys.UserVo;

public interface InspurSaleService {
	//测试获取零售户列表
	public List<InspurSalecustomerVo> selectInspurCustomerList();
	
	/**
	 * 零售户同步
	 * @return
	 */
	public Map<String, Object> doSyncCustomer(UserVo userVo);
	
	/**
	 * 订单同步
	 * @param userVo
	 * @param orderdate
	 * @return
	 */
	public Map<String, Object> doSyncOrder(UserVo userVo,String orderdate);
	
	/**
	 * 商品信息同步
	 * @param userVo
	 * @param orderdate
	 * @return
	 */
	public Map<String, Object> doSyncItem(UserVo userVo);
	
	/**
	 * 营销扣款同步
	 */
	public Map<String, Object> doSyncsettlementflag(UserVo userVo,String orderdate);

}
