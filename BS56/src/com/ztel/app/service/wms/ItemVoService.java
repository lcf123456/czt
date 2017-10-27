package com.ztel.app.service.wms;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.wms.ItemVo;

public interface ItemVoService {

	public List<ItemVo> selectConfiscationListByCond(ItemVo itemVo);
	
	public List<ItemVo> selectItemListByCond(ItemVo itemVo);
	
	public List<HashMap<String, String>> getItemComboboxByCond(ItemVo itemVo);
	
}
