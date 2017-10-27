/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sq;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.sq.CigfactoryVo;
import com.ztel.framework.vo.Pagination;

/**
 * @author yy
 * @since 2017年5月12日
 */
public interface CigfactoryService {
	public List<CigfactoryVo> searchCigfactoryList(Pagination<?> page);
	public void deleteCigfactoryById(List<Integer> ids);
	public void newCigfactory(CigfactoryVo cigfactory);
	public void updateCigfactory(CigfactoryVo cigfactory);
	/**
	 * 取厂家下拉框
	 * @return
	 */
	public List<HashMap<String, String>>getCigfactoryCombobox();

	
	public List<CigfactoryVo> getCigfactoryinfoList(CigfactoryVo vo,String keywd);
	int insertCigfactoryVo();
}
