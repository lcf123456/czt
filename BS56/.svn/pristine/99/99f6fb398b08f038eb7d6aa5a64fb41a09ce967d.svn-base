/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sq;

import java.util.List;

import com.ztel.app.vo.sq.EvalitemVo;
import com.ztel.framework.vo.Pagination;

/**
 * @author NJ
 * @since 2017年6月17日 */
public interface EvalItemService {
	
    List<EvalitemVo> getAutoItemPageList(Pagination<?> page);
    
	public int delEvalItem(List<Integer> ids);

	public int updateEvalItem(EvalitemVo evalitemVo);
	
	public int inertEvalItem(EvalitemVo evalitemVo);

	/**
	 * 根据条件查询考核项列表
	 * @param evalitemVo
	 * @return
	 */
    List<EvalitemVo> getItemList(EvalitemVo evalitemVo);
    
}
