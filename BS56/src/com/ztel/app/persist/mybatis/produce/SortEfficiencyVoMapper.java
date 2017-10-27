package com.ztel.app.persist.mybatis.produce;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.produce.SortEfficiencyVo;
import com.ztel.framework.vo.Pagination;

public interface SortEfficiencyVoMapper {
	
	public List<SortEfficiencyVo> getSortEfficiencyPageList(Pagination<?> page);
	
    /**
     *
     * @mbggenerated 2017-10-19
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-19
     */
    int insert(SortEfficiencyVo record);

    /**
     *
     * @mbggenerated 2017-10-19
     */
    int insertSelective(SortEfficiencyVo record);

    /**
     *
     * @mbggenerated 2017-10-19
     */
    SortEfficiencyVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-19
     */
    int updateByPrimaryKeySelective(SortEfficiencyVo record);

    /**
     *
     * @mbggenerated 2017-10-19
     */
    int updateByPrimaryKey(SortEfficiencyVo record);
}