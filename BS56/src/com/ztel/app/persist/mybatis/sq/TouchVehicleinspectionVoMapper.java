package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.TouchVehicleinspectionVo;
import java.math.BigDecimal;

public interface TouchVehicleinspectionVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(TouchVehicleinspectionVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(TouchVehicleinspectionVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    TouchVehicleinspectionVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(TouchVehicleinspectionVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(TouchVehicleinspectionVo record);
}