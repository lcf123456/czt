package com.ztel.app.inspur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.inspur.mybatis.TestVoMapper;
import com.ztel.app.inspur.vo.TestVo;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestVoMapper TestVoMapper = null;

	@Override
	public List<TestVo> getList() {
		// TODO Auto-generated method stub
		return TestVoMapper.getList();
	}

}
