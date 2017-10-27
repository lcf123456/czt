package com.ztel.app.persist.mybatis.wms;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.wms.InventoryVo;
import com.ztel.framework.vo.Pagination;

public interface InventoryVoMapper {
	
	public List<InventoryVo> selectInventoryPageList(Pagination<?> page);
	
	public List<InventoryVo> selectInventoryCount(InventoryVo record);
    /**
     *
     * @mbggenerated 2017-08-01
     */
    int deleteByPrimaryKey(BigDecimal inventoryid);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insert(InventoryVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insertSelective(InventoryVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    InventoryVo selectByPrimaryKey(BigDecimal inventoryid);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKeySelective(InventoryVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKey(InventoryVo record);
}