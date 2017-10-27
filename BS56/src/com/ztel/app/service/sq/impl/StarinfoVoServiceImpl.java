package com.ztel.app.service.sq.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sq.StarinfoVoMapper;
import com.ztel.app.service.sq.StarinfoVoService;
import com.ztel.app.vo.sq.StarinfoVo;
import com.ztel.framework.vo.Pagination;

@Service
public class StarinfoVoServiceImpl implements StarinfoVoService{

@Autowired
    private StarinfoVoMapper starinfoVoMapper =null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public StarinfoVoServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
        //sortKeyMapping.put(key, value)
		sortKeyMapping.put("starname", "starname");
		sortKeyMapping.put("id", "id");
		sortKeyMapping.put("minscore", "minscore");
		sortKeyMapping.put("maxscore", "maxscore");
	}
	
	//@Override
	public List<StarinfoVo> getStarinfoPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.starinfoVoMapper.getStarinfoPageList(page);
	//	return null;
	}

	@Override
	public int delStarinfo(List<Integer> ids) {
		// TODO Auto-generated method stub
		if(ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				this.starinfoVoMapper.deleteByPrimaryKey(id);
			}
			return ids.size();
		}
		return 0;
	}

	@Override
	public int insertStarinfo(StarinfoVo starinfoVo) {
		// TODO Auto-generated method stub
		if (starinfoVo!=null&&!"".equals(starinfoVo.getId())) {
			return starinfoVoMapper.insertSelective(starinfoVo);
		}
		return 0;
	}

	@Override
	public int updateStarinfo(StarinfoVo starinfoVo) {
		// TODO Auto-generated method stub
		if (starinfoVo!=null&&!"".equals(starinfoVo.getId())) {
			return starinfoVoMapper.updateByPrimaryKeySelective(starinfoVo);
		}
		return 0;
	}

//	@Override
	//public int insertStarinfo(StarinfoVo starinfoVo) {
		// TODO Auto-generated method stub
	//	return 0;
//	}
//
//	@Override
//	public int updateStarinfo(StarinfoVo starinfoVo) {
		// TODO Auto-generated method stub
//		return 0;
//	}
		 
	}
	
