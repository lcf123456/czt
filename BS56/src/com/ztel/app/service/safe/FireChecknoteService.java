package com.ztel.app.service.safe;

import java.util.List;

import com.ztel.app.vo.safe.FireChecknoteVo;
import com.ztel.framework.vo.Pagination;

public interface FireChecknoteService {

	List<FireChecknoteVo> searchFireChecknotePageList(Pagination<?> page);
}
