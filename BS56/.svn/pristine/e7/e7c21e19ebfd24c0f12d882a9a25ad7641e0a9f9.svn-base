package com.ztel.app.service.produce;

import java.util.List;

import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.wms.InventoryLineVo;
import com.ztel.app.vo.wms.StorageAreaInOutVo;
import com.ztel.framework.vo.Pagination;

public interface SortTroughService {
	
	public List<SortTroughVo> getSortTroughPageList(Pagination<?> page);
	public void updateByPrimaryKeySelective(SortTroughVo vo);
	public List<SortTroughVo> getSorttroughList(SortTroughVo sorttroughVo);
	
	public List<SortTroughVo> selectListByCond(SortTroughVo sortTroughVo);
	
	public List<SortTroughVo> getSortTroughSummaryByCond(StorageAreaInOutVo storageAreaInOutVo,
			SortTroughVo sortTroughVo);
	
	public int updateMantissaByCond(SortTroughVo sortTroughVo);
}
