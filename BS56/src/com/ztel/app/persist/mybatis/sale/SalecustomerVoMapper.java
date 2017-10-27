package com.ztel.app.persist.mybatis.sale;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sale.SalecustomerVo;

public interface SalecustomerVoMapper {
	
	/**
	 * 接收到的营销数据插入之前先全部删除
	 */
	void deletecustomer();
	/**
	 * 从营销接口取零售户数据插入到本地
	 * @param sqlstr
	 */
	void insertcustomertoora(@Param("sqlstr")String sqlstr);
    /**
     *
     * @mbggenerated 2017-10-25
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2017-10-25
     */
    int insert(SalecustomerVo record);

    /**
     *
     * @mbggenerated 2017-10-25
     */
    int insertSelective(SalecustomerVo record);

    /**
     *
     * @mbggenerated 2017-10-25
     */
    SalecustomerVo selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2017-10-25
     */
    int updateByPrimaryKeySelective(SalecustomerVo record);

    /**
     *
     * @mbggenerated 2017-10-25
     */
    int updateByPrimaryKey(SalecustomerVo record);
}