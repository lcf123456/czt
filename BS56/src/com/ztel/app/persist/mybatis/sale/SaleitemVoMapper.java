package com.ztel.app.persist.mybatis.sale;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sale.SaleitemVo;

public interface SaleitemVoMapper {
	
	/**
	 * 取所有商品数量，在同步到本地的页面需要统计商品数量
	 * @return
	 */
	int selectAllItemCount();
	
	/**
	 * 营销接口商品信息同步之前先全部删除
	 * @param sqlstr
	 */
	void deleteitemAll(@Param("sqlstr")String sqlstr);
	/**
	 * 营销接口商品信息同步之前先全部置为删除状态
	 */
	void updateitemAllrowstatus();
	
    /**
     *
     * @mbggenerated 2017-10-29
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2017-10-29
     */
    int insert(SaleitemVo record);

    /**
     *
     * @mbggenerated 2017-10-29
     */
    int insertSelective(SaleitemVo record);

    /**
     *
     * @mbggenerated 2017-10-29
     */
    SaleitemVo selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2017-10-29
     */
    int updateByPrimaryKeySelective(SaleitemVo record);

    /**
     *
     * @mbggenerated 2017-10-29
     */
    int updateByPrimaryKey(SaleitemVo record);
}