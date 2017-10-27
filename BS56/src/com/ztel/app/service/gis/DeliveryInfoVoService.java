package com.ztel.app.service.gis;

import java.util.List;

import com.ztel.app.vo.gis.DeliveryInfoVo;

/**
 * 
 * @author NJ
 *
 */
public interface DeliveryInfoVoService {
	
	public List<DeliveryInfoVo> getDeliveryInfoList(String searchdate);
	
}
