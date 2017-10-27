package com.ztel.app.persist.mybatis.safe;

import com.ztel.app.vo.safe.HazardsInfoVo;

public interface HazardsInfoVoMapper {
    /**
     *
     * @mbggenerated 2017-10-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insert(HazardsInfoVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insertSelective(HazardsInfoVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    HazardsInfoVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKeySelective(HazardsInfoVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKey(HazardsInfoVo record);
}