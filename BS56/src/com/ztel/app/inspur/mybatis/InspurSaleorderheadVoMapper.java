package com.ztel.app.inspur.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.inspur.vo.InspurSaleorderheadVo;

public interface InspurSaleorderheadVoMapper {
	
	/**
	 * 获取营销接口中的订单头数据
	 * @param borndate
	 * @return
	 */
	List<InspurSaleorderheadVo> selectInspursaleorderheadList(@Param("sqlstr")String sqlstr);
}