package com.ztel.app.persist.mybatis.sys;

import com.ztel.app.vo.sys.InterfaceUserrelativeVo;

public interface InterfaceUserrelativeVoMapper {
    /**
     *
     * @mbggenerated 2017-04-12
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insert(InterfaceUserrelativeVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insertSelective(InterfaceUserrelativeVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    InterfaceUserrelativeVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKeySelective(InterfaceUserrelativeVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKey(InterfaceUserrelativeVo record);
}