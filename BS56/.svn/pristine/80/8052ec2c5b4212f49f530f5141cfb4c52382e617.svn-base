package com.ztel.app.service.safe.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.safe.VehicleAccidentVoMapper;
import com.ztel.app.service.safe.VehicleAccidentService;
import com.ztel.app.vo.safe.VehicleAccidentVo;
import com.ztel.framework.vo.Pagination;

@Service
public class VehicleAccidentServiceImpl implements VehicleAccidentService {
	
	@Autowired
	private VehicleAccidentVoMapper vehicleaccidentVoMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public VehicleAccidentServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
        //sortKeyMapping.put(key, value)
		//sortKeyMapping.put("deliveryseq", "deliveryseq");
	}

	/**
	 * 取事故列表信息
	 */
	@Override
	public List<VehicleAccidentVo> getAccidentPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		//page.sortKeyToColumn(sortKeyMapping);
		List<VehicleAccidentVo> custList = vehicleaccidentVoMapper.getAccidentPageList(page);
		return custList;
	}
	/**
	 * 查看事故信息
	 */
	@Override
	public void viewAccident(VehicleAccidentVo vehicleaccidentvo) {
		// TODO 自动生成的方法存根
		
	}

}
