package com.ztel.app.service.wms.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.MantissaVoMapper;
import com.ztel.app.service.wms.MantissaService;
import com.ztel.app.vo.wms.MantissaVo;
@Service
public class MantissaServiceImpl implements MantissaService{
	@Autowired
	private MantissaVoMapper mantissaVoMapper = null;

	@Override
	public void insertRecord(MantissaVo vo) {
		// TODO Auto-generated method stub
		List<MantissaVo> list=mantissaVoMapper.getMantissa(vo);
		if(list!=null && list.size()>0)
		{
			MantissaVo temp=mantissaVoMapper.getMantissa(vo).get(0);
			temp.setQty(vo.getQty());
			mantissaVoMapper.updateByPrimaryKeySelective(temp);
		}
		else
		{
			mantissaVoMapper.insertSelective(vo);
		}
		
	}

	@Override
	public MantissaVo getMantissa(MantissaVo vo) {
		// TODO Auto-generated method stub
		List<MantissaVo> list= mantissaVoMapper.getMantissa(vo);
		if(list!=null && list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}
	
}
