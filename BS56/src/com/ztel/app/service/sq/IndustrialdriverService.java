/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sq;

import java.util.List;

import com.ztel.app.vo.sq.IndustrialdriverVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;

/**
 * @author yy
 * @since 2017年5月20日
 */
public interface IndustrialdriverService {
	public List<IndustrialdriverVo> searchIndustrialdriverList(Pagination<?> page);
	public void deleteIndustrialdriverById(List<Integer> ids);
	public void newIndustrialdriver(IndustrialdriverVo industrialdriver);
	public void updateIndustrialdriver(IndustrialdriverVo industrialdriver);
	public String getDrivername();
	public List<IndustrialdriverVo> getIndustrialdriverVoListByFactoryId(Integer factoryid);
}
