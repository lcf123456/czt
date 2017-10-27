package com.ztel.app.persist.mybatis.wms;

import com.ztel.app.vo.wms.ATSCellInfoVo;
import java.math.BigDecimal;

public interface ATSCellInfoVoMapper {
    /**
     *
     * @mbggenerated 2017-08-01
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insert(ATSCellInfoVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insertSelective(ATSCellInfoVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    ATSCellInfoVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKeySelective(ATSCellInfoVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKey(ATSCellInfoVo record);
}