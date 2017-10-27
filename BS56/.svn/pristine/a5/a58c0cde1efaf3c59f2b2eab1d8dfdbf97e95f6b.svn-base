package com.ztel.app.service.sys;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.sys.BaseMultitypeInfoVo;
import com.ztel.app.vo.sys.BasetypeInfoVo;
import com.ztel.app.vo.sys.MenuInfoVo;


/**
 * 
 * @author NJ
 *
 */
public interface BaseMultitypeInfoService {
	
	/**
	 * 根据父id获取列表
	 * @param parentId
	 * @return
	 */
	public List<BaseMultitypeInfoVo> searchBaseMultitypeInfoList(BaseMultitypeInfoVo record);
	
	public void doAddMultitype(BaseMultitypeInfoVo baseMultitypeInfoVo);
	
	public void doEditMultitype(BaseMultitypeInfoVo baseMultitypeInfoVo);
	
	/**
	 * 根据条件获取下拉框
	 * @param baseMultitypeInfoVo
	 * @return
	 */
	public List<HashMap<String, String>> getMultitypeCombobox(BaseMultitypeInfoVo baseMultitypeInfoVo);
}
