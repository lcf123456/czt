package com.ztel.app.service.wms;

import java.util.Date;
import java.util.List;

import com.ztel.app.vo.wms.ItemstockLineVo;
import com.ztel.app.vo.wms.ItemstockVo;

public interface ItemstockService {
	
	/**
	 * 插入卷烟库存账面量表
	 * @param itemstockVo 
	 */
	public void insertItemstock(ItemstockVo itemstockVo);
	

	/**
	 * 取盘点时间为止的库存账面量,用于前台界面各货主账面量的显示,比getStock多了一行合计
	 * @param inventortime 盘点的当前时间
	 * @param consignsor 货主
	 * @return
	 */
	public List<ItemstockLineVo> selectItemstockLineList(Date inventortime,String consignsor);
	
	/**
	 * 取盘点时间为止的库存账面量,用于盘点时账面量核对
	 * @param inventortime 盘点的当前时间
	 * @param consignsor 货主
	 * @return
	 */
	public List<ItemstockLineVo> getStock(Date inventortime,String consignsor);

}
