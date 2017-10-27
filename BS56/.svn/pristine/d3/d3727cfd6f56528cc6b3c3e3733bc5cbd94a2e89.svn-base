package com.ztel.app.service.wms;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.wms.OutBoundVo;
import com.ztel.framework.vo.Pagination;

public interface OutBoundService {

	public List<OutBoundVo> selectOutboundList(Pagination<?> page);;
	//订单出库
	public void doSave(OutBoundVo outBoundVo);
	
	public void deleteOubboundById(List<BigDecimal> ids);
	/**
	 * 调拨出库
	 * @param outBoundVo
	 * @param id 新增的话设置的id
	 * @param userid 用户id
	 * @param obid 用于判断是首次还是二次新增
	 * @param cigarettecode 卷烟编码
	 * @param cigarettename 卷烟名称
	 * @param jtsize 件条比率
	 */
	public void doSavedb(OutBoundVo outBoundVo,BigDecimal id,String obid,String cigarettecode,String cigarettename,String jtsize);
}
