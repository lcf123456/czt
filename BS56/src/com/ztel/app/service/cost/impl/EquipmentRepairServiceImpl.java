package com.ztel.app.service.cost.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.cost.EquipmentRepairVoMapper;
import com.ztel.app.service.cost.EquipmentRepairService;
import com.ztel.app.vo.cost.EquipmentRepairVo;
import com.ztel.framework.vo.Pagination;
@Service
public class EquipmentRepairServiceImpl implements EquipmentRepairService {

	
	@Autowired
	private EquipmentRepairVoMapper equipmentrepairVoMapper = null;
	
	 
	 private Map<String, String> sortKeyMapping = new HashMap<>();
		
		public EquipmentRepairServiceImpl() {
			//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
		//sortKeyMapping.put(key, value)
			sortKeyMapping.put("equipname", "equipname");
		}
		
	@Override
	
public List<EquipmentRepairVo> selectEquipmentRepairPageList(Pagination<?> page) {
		
		page.sortKeyToColumn(sortKeyMapping);
		return this.equipmentrepairVoMapper.selectEquipmentRepairPageList(page);
	}

	@Override
	public int updateEquipmentRepair(EquipmentRepairVo equipmentrepairVo) {
		// TODO Auto-generated method stub
		if (equipmentrepairVo!=null&&!"".equals(equipmentrepairVo.getId())) {
			return equipmentrepairVoMapper.updateByPrimaryKeySelective(equipmentrepairVo);
		}
		return 0;
	}

	@Override
	public int delEquipmentRepair(List<Integer> ids) {
		// TODO Auto-generated method stub
		if(ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				this.equipmentrepairVoMapper.deleteByPrimaryKey(id);
			}
			return ids.size();
		}
		return 0;
	}

	@Override
	public int insertEquipmentRepair(EquipmentRepairVo equipmentrepairVo) {
		// TODO Auto-generated method stub
		if (equipmentrepairVo!=null/*&&!"".equals(equipmentrepairVo.getId())*/) {
			return equipmentrepairVoMapper.insertSelective(equipmentrepairVo);
		}
		return 0;
	}

	//@Override
	public List<EquipmentRepairVo> getEquipmentRepairVoListByEquipId(Integer equipid) {
		// TODO Auto-generated method stub
		List<EquipmentRepairVo> equipmentrepairList = equipmentrepairVoMapper.getEquipmentRepairListByEquipId(equipid);
		return equipmentrepairList;
	}
}
