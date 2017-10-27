package com.ztel.app.service.cost.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.cost.SPLTypeStockByDateVoMapper;
import com.ztel.app.service.cost.SPLTypeStockByDateVoService;
import com.ztel.app.vo.cost.SPLTypeStockByDateVo;

@Service
public class SPLTypeStockByDateVoServiceImpl implements SPLTypeStockByDateVoService{

	@Autowired
    private SPLTypeStockByDateVoMapper splTypeStockByDateVoMapper =null;

	@Override
	public List<SPLTypeStockByDateVo> getSPLTypeStockByDate(SPLTypeStockByDateVo splTypeStockByDateVo) {
		// TODO Auto-generated method stub
		return splTypeStockByDateVoMapper.getSPLTypeStockByDate(splTypeStockByDateVo);
	}

}
	
