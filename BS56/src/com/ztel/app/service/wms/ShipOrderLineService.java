package com.ztel.app.service.wms;

import java.util.List;

import com.ztel.app.vo.wms.ShipOrderLineVo;

public interface ShipOrderLineService {

	public List<ShipOrderLineVo> getShipOrderLineByOrderNo(String orderNo);
	
	public int doUpdateShipOrderLine(ShipOrderLineVo shipOrderLineVo);
	
	/**
	 * 工商协同：品牌汇总
	 * @param orderdatestr
	 * @return
	 */
	public List<ShipOrderLineVo> getShiporderBrandReport(String orderdatestr);
	
	
}
