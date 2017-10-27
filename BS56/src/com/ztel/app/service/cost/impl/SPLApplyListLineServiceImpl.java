package com.ztel.app.service.cost.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.cost.SPLApplyListLineVoMapper;
import com.ztel.app.service.cost.SPLApplyListLineService;
import com.ztel.app.vo.cost.SPLApplyListLineVo;

@Service
public class SPLApplyListLineServiceImpl implements SPLApplyListLineService {
	
	@Autowired
	SPLApplyListLineVoMapper sPLApplyListLineVoMapper = null;

	@Override
	public List<SPLApplyListLineVo> getApplyListLine(SPLApplyListLineVo sPLApplyListLineVo) {
		// TODO Auto-generated method stub
		return this.sPLApplyListLineVoMapper.selectApplyListLineList(sPLApplyListLineVo);
	}

}
