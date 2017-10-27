package com.ztel.app.persist.mybatis.sys;

import com.ztel.app.vo.sys.InterfaceGenmanagerVo;

public interface InterfaceGenmanagerVoMapper {
    /**
     *
     * @mbggenerated 2017-04-12
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insert(InterfaceGenmanagerVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insertSelective(InterfaceGenmanagerVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    InterfaceGenmanagerVo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKeySelective(InterfaceGenmanagerVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKey(InterfaceGenmanagerVo record);
}