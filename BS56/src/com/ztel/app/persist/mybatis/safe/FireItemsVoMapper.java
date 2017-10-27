package com.ztel.app.persist.mybatis.safe;

import com.ztel.app.vo.safe.FireItemsVo;

public interface FireItemsVoMapper {
    /**
     *
     * @mbggenerated 2017-10-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insert(FireItemsVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insertSelective(FireItemsVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    FireItemsVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKeySelective(FireItemsVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKey(FireItemsVo record);
}