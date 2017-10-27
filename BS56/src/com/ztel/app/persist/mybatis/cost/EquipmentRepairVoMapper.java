package com.ztel.app.persist.mybatis.cost;

import com.ztel.app.vo.cost.EquipmentRepairVo;
import com.ztel.app.vo.cost.EquipmentVo;
import com.ztel.framework.vo.Pagination;

import java.math.BigDecimal;
import java.util.List;

public interface EquipmentRepairVoMapper {
    /**
     *
     * @mbggenerated 2017-09-29
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-09-29
     */
    int insert(EquipmentRepairVo record);

    /**
     *
     * @mbggenerated 2017-09-29
     */
    int insertSelective(EquipmentRepairVo record);

    /**
     *
     * @mbggenerated 2017-09-29
     */
    EquipmentRepairVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-29
     */
    int updateByPrimaryKeySelective(EquipmentRepairVo record);

    /**
     *
     * @mbggenerated 2017-09-29
     */
    int updateByPrimaryKey(EquipmentRepairVo record);

	List<EquipmentRepairVo> selectEquipmentRepairPageList(Pagination<?> page);

	List<EquipmentRepairVo> getEquipmentRepairListByEquipId(Integer equipid);

	List<EquipmentVo> getEquipmentrepairList();


	

}