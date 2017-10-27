package com.ztel.app.persist.mybatis.produce;

import java.util.Date;

import com.ztel.app.vo.produce.OrderVo;

public interface OrderVoMapper {
	
	//暂未启用 ，可删
	OrderVo selectOrderdate();
    /**
     *
     * @mbggenerated 2017-08-15
     */
    int deleteByPrimaryKey(String billcode);

    /**
     *
     * @mbggenerated 2017-08-15
     */
    int insert(OrderVo record);

    /**
     *
     * @mbggenerated 2017-08-15
     */
    int insertSelective(OrderVo record);

    /**
     *
     * @mbggenerated 2017-08-15
     */
    OrderVo selectByPrimaryKey(String billcode);

    /**
     *
     * @mbggenerated 2017-08-15
     */
    int updateByPrimaryKeySelective(OrderVo record);

    /**
     *
     * @mbggenerated 2017-08-15
     */
    int updateByPrimaryKey(OrderVo record);
}