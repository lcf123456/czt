package com.ztel.app.persist.mybatis.safe;

import com.ztel.app.vo.safe.FireSystemnoteVo;
import java.math.BigDecimal;

public interface FireSystemnoteVoMapper {
    /**
     *
     * @mbggenerated 2017-10-18
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insert(FireSystemnoteVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insertSelective(FireSystemnoteVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    FireSystemnoteVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKeySelective(FireSystemnoteVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKey(FireSystemnoteVo record);
}