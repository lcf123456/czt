package com.ztel.app.persist.mybatis.cost;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.cost.SPLTypeStockVo;

public interface SPLTypeStockVoMapper {
	
	/**
	 * 根据设备类型id获取该类型的库存情况 ,暂时用于设备类型管理的删除
	 * @param typeid
	 * @return
	 */
	SPLTypeStockVo selectSPLTYPESTOCKByTypeId(Integer typeid);
	
	/**
	 * 用于物资入库和退库的时候更新物资类型的数量与金额
	 * @param splTypeStockVo
	 * @return
	 */
	int updateSplTypeStockByImpOrRefund(SPLTypeStockVo splTypeStockVo);
	
	/**
	 * 根据物资类比id，更新申请数量，暂用于领料申请
	 * @param typeid
	 * @param applyqty
	 * @return
	 */
	void updateSPLTYPESTOCKByTypeId(SPLTypeStockVo splTypeStockVo);
	
	/**
	 * 取物资类型库存列表
	 * @param splTypeStockVo
	 * @return
	 */
	List<SPLTypeStockVo> getTypeStockList(SPLTypeStockVo splTypeStockVo);
	
	/**
	 * 取物资类型库存列表打印
	 * @param splTypeStockVo
	 * @return
	 */
	List<SPLTypeStockVo> getTypeStockListForPrint(SPLTypeStockVo splTypeStockVo);
	
    /**
     *
     * @mbggenerated 2017-06-23
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-06-23
     */
    int insert(SPLTypeStockVo record);

    /**
     *
     * @mbggenerated 2017-06-23
     */
    int insertSelective(SPLTypeStockVo record);

    /**
     *
     * @mbggenerated 2017-06-23
     */
    SPLTypeStockVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-06-23
     */
    int updateByPrimaryKeySelective(SPLTypeStockVo record);

    /**
     *
     * @mbggenerated 2017-06-23
     */
    int updateByPrimaryKey(SPLTypeStockVo record);
}