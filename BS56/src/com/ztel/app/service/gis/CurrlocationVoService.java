package com.ztel.app.service.gis;

import java.util.List;

import com.ztel.app.vo.gis.CurrlocationVo;
import com.ztel.framework.vo.Pagination;

public interface CurrlocationVoService {

	public List<CurrlocationVo> getCurrlocationPageList(Pagination<?> page);
}
