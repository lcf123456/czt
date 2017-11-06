package com.ztel.app.persist.mybatis.sale;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sale.SaleorderheadVo;

public interface SaleorderheadVoMapper {
	
	
	
	/**
	 * 接收到的营销数据插入之前先全部删除
	 */
	void deletesaleorderhead(@Param("sqlstr")String sqlstr);
	/**
	 * 从营销接口取零售户数据插入到本地
	 * @param sqlstr
	 */
	void insertsaleorderhead(@Param("sqlstr")String sqlstr);
	
	/**
	 * 取所有订单信息，在同步到本地的页面需要统计订单数量等信息 ,此处id用作户数
	 * @param orderdate
	 * @return
	 */
	SaleorderheadVo selectAllOrderheadVo(@Param("orderdate")String orderdate);
	
	/**
	 * 取所有订单明细数量，在同步到本地的页面需要统计订单明细数量 
	 * @param orderdate
	 * @return
	 */
	int selectAllOrderlineCount(@Param("orderdate")String orderdate);
	
    /**
     *
     * @mbggenerated 2017-10-27
     */
    int deleteByPrimaryKey(Object orderno);

    /**
     *
     * @mbggenerated 2017-10-27
     */
    int insert(SaleorderheadVo record);

    /**
     *
     * @mbggenerated 2017-10-27
     */
    int insertSelective(SaleorderheadVo record);

    /**
     *
     * @mbggenerated 2017-10-27
     */
    SaleorderheadVo selectByPrimaryKey(Object orderno);

    /**
     *
     * @mbggenerated 2017-10-27
     */
    int updateByPrimaryKeySelective(SaleorderheadVo record);

    /**
     *
     * @mbggenerated 2017-10-27
     */
    int updateByPrimaryKey(SaleorderheadVo record);
}