package com.ztel.app.service.perform.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.perform.TransverseAssessVoMapper;
import com.ztel.app.service.perform.TransverseAssessService;
import com.ztel.app.vo.perform.TransverseAssessVo;
import com.ztel.framework.vo.Pagination;

@Service
public class TransverseAssessServiceImpl implements TransverseAssessService {

	@Autowired(required=true)
	private TransverseAssessVoMapper transverseAssessVoMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public TransverseAssessServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("assessdate", "assessdate");
		sortKeyMapping.put("id", "id");
	}
	
	@Override
	public List<TransverseAssessVo> selectTransverseAssessPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return transverseAssessVoMapper.selectTransverseAssessPageList(page);
	}
	
	public List<TransverseAssessVo>  selectTransverseAssessList(TransverseAssessVo transverseAssessVo){
		List<TransverseAssessVo> result = new ArrayList<TransverseAssessVo>();
		//需要价格合计
		
		BigDecimal amountAll = new BigDecimal("0");
		List<TransverseAssessVo> transverseAssessVoList = transverseAssessVoMapper.selectTransverseAssessList(transverseAssessVo);
		if(transverseAssessVoList != null && transverseAssessVoList.size()>0){
			for(int i=0;i<transverseAssessVoList.size();i++){
				TransverseAssessVo transverseAssessVo1= transverseAssessVoList.get(i);
				amountAll = amountAll.add(transverseAssessVo1.getAmount());
				result.add(transverseAssessVo1);
			}
			TransverseAssessVo transverseAssessVo_hj= new TransverseAssessVo();
			transverseAssessVo_hj.setContent("合计");
			transverseAssessVo_hj.setAmount(amountAll);
			result.add(transverseAssessVo_hj);
		}
		return result;
	}

	@Override
	public void insertTransverseAssess(TransverseAssessVo transverseAssessVo) {
		// TODO Auto-generated method stub
		transverseAssessVoMapper.insertSelective(transverseAssessVo);
	}

	@Override
	public void doAudit(TransverseAssessVo transverseAssessVo) {
		// TODO Auto-generated method stub
		transverseAssessVoMapper.updateByPrimaryKeySelective(transverseAssessVo);
	}

	@Override
	public void doDel(BigDecimal id) {
		// TODO Auto-generated method stub
		transverseAssessVoMapper.deleteByPrimaryKey(id);
	}

}
