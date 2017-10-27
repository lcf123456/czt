package com.ztel.app.service.account.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.account.DistributionModeVoMapper;
import com.ztel.app.service.account.DistributionModeVoService;
import com.ztel.app.vo.account.DistributionModeVo;
@Service
public class DistributionModeVoServiceImpl implements DistributionModeVoService {
	
	@Autowired
	private DistributionModeVoMapper distributionModeVoMapper = null;

	@Override
	public List<DistributionModeVo> getDIstributionModes() {
		// TODO Auto-generated method stub
		return distributionModeVoMapper.getDIstributionModes();
	}

	@Override
	public int updateAllModeDisable() {
		// TODO Auto-generated method stub
		return distributionModeVoMapper.updateAllModeDisable();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doChangeMode(DistributionModeVo distributionModeVo) {
		// TODO Auto-generated method stub
		updateAllModeDisable();
		distributionModeVoMapper.updateByPrimaryKeySelective(distributionModeVo);
	}
	 
}
