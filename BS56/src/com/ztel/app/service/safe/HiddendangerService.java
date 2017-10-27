package com.ztel.app.service.safe;

import java.util.List;

import com.ztel.app.vo.safe.HiddendangerVo;
import com.ztel.app.vo.sys.BlockcustomerVo;
import com.ztel.framework.vo.Pagination;

public interface HiddendangerService {

	List<HiddendangerVo> searchDangercreatPageList(Pagination<?> page);
	List<HiddendangerVo> searchDangerverifyPageList(Pagination<?> page);
	List<HiddendangerVo> searchDangerrectifyPageList(Pagination<?> page);
	public int delHangerrectify(List<Integer> ids);
	public int updateHangerrectify(HiddendangerVo hiddendangerVo);
	public int insertHangercreate(HiddendangerVo hiddendangerVo);
	public int verifyDanger(HiddendangerVo hiddendangerVo);
}
