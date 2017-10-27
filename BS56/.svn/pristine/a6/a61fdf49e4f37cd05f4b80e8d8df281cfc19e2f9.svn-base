package com.ztel.app.service.safe.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.safe.FireChecknoteVoMapper;
import com.ztel.app.persist.mybatis.safe.FireFacilitiesVoMapper;
import com.ztel.app.persist.mybatis.sys.BlockcustomerVoMapper;
import com.ztel.app.service.safe.FireChecknoteService;
import com.ztel.app.service.safe.FireFacilitiesService;
import com.ztel.app.vo.safe.FireChecknoteVo;
import com.ztel.app.vo.safe.FireFacilitiesVo;
import com.ztel.app.vo.sys.BlockcustomerVo;
import com.ztel.app.vo.sys.RoleInfoVo;
import com.ztel.framework.vo.Pagination;


@Service
public class FireChecknoteServiceImpl implements FireChecknoteService {
	@Autowired
	private FireChecknoteVoMapper firechecknoteVoMapper = null;
    private Map<String, String> sortKeyMapping = new HashMap<>();
	
    public FireChecknoteServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("createid", "createid");
		sortKeyMapping.put("id", "id");
	}
	
	
	@Override
	public List<FireChecknoteVo> searchFireChecknotePageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.firechecknoteVoMapper.getFireChecknotePageList(page);
	}
	
	
	


}
