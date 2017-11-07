package com.ztel.app.service.wms;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.sys.DeptVo;
import com.ztel.app.vo.wms.ItemVo;
import com.ztel.framework.vo.Pagination;

public interface ItemService {
	List<ItemVo> selectListByCond(ItemVo itemVo);
	public List<ItemVo> getBrandinfoList(Pagination<?> page);
	public int updateBrandinfo(ItemVo itemVo);
	/**
	 * 获取卷烟下拉框数据
	 * @param itemVo
	 * @return
	 */
	public List<HashMap<String, String>> getItemComboboxByCond(ItemVo itemVo);
	public List<ItemVo> getIteminfoList(Pagination<?> page);
	public int insertIteminfo(ItemVo itemVo);
	public int delIteminfo(List<Integer> ids);
}
