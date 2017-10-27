package com.ztel.app.service.gis.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.gis.TruckseqVoMapper;
import com.ztel.app.service.gis.TruckseqVoService;
import com.ztel.app.vo.gis.TruckseqVo;
import com.ztel.framework.vo.Pagination;

@Service
public class TruckseqVoServiceImpl implements TruckseqVoService {

	@Autowired
	private TruckseqVoMapper truckseqVoMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public TruckseqVoServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("vehicleno", "vehicleno");
		sortKeyMapping.put("seq", "seq");
		sortKeyMapping.put("companyname", "companyname");
		sortKeyMapping.put("backtime", "backtime");
	}
	
	@Override
	public List<TruckseqVo> getTruckseqPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return truckseqVoMapper.getTruckseqPageList(page);
	}
	
	@Override
	public List<TruckseqVo> getTruckseqTodayPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return truckseqVoMapper.getTruckseqTodayPageList(page);
	}
	
	@Override
	public List<TruckseqVo> getTruckseqEfficiencyPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return truckseqVoMapper.getTruckseqEfficiencyPageList(page);
	}
	
	public void updateTruckseqByPrimarykey(TruckseqVo truckseqVo){
		truckseqVoMapper.updateByPrimaryKeySelective(truckseqVo);
	}
	
	/**
	 * 组装卸效率
	 */
	public List<TruckseqVo> getGroupEfficiencyList(TruckseqVo truckseqVo){
		List<TruckseqVo> truckseqVoList = truckseqVoMapper.getGroupEfficiencyList(truckseqVo);
		

		return truckseqVoList;
	}
}
