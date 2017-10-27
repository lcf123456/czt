package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.Sq6ScompsumVo;
import java.math.BigDecimal;

public interface Sq6ScompsumVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(Sq6ScompsumVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(Sq6ScompsumVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    Sq6ScompsumVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(Sq6ScompsumVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(Sq6ScompsumVo record);
}