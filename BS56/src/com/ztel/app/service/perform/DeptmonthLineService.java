package com.ztel.app.service.perform;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.perform.DeptmonthLineVo;

public interface DeptmonthLineService {
	
	public List<DeptmonthLineVo> selectDeptmonthLineByCond(DeptmonthLineVo deptmonthLineVo);
	
	/**
	 * 修改
	 * @param deptmonthsumVo
	 */
	public void doEdit(DeptmonthLineVo deptmonthLineVo);
	
	/**
	 * 部长审核
	 * @param deptmonthsumVo
	 */
	public void doOneAudit(DeptmonthLineVo deptmonthLineVo);
	
	/**
	 * 总经理评
	 * @param deptmonthsumVo
	 */
	public void doEndAudit(DeptmonthLineVo deptmonthLineVo);
	
	public void doDel(BigDecimal inboundid);
}
