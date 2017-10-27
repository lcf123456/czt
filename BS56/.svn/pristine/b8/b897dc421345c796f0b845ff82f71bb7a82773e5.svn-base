package com.ztel.app.service.wms;

import java.util.List;

import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.wms.ATSCellInfoDetailVo;
import com.ztel.app.vo.wms.InventoryLineVo;

public interface InventoryLineVoService {

	public int doInventoryLineAdd(InventoryLineVo inventoryLineVo);
	
	public void doInventoryComplete(String inventoryId,List<ATSCellInfoDetailVo> atsCellList,List<InventoryLineVo> scatteredList,List<SortTroughVo> troughList,List<SortTroughVo> shelfList);

	public List<InventoryLineVo> getLastInventoryInfoByCond(InventoryLineVo inventoryLineVo);
	
	public List<InventoryLineVo> getInventoryInfoByCond(InventoryLineVo inventoryLineVo);
}
