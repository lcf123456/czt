package com.ztel.app.service.sys;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.sys.BasetypeInfoVo;


/**
 * 
 * @author NJ
 *
 */
public interface BaseTypeInfoService {
	
	/**
	 * 取对应typname的信息
	 * @param 
	 * @return
	 */
	public List<BasetypeInfoVo> getBaseTypeinfoList(String typeCode);
	/**
	 * 取对应typname的信息的下拉列表
	 * @return
	 */
	public List<HashMap<String, String>> getBaseTypeinfoCombobox(String typeCode) ;
	
	/**
	 * 新增信息
	 * @param basetypeInfoVo
	 */
	public void doBasetypeInfoNew(BasetypeInfoVo basetypeInfoVo);
	
	/**
	 * 修改信息
	 * @param basetypeInfoVo
	 */
	public void doBasetypeInfoUpdate(BasetypeInfoVo basetypeInfoVo);

	/**
	 * 删除信息
	 * @param basetypeInfoVo
	 */
	public void doBasetypeInfoDel(List<Integer> ids);
	
	
	public List<BasetypeInfoVo> getTypeList() ;
	
}
