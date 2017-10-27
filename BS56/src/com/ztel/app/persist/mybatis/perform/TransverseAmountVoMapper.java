package com.ztel.app.persist.mybatis.perform;

import com.ztel.app.vo.perform.TransverseAmountVo;
import java.math.BigDecimal;

public interface TransverseAmountVoMapper {
    /**
     *
     * @mbggenerated 2017-09-28
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-28
     */
    int insert(TransverseAmountVo record);

    /**
     *
     * @mbggenerated 2017-09-28
     */
    int insertSelective(TransverseAmountVo record);

    /**
     *
     * @mbggenerated 2017-09-28
     */
    TransverseAmountVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-28
     */
    int updateByPrimaryKeySelective(TransverseAmountVo record);

    /**
     *
     * @mbggenerated 2017-09-28
     */
    int updateByPrimaryKey(TransverseAmountVo record);
}