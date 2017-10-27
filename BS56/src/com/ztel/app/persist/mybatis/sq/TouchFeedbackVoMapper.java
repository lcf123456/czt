package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.TouchFeedbackVo;
import java.math.BigDecimal;

public interface TouchFeedbackVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(TouchFeedbackVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(TouchFeedbackVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    TouchFeedbackVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(TouchFeedbackVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(TouchFeedbackVo record);
}