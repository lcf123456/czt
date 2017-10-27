package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.PhonetypenumberVo;

public interface PhonetypenumberVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(PhonetypenumberVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(PhonetypenumberVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    PhonetypenumberVo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(PhonetypenumberVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(PhonetypenumberVo record);
}