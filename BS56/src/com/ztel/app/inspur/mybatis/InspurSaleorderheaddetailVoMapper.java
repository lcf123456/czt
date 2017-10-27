package com.ztel.app.inspur.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.inspur.vo.InspurSaleorderheaddetailVo;

public interface InspurSaleorderheaddetailVoMapper {
	
	/**
	 * 获取营销订单明细列表
	 * @param borndate
	 * @return
	 */
	List<InspurSaleorderheaddetailVo> selectInspursaleorderheaddetailList(@Param("borndate")String borndate);
}