package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.MoveareastockLineVoMapper;
import com.ztel.app.service.wms.MoveareastockLineService;
import com.ztel.app.vo.wms.MoveareastockLineVo;

@Service
public class MoveareastockLineServiceImpl implements MoveareastockLineService {

	@Autowired
	MoveareastockLineVoMapper  MoveareastockLineVoMapper = null;
	
	@Override
	public List<MoveareastockLineVo> getMoveareastockLineListByFid(BigDecimal parentid) {
		// TODO Auto-generated method stub
		return MoveareastockLineVoMapper.selectByParentid(parentid);
	}

}
