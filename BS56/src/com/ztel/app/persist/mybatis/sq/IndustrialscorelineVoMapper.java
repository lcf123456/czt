package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.IndustrialscorelineVo;

public interface IndustrialscorelineVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(IndustrialscorelineVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(IndustrialscorelineVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    IndustrialscorelineVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(IndustrialscorelineVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(IndustrialscorelineVo record);
}