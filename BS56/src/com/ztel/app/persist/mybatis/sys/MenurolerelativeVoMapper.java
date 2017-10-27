package com.ztel.app.persist.mybatis.sys;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sys.MenurolerelativeVo;

public interface MenurolerelativeVoMapper {
	
	/**
	 * 检查menuid、sysroleid的记录是否已经存在，用于角色授权
	 * @param menuid
	 * @param sysroleid
	 * @return
	 */
	int checkSetting(@Param("menuid")String menuid,@Param("sysroleid")String sysroleid);
	
    /**
     *
     * @mbggenerated 2017-04-12
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insert(MenurolerelativeVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insertSelective(MenurolerelativeVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    MenurolerelativeVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKeySelective(MenurolerelativeVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKey(MenurolerelativeVo record);
}