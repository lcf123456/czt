package com.ztel.app.service.sq.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sq.VoiceparaVoMapper;
import com.ztel.app.service.sq.VoiceparaService;
import com.ztel.app.vo.sq.VoiceparaVo;
import com.ztel.framework.vo.Pagination;

@Service
public class VoiceparaServiceImpl implements VoiceparaService{

	@Autowired
	private VoiceparaVoMapper voiceparaVoMapper =null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public VoiceparaServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
        //sortKeyMapping.put(key, value)
		sortKeyMapping.put("paraname", "paraname");
		sortKeyMapping.put("id", "id");
	}
	
	@Override
	public List<VoiceparaVo> getVoiceparaPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.voiceparaVoMapper.getVoiceparaPageList(page);
	}



	@Override
	public int insertVoicepara(VoiceparaVo voiceparaVo) {
		// TODO Auto-generated method stub
		if (voiceparaVo!=null&&!"".equals(voiceparaVo.getId())) {
			return voiceparaVoMapper.insertSelective(voiceparaVo);
		}
		return 0;
	}



	@Override
	public int updateVoicepara(VoiceparaVo voiceparaVo) {
		// TODO Auto-generated method stub
		if (voiceparaVo!=null&&!"".equals(voiceparaVo.getId())) {
			return voiceparaVoMapper.updateByPrimaryKeySelective(voiceparaVo);
		}
		return 0;
	}
		 
	}
	
