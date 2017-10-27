package com.ztel.app.service.wms;

import java.util.List;

import com.ztel.app.vo.wms.LanewayVo;
import com.ztel.framework.vo.Pagination;

public interface LanewayService {
	
	public List<LanewayVo> selectLanewayPageList(Pagination<?> page);
	
}
