package com.ztel.app.service.account;

import java.util.List;

import com.ztel.app.vo.account.TimebydmVo;
import com.ztel.framework.vo.Pagination;

public interface TimebydmVoService {

	/**
	 * 根据访配模式取日期
	 * @param date 订单日期或送货日期 
	 * @param type 0:取订单日期     1：取送货日期
	 * @return
	 */
	public String getTimebydm(String date,String type);
	/**
	 * 订单配送日期
	 * @param date 订单日期或送货日期
	 * @return
	 */
	public int updateTimebydmdate(TimebydmVo timebydmVo);
	public List<TimebydmVo> getTimebydmdatePageList(Pagination<?> page);
}
