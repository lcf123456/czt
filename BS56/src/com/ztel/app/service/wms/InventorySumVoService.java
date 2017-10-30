package com.ztel.app.service.wms;

import java.util.List;

import com.ztel.app.vo.wms.InventorySumVo;

public interface InventorySumVoService {

	public List<InventorySumVo> selectInventoryList(String searchDate,String orderDate);
	
	public List<InventorySumVo> selectInventoryListView(String inventoryid,String searchDate);
	
}
