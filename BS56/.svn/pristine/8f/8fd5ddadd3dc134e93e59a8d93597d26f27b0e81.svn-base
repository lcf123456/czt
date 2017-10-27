package com.ztel.app.persist.mybatis.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ztel.app.vo.account.TimebydmVo;
import com.ztel.framework.vo.Pagination;

public interface TimebydmVoMapper {
	
	/**
	 * 根据配送日期取订单日期
	 * @param record
	 * @return
	 */
	TimebydmVo selectOrderdateByDDate(TimebydmVo record);
	
	/**
	 * 根据订单日期取配送日期
	 * @param record
	 * @return
	 */
	TimebydmVo selectDeliverydateByODate(TimebydmVo record);
    /**
     *
     * @mbggenerated 2017-09-12
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-12
     */
    int insert(TimebydmVo record);

    /**
     *
     * @mbggenerated 2017-09-12
     */
    int insertSelective(TimebydmVo record);

    /**
     *
     * @mbggenerated 2017-09-12
     */
    TimebydmVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-12
     */
    int updateByPrimaryKeySelective(TimebydmVo record);

    /**
     *
     * @mbggenerated 2017-09-12
     */
    int updateByPrimaryKey(TimebydmVo record);

	List<TimebydmVo> selectTimebydmdatePageList(Pagination<?> page);
}