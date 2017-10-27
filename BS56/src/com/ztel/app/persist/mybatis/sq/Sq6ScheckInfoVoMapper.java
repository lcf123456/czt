package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.Sq6ScheckInfoVo;

public interface Sq6ScheckInfoVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(Sq6ScheckInfoVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(Sq6ScheckInfoVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    Sq6ScheckInfoVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(Sq6ScheckInfoVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(Sq6ScheckInfoVo record);
}