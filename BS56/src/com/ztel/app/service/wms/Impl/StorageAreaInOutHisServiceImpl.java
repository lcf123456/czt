package com.ztel.app.service.wms.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.StorageAreaInOutHisVoMapper;
import com.ztel.app.service.wms.StorageAreaInOutHisService;
@Service
public class StorageAreaInOutHisServiceImpl implements StorageAreaInOutHisService {

	@Autowired
	private StorageAreaInOutHisVoMapper areaInOutHisVoMapper = null;

	@Override
	public int insertToInOutHis(String searchtime) {
		// TODO Auto-generated method stub
		return areaInOutHisVoMapper.insertToInOutHis(searchtime);
	}

}
