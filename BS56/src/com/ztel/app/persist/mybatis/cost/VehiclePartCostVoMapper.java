package com.ztel.app.persist.mybatis.cost;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.cost.VehiclePartCostVo;

public interface VehiclePartCostVoMapper {
	
	List<VehiclePartCostVo> getVehiclePartCostListByApplyId(@Param("did")BigDecimal did);
    /**
     *
     * @mbggenerated 2017-10-23
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-23
     */
    int insert(VehiclePartCostVo record);

    /**
     *
     * @mbggenerated 2017-10-23
     */
    int insertSelective(VehiclePartCostVo record);

    /**
     *
     * @mbggenerated 2017-10-23
     */
    VehiclePartCostVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-23
     */
    int updateByPrimaryKeySelective(VehiclePartCostVo record);

    /**
     *
     * @mbggenerated 2017-10-23
     */
    int updateByPrimaryKey(VehiclePartCostVo record);
}