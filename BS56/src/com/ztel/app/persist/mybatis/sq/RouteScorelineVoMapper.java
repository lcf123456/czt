package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.RouteScorelineVo;

public interface RouteScorelineVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(RouteScorelineVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(RouteScorelineVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    RouteScorelineVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(RouteScorelineVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(RouteScorelineVo record);
}