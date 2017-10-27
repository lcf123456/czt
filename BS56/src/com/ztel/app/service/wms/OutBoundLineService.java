package com.ztel.app.service.wms;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.wms.OutBoundLineVo;
import com.ztel.app.vo.wms.OutBoundVo;

public interface OutBoundLineService {

	List<OutBoundLineVo> selectByOutboundid(BigDecimal outboundid);
	
	/**
	 * 管控中心：仓储管控：出库汇总
	 * @param outBoundVo
	 * @return
	 */
	public List<OutBoundLineVo> selectOutboundReportListByCond(OutBoundVo outBoundVo);
}
