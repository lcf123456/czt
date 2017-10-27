package com.ztel.app.persist.mybatis.safe;

import com.ztel.app.vo.safe.FireSystemVo;

public interface FireSystemVoMapper {
    /**
     *
     * @mbggenerated 2017-10-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insert(FireSystemVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insertSelective(FireSystemVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    FireSystemVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKeySelective(FireSystemVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKey(FireSystemVo record);
}