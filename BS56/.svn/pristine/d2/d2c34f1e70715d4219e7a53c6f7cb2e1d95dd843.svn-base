package com.ztel.app.persist.mybatis.wms;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.wms.InBoundLineVo;
import com.ztel.app.vo.wms.InBoundVo;

public interface InBoundLineVoMapper {
	/**
	 * 获取列表
	 * @param inBoundLineVo
	 * @return
	 */
    List<InBoundLineVo> selectListByCond(InBoundLineVo inBoundLineVo);
    
    /**
     * 管控中心：仓储管控：入库汇总
     * @param inBoundVo
     * @return
     */
    List<InBoundLineVo> selectInboundReportListByCond(InBoundVo inBoundVo);
    
    /**
	 * 取入库库存的账面量
	 * @param record
	 * @return
	 */
	List<InBoundLineVo> selectInboundListForStock(InBoundVo record);
	
    /**
     *
     * @mbggenerated 2017-08-01
     */
    int deleteByPrimaryKey(BigDecimal inbounddetailid);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insert(InBoundLineVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insertSelective(InBoundLineVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    InBoundLineVo selectByPrimaryKey(BigDecimal inbounddetailid);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKeySelective(InBoundLineVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKey(InBoundLineVo record);
}