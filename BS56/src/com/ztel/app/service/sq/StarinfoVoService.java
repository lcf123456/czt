/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sq;

import java.util.List;

import com.ztel.app.vo.sq.StarinfoVo;
import com.ztel.framework.vo.Pagination;

/**
 * @author Sn
 * @since 2017年4月25日
 */
public interface StarinfoVoService {
	
	public List<StarinfoVo> getStarinfoPageList(Pagination<?> page);

	public int delStarinfo(List<Integer> ids);

	public int insertStarinfo(StarinfoVo starinfoVo);

	public int updateStarinfo(StarinfoVo starinfoVo);
}
