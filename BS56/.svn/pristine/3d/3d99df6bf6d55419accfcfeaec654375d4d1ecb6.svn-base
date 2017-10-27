package com.ztel.app.persist.mybatis.sys;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.sys.VehicleVo;
import com.ztel.framework.vo.Pagination;

public interface VehicleVoMapper {
    /**
     *
     * @mbggenerated 2017-04-12
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insert(VehicleVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insertSelective(VehicleVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    VehicleVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKeySelective(VehicleVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKey(VehicleVo record);

	List<VehicleVo> getVehicleVoList(VehicleVo vehicleVo);
	
	List<VehicleVo> getVehicleVoPageList(Pagination<?> page);
	
	List<HashMap<String, VehicleVo>> getVehicleCombobox(String keywd, VehicleVo vo);


}