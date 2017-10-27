package com.ztel.app.persist.mybatis.gis;

import com.ztel.app.vo.gis.OrderlineinfoVo;
import com.ztel.framework.vo.Pagination;

import java.math.BigDecimal;
import java.util.List;

public interface OrderlineinfoVoMapper {
	
	List<OrderlineinfoVo> getOrderlineinfoPageList(Pagination<?> page);
	
    /**
     *
     * @mbggenerated 2017-08-31
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-08-31
     */
    int insert(OrderlineinfoVo record);

    /**
     *
     * @mbggenerated 2017-08-31
     */
    int insertSelective(OrderlineinfoVo record);

    /**
     *
     * @mbggenerated 2017-08-31
     */
    OrderlineinfoVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-08-31
     */
    int updateByPrimaryKeySelective(OrderlineinfoVo record);

    /**
     *
     * @mbggenerated 2017-08-31
     */
    int updateByPrimaryKey(OrderlineinfoVo record);
}