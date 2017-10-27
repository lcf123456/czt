package com.ztel.app.service.safe;

import java.util.List;


import com.ztel.app.vo.safe.HazardsVo;
import com.ztel.framework.vo.Pagination;

public interface HazardsService {
	List<HazardsVo> searchHazardsPageList(Pagination<?> page);
	List<HazardsVo> searchHazardsAuditPageList(Pagination<?> page);
	List<HazardsVo> searchHazardsControlPageList(Pagination<?> page);
	List<HazardsVo> searchHazardsStatPageList(Pagination<?> page);
}
