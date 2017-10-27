package com.ztel.app.service.safe;

import java.util.List;

import com.ztel.app.vo.safe.FireFacilitiesVo;
import com.ztel.framework.vo.Pagination;

public interface FireFacilitiesService {

	List<FireFacilitiesVo> searchFireFacilitiesPageList(Pagination<?> page);
}
