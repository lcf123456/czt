package com.ztel.app.service.account;

import java.util.List;

import com.ztel.app.vo.account.DistributionModeVo;

public interface DistributionModeVoService {

	public List<DistributionModeVo> getDIstributionModes();
	
	public int updateAllModeDisable();
	
	public void doChangeMode(DistributionModeVo distributionModeVo);
}
