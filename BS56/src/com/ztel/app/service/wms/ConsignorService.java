package com.ztel.app.service.wms;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.wms.ConsignorVo;

public interface ConsignorService {

	public List<ConsignorVo>  getConsignorList(ConsignorVo consignorVo);
	
//	public List<ConsignorVo>  getConsignorPageList(Pagination<?> page);

	//public void viewConsignorVo(ConsignorVo consignorvo);
	
	public List<HashMap<String, String>> getConsignsorComboboxByCond(ConsignorVo consignorVo);
	
}
