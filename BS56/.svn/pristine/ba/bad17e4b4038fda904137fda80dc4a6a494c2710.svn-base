package com.ztel.app.service.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sys.SysparameterVoMapper;
import com.ztel.app.service.sys.SysparameterService;
import com.ztel.app.vo.sys.SysparameterVo;
import com.ztel.framework.vo.Pagination;

@Service
public class SysparameterServiceImpl implements SysparameterService{

	@Autowired
	private SysparameterVoMapper sysparameterVoMapper =null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public SysparameterServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
        //sortKeyMapping.put(key, value)
		sortKeyMapping.put("paraname_E", "paraname_E");
		sortKeyMapping.put("id", "id");
		sortKeyMapping.put("paraname_C", "paraname_C");
	}
	
	@Override
	public List<SysparameterVo> getSysparameterPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.sysparameterVoMapper.getSysparameterPageList(page);
	}

	@Override
	public int delSysparameter(List<Integer> ids) {
		// TODO Auto-generated method stub
		if(ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				this.sysparameterVoMapper.deleteByPrimaryKey(id);
			}
			return ids.size();
		}
		return 0;
	}

	@Override
	public int insertSysparameter(SysparameterVo sysparameterVo) {
		// TODO Auto-generated method stub
		if (sysparameterVo!=null&&!"".equals(sysparameterVo.getId())) {
			return sysparameterVoMapper.insertSelective(sysparameterVo);
		}
		return 0;
	}

	@Override
	public int updateSysparameter(SysparameterVo sysparameterVo) {
		// TODO Auto-generated method stub
		if (sysparameterVo!=null&&!"".equals(sysparameterVo.getId())) {
			return sysparameterVoMapper.updateByPrimaryKeySelective(sysparameterVo);
		}
		return 0;
	}
		 
	}
	
