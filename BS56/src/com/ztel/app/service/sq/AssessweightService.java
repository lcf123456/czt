/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sq;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.sq.AssessweightVo;
import com.ztel.framework.vo.Pagination;

/**
 * @author sn
 * @since 2017年6月14日
 */
public interface AssessweightService {
	public int deleteAssessweightById(Integer id,AssessweightVo assessweightVo);
	public List<AssessweightVo> getAssessweightList(Pagination<?> page);
}
