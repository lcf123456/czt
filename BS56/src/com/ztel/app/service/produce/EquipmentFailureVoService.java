package com.ztel.app.service.produce;

import java.util.List;

import com.ztel.app.vo.produce.EquipmentFailureVo;
import com.ztel.framework.vo.Pagination;

public interface EquipmentFailureVoService {

	public List<EquipmentFailureVo> getEquipmentFailureVoPageList(Pagination<?> page);
}
