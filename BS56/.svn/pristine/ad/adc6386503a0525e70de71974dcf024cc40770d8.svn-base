package com.ztel.app.service.gis;

import java.util.HashMap;
import java.util.List;


import com.ztel.app.vo.gis.GroupinfoVo;
import com.ztel.framework.vo.Pagination;


/**
 * 
 * @author SN
 *
 */
public interface GroupinfoVoService {
	/**
	 * 取配送车辆下拉框
	 * @return
	 */
	public List<HashMap<String, String>>getGroupinfoCombobox();
	/**
	 * 数据翻页
	 * @return
	 */
	
	public int delGroupinfoVo(List<String> groupcodes);

	public int insertGroupinfoVo(GroupinfoVo groupinfoVo);

	public int updateGroupinfoVo(GroupinfoVo groupinfoVo);
	
	List<GroupinfoVo> getGroupinfoVoList(GroupinfoVo groupinfoVo);
	
	/**
	 * 取装卸组所有记录，未做group的查询
	 * @param groupinfoVo
	 * @return
	 */
	List<GroupinfoVo> selectAllGroupinfoList(GroupinfoVo groupinfoVo);
	
}
