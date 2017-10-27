package com.ztel.app.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sys.BusinessRoleRelativeVoMapper;
import com.ztel.app.service.sys.BusinessRoleRelativeService;
import com.ztel.app.vo.sys.BusinessRoleRelativeVo;

@Service
public class BusinessRoleRelativeServiceImpl implements BusinessRoleRelativeService {

	@Autowired
	private BusinessRoleRelativeVoMapper businessRoleRelativeVoMapper = null;
	@Override
	public List<BusinessRoleRelativeVo> getBusinessRoleRelativeGrantList(String roleid) {
		// TODO Auto-generated method stub
		return this.businessRoleRelativeVoMapper.getBusinessRoleRelativeGrantList(roleid);
	}

}
