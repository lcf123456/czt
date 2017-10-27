package com.ztel.app.service.wms;

import java.util.List;

import com.ztel.app.vo.wms.InBoundLineVo;
import com.ztel.app.vo.wms.InBoundVo;
import com.ztel.framework.vo.Pagination;

public interface InBoundService {

	//public int getIdFromSequence();

	public void doConfiscationImp(List<Integer> inBoundIdList);
	
	public void doInsertInBound(InBoundVo vo);
	
	public void doUpdateInBound(InBoundVo vo);
	
	public void InsertInBound(InBoundVo vo);
	
	public List<InBoundVo> selectInBoundPageList(Pagination<?> page);
	
	public List<InBoundVo> selectInBoundList(InBoundVo inBoundVo);
	
	public void doAddInboundAndLine(InBoundVo inBoundVo,InBoundLineVo inBoundLineVo,String addType);
	
}
