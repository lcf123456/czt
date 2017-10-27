package com.ztel.app.service.sq.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sq.EvalitemVoMapper;
import com.ztel.app.service.sq.EvalItemService;
import com.ztel.app.vo.sq.EvalitemVo;
import com.ztel.framework.vo.Pagination;

@Service
public class EvalItemServiceImpl implements EvalItemService{

    @Autowired
    private EvalitemVoMapper evalitemVoMapper =null;
	
	@Override
	public List<EvalitemVo> getAutoItemPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		return this.evalitemVoMapper.getAutoItemPageList(page);
	}

	@Override
	public int delEvalItem(List<Integer> ids) {
		// TODO Auto-generated method stub
		if(ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				this.evalitemVoMapper.deleteByPrimaryKey(id);
			}
			return ids.size();
		}
		return 0;
	}

	@Override
	public int updateEvalItem(EvalitemVo evalitemVo) {
		// TODO Auto-generated method stub
		return this.evalitemVoMapper.updateByPrimaryKeySelective(evalitemVo);
	}

	@Override
	public int inertEvalItem(EvalitemVo evalitemVo) {
		// TODO Auto-generated method stub
		return this.evalitemVoMapper.insertSelective(evalitemVo);
	}

	@Override
	public List<EvalitemVo> getItemList(EvalitemVo evalitemVo) {
		// TODO Auto-generated method stub
		return this.evalitemVoMapper.getItemList(evalitemVo);
	}
		 
}
	
