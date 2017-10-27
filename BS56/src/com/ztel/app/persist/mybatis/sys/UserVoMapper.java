package com.ztel.app.persist.mybatis.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;

public interface UserVoMapper {
	
	int selectByCode(@Param("usercode")String username);
	
	UserVo selectByCodeAndPwd(@Param("usercode")String username,@Param("userpsw")String userpsw);
	
	public List<UserVo> getUserListByDeptId(@Param("deptid")Integer deptid);//根据部门id获取用户信息
	
	public List<UserVo> getUserListByRoleId(@Param("roleid")Integer roleid);//根据角色id获取用户信息
	
	public void resetRole(@Param("roleid")String roleid);//重置角色为roleid的用户，将角色设置为默认角色
	int checkGrant(@Param("userid")String userid,@Param("roleid")String roleid);//检查userid用户是否已经授权为roleid
	List<UserVo> selectUserListByroleid(@Param("roleid")String roleid);//获取授权为roleid的用户列表
	/**
	 * 取人员列表
	 * @param page
	 * @return
	 */
	List<UserVo> getUserPageList(Pagination<?> page);
	
	/**
	 * 密码重置
	 * @param page
	 * @return
	 */
	int updateUserPsw(Integer id);
	
    /**
     *
     * @mbggenerated 2017-04-12
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insert(UserVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insertSelective(UserVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    UserVo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKeySelective(UserVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKey(UserVo record);
}