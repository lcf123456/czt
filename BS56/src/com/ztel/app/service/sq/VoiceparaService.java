/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sq;

import java.util.List;

import com.ztel.app.vo.sq.VoiceparaVo;
import com.ztel.framework.vo.Pagination;

/**
 * @author yy
 * @since 2017年5月24日
 */
public interface VoiceparaService {
	
	public List<VoiceparaVo> getVoiceparaPageList(Pagination<?> page);



	public int insertVoicepara(VoiceparaVo voiceparaVo);

	public int updateVoicepara(VoiceparaVo voiceparaVo);
}
