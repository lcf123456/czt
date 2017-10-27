package com.ztel.app.service.gis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.gis.RegionVoMapper;
import com.ztel.app.service.gis.RegionVoService;
import com.ztel.app.vo.gis.RegionVo;
@Service
public class RegionVoServiceImpl implements RegionVoService{
	
	@Autowired
	private RegionVoMapper regionVoMapper = null;
	
	@Override
	public List<RegionVo> getRegionInfoList() {
		// TODO Auto-generated method stub
		return regionVoMapper.getRegionInfoList();
	}

	@Override
	public void doRegionInsert(RegionVo regionVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doRegionUpdate(RegionVo regionVo) {
		// TODO Auto-generated method stub
		
	}

}
