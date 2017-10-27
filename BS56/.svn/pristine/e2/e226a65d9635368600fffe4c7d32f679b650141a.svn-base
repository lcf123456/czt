package com.ztel.app.persist.mybatis.wms;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.wms.OutBoundLineVo;
import com.ztel.app.vo.wms.OutBoundVo;

public interface OutBoundLineVoMapper {
	
	/**
	 * 根据车组编码routecode和订单日期orderdate 获取订单明细汇总，汇总根据品牌进行
	 * @param outBoundVo
	 * @return
	 */
	List<OutBoundLineVo> selectOrderLineList(OutBoundVo outBoundVo);
	
	List<OutBoundLineVo> selectByOutboundid(@Param("outboundid") BigDecimal outboundid);
	
	/**
	 * 取出库库存的账面量
	 * @param record
	 * @return
	 */
	List<OutBoundLineVo> selectOutboundListForStock(OutBoundVo outBoundVo);
	
    /**
     *
     * @mbggenerated 2017-08-01
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * 根据outboundid删除
     * @param id
     * @return
     */
    int deleteByOutboundid(BigDecimal id);
    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insert(OutBoundLineVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insertSelective(OutBoundLineVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    OutBoundLineVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKeySelective(OutBoundLineVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKey(OutBoundLineVo record);
}