package com.ztel.app.persist.mybatis.account;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.account.OperateVo;
import com.ztel.framework.vo.Pagination;

public interface OperateVoMapper {
	
	List<OperateVo> getOperatePageList(Pagination<?> page);
	
	/**
	 * 预付款退货
	 * @param vo
	 * @return
	 */
	List<OperateVo> selectPrepayreturn(OperateVo vo);
    /**
     *
     * @mbggenerated 2017-08-02
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-08-02
     */
    int insert(OperateVo record);

    /**
     *
     * @mbggenerated 2017-08-02
     */
    int insertSelective(OperateVo record);

    /**
     *
     * @mbggenerated 2017-08-02
     */
    OperateVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-08-02
     */
    int updateByPrimaryKeySelective(OperateVo record);

    /**
     *
     * @mbggenerated 2017-08-02
     */
    int updateByPrimaryKey(OperateVo record);
}