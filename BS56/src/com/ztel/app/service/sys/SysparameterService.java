/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sys;

import java.util.List;

import com.ztel.app.vo.sys.SysparameterVo;
import com.ztel.framework.vo.Pagination;

/**
 * @author NJ
 * @since 2017年4月18日
 */
public interface SysparameterService {
	
	public List<SysparameterVo> getSysparameterPageList(Pagination<?> page);

	public int delSysparameter(List<Integer> ids);

	public int insertSysparameter(SysparameterVo sysparameterVo);

	public int updateSysparameter(SysparameterVo sysparameterVo);
}
