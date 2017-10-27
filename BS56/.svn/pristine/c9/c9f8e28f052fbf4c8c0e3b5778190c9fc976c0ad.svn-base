package com.ztel.app.service.safe.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.safe.HazardsVoMapper;
import com.ztel.app.service.safe.HazardsService;
import com.ztel.app.vo.safe.HazardsVo;
import com.ztel.framework.vo.Pagination;


@Service
public class HazardsServiceImpl implements HazardsService {
	@Autowired
	private HazardsVoMapper hazardsVoMapper = null;
    private Map<String, String> sortKeyMapping = new HashMap<>();
	
    public HazardsServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("hazards", "hazards");
		sortKeyMapping.put("id", "id");
	}
	
	
	@Override
	public List<HazardsVo> searchHazardsPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.hazardsVoMapper.getHazardsPageList(page);
	}
	
	@Override
	public List<HazardsVo> searchHazardsAuditPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.hazardsVoMapper.getHazardsAuditPageList(page);
	}
	
	@Override
	public List<HazardsVo> searchHazardsControlPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.hazardsVoMapper.getHazardsControlPageList(page);
	}
	@Override
	public List<HazardsVo> searchHazardsStatPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.hazardsVoMapper.getHazardsStatPageList(page);
	}

}
