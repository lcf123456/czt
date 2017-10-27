package com.ztel.app.persist.mybatis.sale;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sale.SaleorderlineVo;

public interface SaleorderlineVoMapper {
	
	/**
	 * 接收到的营销数据插入之前先全部删除
	 */
	void deletesaleorderline(@Param("sqlstr")String sqlstr);
	/**
	 * 从营销接口取零售户数据插入到本地
	 * @param sqlstr
	 */
	void insertsaleorderline(@Param("sqlstr")String sqlstr);
	
    /**
     *
     * @mbggenerated 2017-10-27
     */
    int insert(SaleorderlineVo record);

    /**
     *
     * @mbggenerated 2017-10-27
     */
    int insertSelective(SaleorderlineVo record);
}