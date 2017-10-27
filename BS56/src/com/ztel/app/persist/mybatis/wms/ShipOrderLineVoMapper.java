package com.ztel.app.persist.mybatis.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.wms.ShipOrderLineVo;

public interface ShipOrderLineVoMapper {
	
	List<ShipOrderLineVo> getShipOrderLineByOrderNo(String orderNo);
	
	/**
	 * 工商协同：品牌汇总
	 * @param orderdatestr
	 * @return
	 */
	List<ShipOrderLineVo> getShiporderBrandReport(@Param("orderdate")String orderdate);
    /**
     *
     * @mbggenerated 2017-08-04
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2017-08-04
     */
    int insert(ShipOrderLineVo record);

    /**
     *
     * @mbggenerated 2017-08-04
     */
    int insertSelective(ShipOrderLineVo record);

    /**
     *
     * @mbggenerated 2017-08-04
     */
    ShipOrderLineVo selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2017-08-04
     */
    int updateByPrimaryKeySelective(ShipOrderLineVo record);

    /**
     *
     * @mbggenerated 2017-08-04
     */
    int updateByPrimaryKey(ShipOrderLineVo record);
}