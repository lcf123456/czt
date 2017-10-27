package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.RouteitemVo;

public interface RouteitemVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(RouteitemVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(RouteitemVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    RouteitemVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(RouteitemVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(RouteitemVo record);
}