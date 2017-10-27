package com.ztel.app.service.account.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.account.AccountTotalVoMapper;
import com.ztel.app.service.account.AccountTotalService;
import com.ztel.app.vo.account.AccountTotalVo;
import com.ztel.framework.vo.Pagination;
@Service
public class AccountTotalServiceImpl implements AccountTotalService {
	
	@Autowired
	private AccountTotalVoMapper accountTotalVoMapper = null;
	 
	//@Transactional(rollbackFor=Exception.class)

	@Override
	public List<AccountTotalVo> selectAccountTotalList(Pagination<?> page) {
		// TODO Auto-generated method stub
		return accountTotalVoMapper.selectAccountTotalList(page);
	}

	@Override
	public void doAccountTotalUpdate(AccountTotalVo accountTotalVo) {
		// TODO Auto-generated method stub
		accountTotalVoMapper.updateByPrimaryKeySelective(accountTotalVo);
	}
}
