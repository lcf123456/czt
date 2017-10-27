package com.ztel.app.service.sys;

import java.util.HashMap;
import java.util.List;


import com.ztel.app.vo.sys.VehicleVo;
import com.ztel.framework.vo.Pagination;


/**
 * 
 * @author SN
 *
 */
public interface VehicleVoService {
	/**
	 * 取配送车辆下拉框
	 * @return
	 */
	public List<HashMap<String, String>>getVehiclesCombobox();
	/**
	 * 数据翻页
	 * @return
	 */
	//public List<DeptVo> getDeptVoPageList(Pagination<?> page);
	
	public int delVehicleVo(List<Integer> ids);

	public int insertVehicleVo(VehicleVo vehicleVo);

	public int updateVehicleVo(VehicleVo vehicleVo);
	
	public int viewVehicleVo(VehicleVo vehicleVo);
	
	List<VehicleVo> getVehicleVoList(VehicleVo vehicleVo);
	
	List<VehicleVo> getVehicleVoPageList(Pagination<?> page);

}
