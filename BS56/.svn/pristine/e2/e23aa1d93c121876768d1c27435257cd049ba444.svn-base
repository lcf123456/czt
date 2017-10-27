package com.ztel.app.persist.mybatis.gis;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.gis.TruckseqVo;
import com.ztel.framework.vo.Pagination;

public interface TruckseqVoMapper {
	
	/**
	 * 来烟排队：入园、入单、排队、出园
	 * getTruckseqEfficiencyPageList
	 * @param page
	 * @return
	 */
	List<TruckseqVo> getTruckseqPageList(Pagination<?> page);
	
	/**
	 * 今日装卸
	 * @param page
	 * @return
	 */
	List<TruckseqVo> getTruckseqTodayPageList(Pagination<?> page);
	
	/**
	 * 装卸效率
	 * @param page
	 * @return
	 */
	List<TruckseqVo> getTruckseqEfficiencyPageList(Pagination<?> page);
	
	/**
	 * 组装卸效率
	 * @param truckseqVo
	 * @return
	 */
	List<TruckseqVo>  getGroupEfficiencyList(TruckseqVo truckseqVo);
	
	BigDecimal getnextSeq();
	
	
    /**
     *
     * @mbggenerated 2017-09-04
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-04
     */
    int insert(TruckseqVo record);

    /**
     *
     * @mbggenerated 2017-09-04
     */
    int insertSelective(TruckseqVo record);

    /**
     *
     * @mbggenerated 2017-09-04
     */
    TruckseqVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-04
     */
    int updateByPrimaryKeySelective(TruckseqVo record);

    /**
     *
     * @mbggenerated 2017-09-04
     */
    int updateByPrimaryKey(TruckseqVo record);
    
}