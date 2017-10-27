package com.ztel.app.service.produce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.produce.SortEfficiencyVoMapper;
import com.ztel.app.service.produce.SortEfficiencyVoService;
import com.ztel.app.vo.produce.SortEfficiencyVo;
import com.ztel.framework.vo.Pagination;

@Service
public class SortEfficiencyVoServiceImpl implements SortEfficiencyVoService{

    @Autowired
    private SortEfficiencyVoMapper sortEfficiencyVoMapper =null;

	@Override
	public List<SortEfficiencyVo> getortEfficiencyVoPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		return sortEfficiencyVoMapper.getSortEfficiencyPageList(page);
	}

}
	
