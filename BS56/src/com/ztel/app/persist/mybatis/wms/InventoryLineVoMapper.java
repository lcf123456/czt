package com.ztel.app.persist.mybatis.wms;

import com.ztel.app.vo.wms.InventoryLineVo;
import java.math.BigDecimal;
import java.util.List;

public interface InventoryLineVoMapper {
	List<InventoryLineVo> selectLastInventoryInfoByCond(InventoryLineVo inventoryLineVo);
	List<InventoryLineVo> selectInventoryTroughInfoByCond(InventoryLineVo inventoryLineVo);
	/**
	 * 查看页面
	 * @param inventoryLineVo
	 * @return
	 */
	List<InventoryLineVo> selectInventoryInfoByCond(InventoryLineVo inventoryLineVo);
    /**
     *
     * @mbggenerated 2017-08-01
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insert(InventoryLineVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insertSelective(InventoryLineVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    InventoryLineVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKeySelective(InventoryLineVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKey(InventoryLineVo record);
}