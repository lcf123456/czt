package com.ztel.app.service.wms;

import java.util.List;

import com.ztel.app.vo.wms.InBoundLineVo;
import com.ztel.app.vo.wms.InBoundVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.webservice.wms.vo.WMSBillscanLineVo;
import com.ztel.webservice.wms.vo.WMSBillscanVo;

public interface InBoundService {

	//public int getIdFromSequence();

	public void doConfiscationImp(List<Integer> inBoundIdList);
	
	public void doInsertInBound(InBoundVo vo);
	
	public void doUpdateInBound(InBoundVo vo);
	
	public void InsertInBound(InBoundVo vo);
	
	/**
	 * 从一号工程接收到的数据插入入库单以及明细表
	 * @param vo
	 * @param lineist
	 * int 1：成功 0：失败
	 */
	public int doInsertInBoundAndLineList(WMSBillscanVo vo,List<WMSBillscanLineVo> lineist);
	
	public List<InBoundVo> selectInBoundPageList(Pagination<?> page);
	
	public List<InBoundVo> selectInBoundList(InBoundVo inBoundVo);
	
	public void doAddInboundAndLine(InBoundVo inBoundVo,InBoundLineVo inBoundLineVo,String addType);
	
	public void doDestroyInbound(List<String> inboundidLst);
	
	public void doUpdateInboundNumById(InBoundVo inBoundVo);
	
}
