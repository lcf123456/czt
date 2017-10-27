package com.ztel.app.persist.mybatis.sale;

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