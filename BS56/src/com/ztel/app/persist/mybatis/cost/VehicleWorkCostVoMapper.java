package com.ztel.app.persist.mybatis.cost;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.cost.VehicleWorkCostVo;

public interface VehicleWorkCostVoMapper {
	
	List<VehicleWorkCostVo> getVehicleWorkCostListByApplyId(@Param("fid")BigDecimal fid);
    /**
     *
     * @mbggenerated 2017-10-23
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-23
     */
    int insert(VehicleWorkCostVo record);

    /**
     *
     * @mbggenerated 2017-10-23
     */
    int insertSelective(VehicleWorkCostVo record);

    /**
     *
     * @mbggenerated 2017-10-23
     */
    VehicleWorkCostVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-23
     */
    int updateByPrimaryKeySelective(VehicleWorkCostVo record);

    /**
     *
     * @mbggenerated 2017-10-23
     */
    int updateByPrimaryKey(VehicleWorkCostVo record);
}