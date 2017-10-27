package com.ztel.app.persist.mybatis.wms;

import com.ztel.app.vo.wms.ItemstockVo;

public interface ItemstockVoMapper {
	
	/**
	 * 取最近次的盘点库存记录
	 * @return
	 */
	ItemstockVo selectItemstockVoLast();
    /**
     *
     * @mbggenerated 2017-09-25
     */
    int insert(ItemstockVo record);

    /**
     *
     * @mbggenerated 2017-09-25
     */
    int insertSelective(ItemstockVo record);
}