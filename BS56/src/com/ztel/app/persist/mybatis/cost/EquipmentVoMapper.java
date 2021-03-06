package com.ztel.app.persist.mybatis.cost;

import com.ztel.app.vo.cost.EquipmentVo;
import com.ztel.framework.vo.Pagination;

import java.math.BigDecimal;
import java.util.List;

public interface EquipmentVoMapper {
    /**
     *
     * @mbggenerated 2017-09-29
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-09-29
     */
    int insert(EquipmentVo record);

    /**
     *
     * @mbggenerated 2017-09-29
     */
    int insertSelective(EquipmentVo record);

    /**
     *
     * @mbggenerated 2017-09-29
     */
    EquipmentVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-29
     */
    int updateByPrimaryKeySelective(EquipmentVo record);

    /**
     *
     * @mbggenerated 2017-09-29
     */
    int updateByPrimaryKey(EquipmentVo record);

	List<EquipmentVo> selectEquipmentPageList(Pagination<?> page);

	List<EquipmentVo> selectListEquipment(EquipmentVo equipmentVo);
}