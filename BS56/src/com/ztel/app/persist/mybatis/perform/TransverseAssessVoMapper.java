package com.ztel.app.persist.mybatis.perform;

import com.ztel.app.vo.perform.TransverseAssessVo;
import com.ztel.app.vo.wms.CigarettedamagedVo;
import com.ztel.framework.vo.Pagination;

import java.math.BigDecimal;
import java.util.List;

public interface TransverseAssessVoMapper {
	
	List<TransverseAssessVo> selectTransverseAssessPageList(Pagination<?> page);
	
	List<TransverseAssessVo> selectTransverseAssessList(TransverseAssessVo transverseAssessVo);
    /**
     *
     * @mbggenerated 2017-09-28
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-28
     */
    int insert(TransverseAssessVo record);

    /**
     *
     * @mbggenerated 2017-09-28
     */
    int insertSelective(TransverseAssessVo record);

    /**
     *
     * @mbggenerated 2017-09-28
     */
    TransverseAssessVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-28
     */
    int updateByPrimaryKeySelective(TransverseAssessVo record);

    /**
     *
     * @mbggenerated 2017-09-28
     */
    int updateByPrimaryKey(TransverseAssessVo record);
}