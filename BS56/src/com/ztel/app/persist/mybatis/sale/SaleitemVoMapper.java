package com.ztel.app.persist.mybatis.sale;

import com.ztel.app.vo.sale.SaleitemVo;
import java.math.BigDecimal;

public interface SaleitemVoMapper {
    /**
     *
     * @mbggenerated 2017-10-27
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-27
     */
    int insert(SaleitemVo record);

    /**
     *
     * @mbggenerated 2017-10-27
     */
    int insertSelective(SaleitemVo record);

    /**
     *
     * @mbggenerated 2017-10-27
     */
    SaleitemVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-27
     */
    int updateByPrimaryKeySelective(SaleitemVo record);

    /**
     *
     * @mbggenerated 2017-10-27
     */
    int updateByPrimaryKey(SaleitemVo record);
}