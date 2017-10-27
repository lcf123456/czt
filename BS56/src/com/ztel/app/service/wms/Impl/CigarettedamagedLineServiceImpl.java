package com.ztel.app.service.wms.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.CigarettedamagedLineVoMapper;
import com.ztel.app.service.wms.CigarettedamagedLineService;
import com.ztel.app.vo.wms.CigarettedamagedLineVo;

@Service
public class CigarettedamagedLineServiceImpl implements CigarettedamagedLineService {

	@Autowired 
	private CigarettedamagedLineVoMapper cigarettedamagedLineVoMapper = null;
	
	@Override
	public List<CigarettedamagedLineVo> selectListByCond(CigarettedamagedLineVo record) {
		// TODO Auto-generated method stub
		return cigarettedamagedLineVoMapper.selectListByCond(record);
	}

}
