package com.ztel.app.service.sys;

import java.util.List;

import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.sys.UserrolerelativeVo;

public interface UserrolerelativeService {

	/**
	 * 根据角色获取已经授权的用户列表
	 * @param roleid
	 * @return
	 */
	public List<UserVo> selectUserListByroleid(String roleid);
	
	/**
	 * 单个用户授权
	 * @param userrolerelativeVo  实体需要设置userid和roleid
	 */
	public void doSingleUserGrant(UserrolerelativeVo userrolerelativeVo);
	
	public void doDelGrant(int userid);
	
}
