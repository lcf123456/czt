package com.ztel.app.service.wms.Impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.ShipOrderLineVoMapper;
import com.ztel.app.service.wms.ShipOrderLineService;
import com.ztel.app.vo.wms.ShipOrderLineVo;
@Service
public class ShipOrderLineServiceImpl implements ShipOrderLineService {

	
	@Autowired
	private ShipOrderLineVoMapper shipOrderLineVoMapper = null;
	 
	private Map<String, String> sortKeyMapping = new HashMap<>();
		
	public ShipOrderLineServiceImpl() {
			//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
			//sortKeyMapping.put(key, value)
	}

	@Override
	public List<ShipOrderLineVo> getShipOrderLineByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return shipOrderLineVoMapper.getShipOrderLineByOrderNo(orderNo);
	}

	@Override
	public int doUpdateShipOrderLine(ShipOrderLineVo shipOrderLineVo) {
		// TODO Auto-generated method stub
		return shipOrderLineVoMapper.updateByPrimaryKeySelective(shipOrderLineVo);
	}
	
	/**
	 * 工商协同：品牌汇总
	 * @param orderdatestr
	 * @return
	 */
	public List<ShipOrderLineVo> getShiporderBrandReport(String orderdatestr){
		List<ShipOrderLineVo> resultList = shipOrderLineVoMapper.getShiporderBrandReport(orderdatestr);
//		if(resultList!=null&&resultList.size()>0){
//			Collections.reverse(resultList);//反序排列
//		}
		return resultList;
	}
	
}
