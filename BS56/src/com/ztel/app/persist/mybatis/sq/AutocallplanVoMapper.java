package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.AutocallplanVo;
import java.math.BigDecimal;

public interface AutocallplanVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(AutocallplanVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(AutocallplanVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    AutocallplanVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(AutocallplanVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(AutocallplanVo record);
}