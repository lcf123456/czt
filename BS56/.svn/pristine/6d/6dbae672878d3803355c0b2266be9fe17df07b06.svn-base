package com.ztel.app.service.wms;

import java.util.List;

import com.ztel.app.vo.produce.SortTroughVo;
import com.ztel.app.vo.wms.ATSCellInfoDetailVo;
import com.ztel.app.vo.wms.InventoryLineVo;
import com.ztel.app.vo.wms.InventorySumVo;
import com.ztel.app.vo.wms.ItemstockVo;

public interface InventoryLineVoService {

	public int doInventoryLineAdd(InventoryLineVo inventoryLineVo);
	
	public void doInventoryComplete(String inventoryId,List<ATSCellInfoDetailVo> atsCellList,List<InventoryLineVo> scatteredList,List<SortTroughVo> troughList,List<SortTroughVo> shelfList);
	
	public void doInventoryCompleteNew(String inventoryId,List<InventorySumVo> sumList,ItemstockVo itemstockVo);

	public List<InventoryLineVo> getLastInventoryInfoByCond(InventoryLineVo inventoryLineVo);
	
	public List<InventoryLineVo> getInventoryTroughInfoByCond(InventoryLineVo inventoryLineVo);
	
	public List<InventoryLineVo> getInventoryInfoByCond(InventoryLineVo inventoryLineVo);
}
