package com.ztel.app.service.safe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ztel.app.vo.safe.VehicleAccidentVo;
import com.ztel.framework.vo.Pagination;

public interface VehicleAccidentService {

	public void viewAccident(VehicleAccidentVo vehicleaccidentVo);
	
	public List<VehicleAccidentVo> getAccidentPageList(Pagination<?> page);

}
