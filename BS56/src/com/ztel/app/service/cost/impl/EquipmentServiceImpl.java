package com.ztel.app.service.cost.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.cost.EquipmentRepairVoMapper;
import com.ztel.app.persist.mybatis.cost.EquipmentVoMapper;
import com.ztel.app.persist.mybatis.sq.IndustrialdriverVoMapper;
import com.ztel.app.service.cost.EquipmentService;
import com.ztel.app.vo.cost.EquipmentVo;
import com.ztel.app.vo.sq.CigfactoryVo;
import com.ztel.app.vo.wms.CustomerVo;
import com.ztel.framework.vo.Pagination;
@Service
public class EquipmentServiceImpl implements EquipmentService {

	
	@Autowired
	private EquipmentVoMapper equipmentVoMapper = null;
	@Autowired
	private EquipmentRepairVoMapper equipmentrepairVoMapper = null;
	 @Autowired 
	 DataSource ds = null;
	 JdbcTemplate template;
	 
	 private Map<String, String> sortKeyMapping = new HashMap<>();
		
		public EquipmentServiceImpl() {
			//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//			sortKeyMapping.put(key, value)
			sortKeyMapping.put("equipname", "equipname");
		}
		
	@Override
	
public List<EquipmentVo> selectEquipmentPageList(Pagination<?> page) {
		
		page.sortKeyToColumn(sortKeyMapping);
		return this.equipmentVoMapper.selectEquipmentPageList(page);
	}

	@Override
	public int updateEquipment(EquipmentVo equipmentVo) {
		if (equipmentVo!=null&&!"".equals(equipmentVo.getId())) {
			return equipmentVoMapper.updateByPrimaryKeySelective(equipmentVo);
		}
		return 0;
	
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delEquipment(List<Integer> ids) {
		// TODO Auto-generated method stub
		if(ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				this.equipmentVoMapper.deleteByPrimaryKey(id);
			}
			return ids.size();
		}
		return 0;
	}

	@Override
	public int insertEquipment(EquipmentVo equipmentVo) {
		// TODO Auto-generated method stub
		if (equipmentVo!=null/*&&!"".equals(equipmentVo.getId())*/) {
			return equipmentVoMapper.insertSelective(equipmentVo);
		}
		return 0;
	}
	//@Override
	///public List<HashMap<String, String>> getEquipmentCombobox() {
		// TODO 自动生成的方法存根
		//List<EquipmentVo> treeList=this.equipmentrepairVoMapper.getEquipmentrepairList();
		// List<HashMap<String, String>> boxList=new ArrayList<>();
		// if (treeList!=null&&treeList.size()>0) {
		//	 for(int i=0;i<treeList.size();i++){
			//	 EquipmentVo vo=treeList.get(i);
			//	 HashMap<String, String> map=new HashMap<String, String>();
			//	map.put("id", vo.getId().toString());
			//	map.put("equipname", vo.getEquipname());
				//boxList.add(map);
			// }
		//}
		 
		//return boxList;
	//}

	@Override
	public List<EquipmentVo> getListEquipname(EquipmentVo equipmentVo) {
		// TODO Auto-generated method stub
		List<EquipmentVo> custList = null;
		custList = equipmentVoMapper.selectListEquipment(equipmentVo);
		return custList;
	}
}
