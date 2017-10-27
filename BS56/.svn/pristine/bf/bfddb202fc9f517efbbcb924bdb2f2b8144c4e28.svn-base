package com.ztel.app.service.wms.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.MachinedamagedLineVoMapper;
import com.ztel.app.service.wms.MachinedamagedLineService;
import com.ztel.app.vo.wms.MachinedamagedLineVo;

@Service
public class MachinedamagedLineServiceImpl implements MachinedamagedLineService {
	@Autowired
	private MachinedamagedLineVoMapper machinedamagedLineVoMapper = null;

	@Override
	public List<MachinedamagedLineVo> selectListByCond(MachinedamagedLineVo machinedamagedLineVo) {
		// TODO Auto-generated method stub
		return machinedamagedLineVoMapper.selectListByCond(machinedamagedLineVo);
	}

}
