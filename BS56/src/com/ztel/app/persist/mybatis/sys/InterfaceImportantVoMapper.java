package com.ztel.app.persist.mybatis.sys;

import com.ztel.app.vo.sys.InterfaceImportantVo;

public interface InterfaceImportantVoMapper {
    /**
     *
     * @mbggenerated 2017-04-12
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insert(InterfaceImportantVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insertSelective(InterfaceImportantVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    InterfaceImportantVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKeySelective(InterfaceImportantVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKey(InterfaceImportantVo record);
}