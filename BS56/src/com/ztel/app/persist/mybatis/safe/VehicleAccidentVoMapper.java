package com.ztel.app.persist.mybatis.safe;

import java.util.List;

import com.ztel.app.vo.safe.VehicleAccidentVo;
import com.ztel.framework.vo.Pagination;

public interface VehicleAccidentVoMapper {
    /**
     *
     * @mbggenerated 2017-10-19
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-10-19
     */
    int insert(VehicleAccidentVo record);

    /**
     *
     * @mbggenerated 2017-10-19
     */
    int insertSelective(VehicleAccidentVo record);

    /**
     *
     * @mbggenerated 2017-10-19
     */
    VehicleAccidentVo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-10-19
     */
    int updateByPrimaryKeySelective(VehicleAccidentVo record);

    /**
     *
     * @mbggenerated 2017-10-19
     */
    int updateByPrimaryKey(VehicleAccidentVo record);

	List<VehicleAccidentVo> getAccidentPageList(Pagination<?> page);
}