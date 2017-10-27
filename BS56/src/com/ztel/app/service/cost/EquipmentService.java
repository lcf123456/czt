package com.ztel.app.service.cost;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.cost.EquipmentVo;
import com.ztel.app.vo.sys.BlockcustomerVo;
import com.ztel.framework.vo.Pagination;

public interface EquipmentService {
	
	public List<EquipmentVo> selectEquipmentPageList(Pagination<?> page);

	public int updateEquipment(EquipmentVo equipmentVo);
	public int delEquipment(List<Integer> ids);

	public int insertEquipment(EquipmentVo equipmentVo);

	//List<HashMap<String, String>> getEquipmentCombobox();

	public List<EquipmentVo> getListEquipname(EquipmentVo equipmentVo);

	
}
