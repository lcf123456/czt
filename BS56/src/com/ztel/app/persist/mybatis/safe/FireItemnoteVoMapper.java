package com.ztel.app.persist.mybatis.safe;

import com.ztel.app.vo.safe.FireItemnoteVo;
import java.math.BigDecimal;

public interface FireItemnoteVoMapper {
    /**
     *
     * @mbggenerated 2017-10-18
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insert(FireItemnoteVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insertSelective(FireItemnoteVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    FireItemnoteVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKeySelective(FireItemnoteVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKey(FireItemnoteVo record);
}