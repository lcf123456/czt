package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.ItemtypeVo;

public interface ItemtypeVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(ItemtypeVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(ItemtypeVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    ItemtypeVo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(ItemtypeVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(ItemtypeVo record);
}