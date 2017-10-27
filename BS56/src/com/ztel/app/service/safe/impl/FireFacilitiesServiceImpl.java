package com.ztel.app.service.safe.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.safe.FireFacilitiesVoMapper;
import com.ztel.app.persist.mybatis.sys.BlockcustomerVoMapper;
import com.ztel.app.service.safe.FireFacilitiesService;
import com.ztel.app.vo.safe.FireFacilitiesVo;
import com.ztel.app.vo.sys.BlockcustomerVo;
import com.ztel.app.vo.sys.RoleInfoVo;
import com.ztel.framework.vo.Pagination;


@Service
public class FireFacilitiesServiceImpl implements FireFacilitiesService {
	@Autowired
	private FireFacilitiesVoMapper firefacilitiesVoMapper = null;
    private Map<String, String> sortKeyMapping = new HashMap<>();
	
    public FireFacilitiesServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("deviceid", "deviceid");
		sortKeyMapping.put("id", "id");
	}
	
	
	@Override
	public List<FireFacilitiesVo> searchFireFacilitiesPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.firefacilitiesVoMapper.getFireFacilitiesPageList(page);
	}
	
	
	


}
