package com.ztel.app.inspur.mybatis;

import java.util.List;

import com.ztel.app.inspur.vo.InspurSalecustomerVo;

public interface InspurSalecustomerVoMapper {
	/**
	 * 获取营销零售户列表
	 * @return
	 */
	List<InspurSalecustomerVo> selectInspurCustomerList();
}