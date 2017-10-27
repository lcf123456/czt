package com.ztel.app.service.cost.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ztel.app.persist.mybatis.cost.SPLConsumeVoMapper;
import com.ztel.app.service.cost.SPLConsumeServcie;
import com.ztel.app.service.sys.DeptVoService;
import com.ztel.app.vo.cost.SPLConsumeVo;
import com.ztel.app.vo.sys.DeptTreeVo;
import com.ztel.framework.vo.Pagination;
@Service
public class SPLConsumeServiceImpl implements SPLConsumeServcie {

	@Autowired
	private SPLConsumeVoMapper splConsumeVoMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	@Autowired 
	private DeptVoService deptVoService = null;
	
	@Override
	public List<SPLConsumeVo> getSPLConsumeVoPageList(Pagination<?> page) {
		page.sortKeyToColumn(sortKeyMapping);
		return this.splConsumeVoMapper.getSPLConsumeVoPageList(page);
	}
	@Override
	public List<HashMap<String, String>> getDeptCombobox() {
		// TODO 自动生成的方法存根
		return null;
	}


	@Override
	public List<SPLConsumeVo> getSPLConsummaryList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.splConsumeVoMapper.getSPLConsummaryList(page);
	}


	}
