package com.ztel.app.service.gis;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.gis.TruckseqVo;
import com.ztel.framework.vo.Pagination;

/**
 * 车辆排队
 * @author lcf
 *
 */
public interface TruckseqVoService {
	
	/**
	 * 来烟排队：入园、入单、排队、出园
	 * @param page
	 * @return
	 */
	public List<TruckseqVo> getTruckseqPageList(Pagination<?> page);
	
	/**
	 * 今日装卸
	 * @param page
	 * @return
	 */
	public List<TruckseqVo> getTruckseqTodayPageList(Pagination<?> page);
	
	/**
	 * 装卸效率
	 * @param page
	 * @return
	 */
	public List<TruckseqVo> getTruckseqEfficiencyPageList(Pagination<?> page);
	
	/**
	 * 组装卸效率
	 * @param truckseqVo
	 * @return
	 */
	public List<TruckseqVo> getGroupEfficiencyList(TruckseqVo truckseqVo);
	
	public void updateTruckseqByPrimarykey(TruckseqVo truckseqVo);
	
	public BigDecimal getnextSeq();
}
