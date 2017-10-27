package com.ztel.app.service.wms;

import java.util.List;

import com.ztel.app.vo.wms.InventoryLineVo;
import com.ztel.app.vo.wms.StorageAreaInOutVo;

public interface StorageAreaInOutService {

	public int doInsert(StorageAreaInOutVo storageAreaInOutVo);
	
	public void doRefund(List<Integer> operateIdList,List<String> ordernoList);
	
	public List<StorageAreaInOutVo> getLastStorageAreaInOutInfoByCond(StorageAreaInOutVo storageAreaInOutVo);
	
	public List<StorageAreaInOutVo> getLastCellInOutInfoByCond(StorageAreaInOutVo storageAreaInOutVo);
	
	public List<InventoryLineVo> getInOutInfoSummaryByCond(StorageAreaInOutVo storageAreaInOutVo,InventoryLineVo inventoryLineVo);
}
