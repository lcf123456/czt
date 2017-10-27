/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sys;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.sys.OperationinfoVo;

/**
 * @author lcf
 * @since 2017年2月27日
 */
public interface OperationinfoService {
	/**
	 * 根据菜单编码查找功能点信息
	 * @param menucode
	 * @return
	 */
	public List<OperationinfoVo> getOperationinfoList(String menucode);
	
	/**
	 * 新增
	 * @param operationinfoVo
	 * @return
	 */
	public int operationNew(OperationinfoVo operationinfoVo);

	/**
	 * 修改
	 */
	public int operationUpdate(OperationinfoVo operationinfoVo);
	
	/**
	 * 删除
	 */
	public void operationDel(List<Integer> ids);
	
}
