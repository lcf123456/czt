package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.OutBoundLineVoMapper;
import com.ztel.app.service.wms.OutBoundLineService;
import com.ztel.app.vo.wms.OutBoundLineVo;

@Service
public class OutBoundLineServiceImpl implements OutBoundLineService {

	@Autowired
	private OutBoundLineVoMapper outBoundLineVoMapper = null;
	
	@Override
	public List<OutBoundLineVo> selectByOutboundid(BigDecimal outboundid) {
		// TODO Auto-generated method stub
		return outBoundLineVoMapper.selectByOutboundid(outboundid);
	}

}
