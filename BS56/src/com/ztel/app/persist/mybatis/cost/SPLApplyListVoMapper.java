package com.ztel.app.persist.mybatis.cost;

import com.ztel.app.vo.cost.SPLApplyListVo;
import com.ztel.framework.vo.Pagination;

import java.math.BigDecimal;
import java.util.List;

public interface SPLApplyListVoMapper {
	
	List<SPLApplyListVo> selectApplyListPageList(Pagination<?> page);
    /**
     *
     * @mbggenerated 2017-06-23
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-06-23
     */
    int insert(SPLApplyListVo record);

    /**
     *
     * @mbggenerated 2017-06-23
     */
    int insertSelective(SPLApplyListVo record);

    /**
     *
     * @mbggenerated 2017-06-23
     */
    SPLApplyListVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-06-23
     */
    int updateByPrimaryKeySelective(SPLApplyListVo record);

    /**
     *
     * @mbggenerated 2017-06-23
     */
    int updateByPrimaryKey(SPLApplyListVo record);
}