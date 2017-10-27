/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sys.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sys.MenuinfoVoMapper;
import com.ztel.app.persist.mybatis.sys.OperationinfoVoMapper;
import com.ztel.app.persist.mybatis.sys.OperationrolerelativeVoMapper;
import com.ztel.app.service.sys.OperationinfoService;
import com.ztel.app.vo.sys.MenuInfoVo;
import com.ztel.app.vo.sys.OperationinfoVo;

/**
 * @author Administrator
 * @since 2017年2月27日
 */
@Service
public class OperationinfoServiceImpl implements OperationinfoService {
	
	@Autowired
	private OperationinfoVoMapper operationinfoVoMapper = null;
	 
	@Autowired
	OperationrolerelativeVoMapper operationrolerelativeVoMapper=null;
	
	@Autowired
	private MenuinfoVoMapper menuinfoVoMapper = null;
	
	public OperationinfoServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
	}
	
	public List<OperationinfoVo> getOperationinfoList(String mcode) {
		return this.operationinfoVoMapper.getOperationinfoList(mcode);
	}
	
	/**
	 * 新增
	 * @param operationinfoVo
	 * @return
	 */
	public int operationNew(OperationinfoVo operationinfoVo){
		return  this.operationinfoVoMapper.insertSelective(operationinfoVo);
	}
	
	/**
	 * 修改
	 */
	public int operationUpdate(OperationinfoVo operationinfoVo){
		return this.operationinfoVoMapper.updateByPrimaryKeySelective(operationinfoVo);
	}
	
	/**
	 * 删除
	 */
	public void operationDel(List<Integer> ids)
	{
		if(ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				BigDecimal idD = new BigDecimal(Integer.toString(id));
				this.operationinfoVoMapper.deleteByPrimaryKey(idD);
				//功能点删除的同时，删除功能点授权表中的相应记录
				operationrolerelativeVoMapper.deleteByOperationid(idD+"");
			}
		}
	}
	
	
}
