package com.ztel.app.service.sys;

import java.util.List;
import java.util.Map;

import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;


/**
 * 
 * @author lcf
 *
 */
public interface UserVoService {
	/**
	 * 根据用户名密码，验证登录用户是否存在
	 * @param username
	 * @param userpsw
	 * @return
	 */
	public Map<String, Object> checkUser(String usercode,String userpsw);
	
	public List<UserVo> getUserListByDeptId(Integer deptid);
	
	/**
	 * 根据角色id查找用户
	 * @param roleid
	 * @return
	 */
	public List<UserVo> getUserListByRoleId(Integer roleid);
	
	/**
	 * 分页取用户列表
	 * @param page
	 * @return
	 */
	public List<UserVo> getUserPageList(Pagination<?> page);
	
	/**
	 * 密码重置
	 * @param ids
	 */
	public void resetPsw(List<Integer> ids);
	
	/**
	 * 新增用户
	 * @param userVo
	 */
	public void insertNewUser(UserVo userVo);
	
	/**
	 * 修改用户
	 * @param userVo
	 */
	public void updateUser(UserVo userVo);
	
	/**
	 * 角色重置，角色授权之前，把已经授权的角色id为roleid的用户重置为默认角色，以便下一步授权
	 * @param roleid
	 */
	public void resetRole(String roleid);
	
	public List<UserVo> selectUserListByroleid(String roleid);
	
	/**
	 * 获取部门id及子部门下的所有用户，此方法在绩效管理的日常工作下新增用到
	 * @param deptid
	 * @return
	 */
	public List<UserVo> getUserAndChildListByDeptId(Integer deptid);
	
}
