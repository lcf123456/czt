package com.ztel.app.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sys.UserrolerelativeVoMapper;
import com.ztel.app.service.sys.UserrolerelativeService;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.sys.UserrolerelativeVo;
@Service
public class UserrolerelativeServiceImpl implements UserrolerelativeService {

	@Autowired
	private UserrolerelativeVoMapper userrolerelativeVoMapper = null;
	
	/**
	 * 根据角色获取已经授权的用户列表
	 */
	public List<UserVo> selectUserListByroleid(String roleid){
		List<UserVo> userVo = userrolerelativeVoMapper.selectUserListByroleid(roleid);
		return userVo;
	}

	/**
	 * 单个用户授权
	 */
	@Override
	public void doSingleUserGrant(UserrolerelativeVo userrolerelativeVo) {
		// TODO Auto-generated method stub
		doDelGrant(userrolerelativeVo.getUserid());
		userrolerelativeVoMapper.insertSelective(userrolerelativeVo);
	}

	@Override
	public void doDelGrant(int userid) {
		// TODO Auto-generated method stub
		userrolerelativeVoMapper.deleteByUserid(userid);
	}
}
