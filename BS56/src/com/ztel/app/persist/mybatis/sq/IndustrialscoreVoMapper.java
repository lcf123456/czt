package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.IndustrialscoreVo;

public interface IndustrialscoreVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(IndustrialscoreVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(IndustrialscoreVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    IndustrialscoreVo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(IndustrialscoreVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(IndustrialscoreVo record);
}