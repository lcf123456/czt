package com.ztel.app.persist.mybatis.safe;

import com.ztel.app.vo.safe.FireChecktypeVo;

public interface FireChecktypeVoMapper {
    /**
     *
     * @mbggenerated 2017-10-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insert(FireChecktypeVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insertSelective(FireChecktypeVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    FireChecktypeVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKeySelective(FireChecktypeVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKey(FireChecktypeVo record);
}