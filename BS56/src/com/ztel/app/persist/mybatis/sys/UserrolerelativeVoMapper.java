package com.ztel.app.persist.mybatis.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.sys.UserrolerelativeVo;

public interface UserrolerelativeVoMapper {
	
	/**
	 * 检查用户和角色是否已经授权
	 * @param userid
	 * @param roleid
	 * @return
	 */
	int checkGrant(@Param("userid")String userid,@Param("roleid")String roleid);
	
	List<UserVo> selectUserListByroleid(@Param("roleid")String roleid);
	
	/**
	 * 角色授权时，先删除该角色下授权的用户,用于角色管理下的授权
	 * @param roleid
	 * @return
	 */
	int deleteByRoleid(@Param("roleid")String roleid);
	
    /**
     *
     * @mbggenerated 2017-04-12
     */
    int deleteByUserid(Integer userid);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insert(UserrolerelativeVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insertSelective(UserrolerelativeVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    UserrolerelativeVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKeySelective(UserrolerelativeVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKey(UserrolerelativeVo record);
}