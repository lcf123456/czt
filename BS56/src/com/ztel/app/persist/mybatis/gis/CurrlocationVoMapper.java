package com.ztel.app.persist.mybatis.gis;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.gis.CurrlocationVo;
import com.ztel.framework.vo.Pagination;

public interface CurrlocationVoMapper {
	
	List<CurrlocationVo> getCurrlocationPageList(Pagination<?> page);
    /**
     *
     * @mbggenerated 2017-08-31
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-08-31
     */
    int insert(CurrlocationVo record);

    /**
     *
     * @mbggenerated 2017-08-31
     */
    int insertSelective(CurrlocationVo record);

    /**
     *
     * @mbggenerated 2017-08-31
     */
    CurrlocationVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-08-31
     */
    int updateByPrimaryKeySelective(CurrlocationVo record);

    /**
     *
     * @mbggenerated 2017-08-31
     */
    int updateByPrimaryKey(CurrlocationVo record);
}