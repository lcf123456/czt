package com.ztel.app.service.cost;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.cost.SPLTypeInfoVo;

public interface SPLTypeServcie {

	public List<SPLTypeInfoVo> getSPLTypeList(String id);
	
	public void doAddSPLTypeInfo(SPLTypeInfoVo sPLTypeInfoVo);
	
	public int doEditSPLTypeInfo(SPLTypeInfoVo sPLTypeInfoVo);
	
	public int doDelSPLTypeInfo(Integer typeid);
	
	public List<SPLTypeInfoVo> getSPLTypeInfoListByCondition(SPLTypeInfoVo splTypeInfoVo);
	
	public List<HashMap<String, String>> getSPLTypeInfoCombobox(SPLTypeInfoVo splTypeInfoVo);
	
	//领料申请时获取类别，库存为0的类别不再显示
	public List<HashMap<String, String>> getSPLTypeInfoComboboxnForApply(SPLTypeInfoVo splTypeInfoVo);
}
