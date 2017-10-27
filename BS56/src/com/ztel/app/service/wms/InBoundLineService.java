package com.ztel.app.service.wms;

import java.util.List;

import com.ztel.app.vo.wms.InBoundLineVo;
import com.ztel.app.vo.wms.InBoundVo;

public interface InBoundLineService {

	public void updateInBoundLine(InBoundLineVo vo);

	public void InsertInBoundLine(InBoundLineVo vo);
	
	public List<InBoundLineVo> selectListByCond(InBoundLineVo inBoundLineVo);
	
	/**
	 * 管控中心：仓储管控：入库汇总
	 * @param inBoundVo
	 * @return
	 */
	public List<InBoundLineVo> selectInboundReportListByCond(InBoundVo inBoundVo);
	
}
