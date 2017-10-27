package com.ztel.app.persist.mybatis.wms;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.wms.ItemstockLineVo;

public interface ItemstockLineVoMapper {
	/**
	 * 根据父id获取列表明细
	 * @param parentid
	 * @return
	 */
	List<ItemstockLineVo> selectDetailListByParentid(BigDecimal parentid);
	
	/**
	 * 根据父id获取列表明细汇总
	 * @param parentid
	 * @return
	 */
	List<ItemstockLineVo> selectSumListByParentid(ItemstockLineVo itemstockLineVo);
	
    /**
     *
     * @mbggenerated 2017-09-25
     */
    int insert(ItemstockLineVo record);

    /**
     *
     * @mbggenerated 2017-09-25
     */
    int insertSelective(ItemstockLineVo record);
}