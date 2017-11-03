package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.wms.InBoundLineVoMapper;
import com.ztel.app.service.wms.InBoundLineService;
import com.ztel.app.service.wms.InBoundService;
import com.ztel.app.vo.wms.InBoundLineVo;
import com.ztel.app.vo.wms.InBoundVo;

@Service
public class InBoundLineServiceImpl implements InBoundLineService {

	@Autowired
	private InBoundLineVoMapper inBoundLineVoMapper = null;
	
	@Autowired
	private InBoundService inBoundService = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	public InBoundLineServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("inbounddetailid", "inbounddetailid");
		sortKeyMapping.put("cigarettecode", "cigarettecode");
		sortKeyMapping.put("cigarettename", "cigarettename");
	}

	@Override
	public void InsertInBoundLine(InBoundLineVo vo) {
		// TODO Auto-generated method stub
		inBoundLineVoMapper.insertSelective(vo);
	}

	public List<InBoundLineVo> selectListByCond(InBoundLineVo inBoundLineVo){
		return inBoundLineVoMapper.selectListByCond(inBoundLineVo);
	}

	@Override
	public void updateInBoundLine(InBoundLineVo vo) {
		// TODO Auto-generated method stub
		inBoundLineVoMapper.updateByPrimaryKeySelective(vo);
	}
	
	/**
	 * 管控中心：仓储管控：入库汇总
	 * @param inBoundVo
	 * @return
	 */
	public List<InBoundLineVo> selectInboundReportListByCond(InBoundVo inBoundVo){
		return inBoundLineVoMapper.selectInboundReportListByCond(inBoundVo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doInboundLineDel(List<String> inbounddetailids,String inboundid,String totalnum) {
		// TODO Auto-generated method stub
		for(int i=0;i<inbounddetailids.size();i++){
			//删除入库单明细
			inBoundLineVoMapper.deleteByPrimaryKey(new BigDecimal(inbounddetailids.get(i)));
			//更新入库单的数量
			InBoundVo inBoundVo=new InBoundVo();
			inBoundVo.setInboundid(new BigDecimal(inboundid));
			inBoundVo.setQty(new BigDecimal(totalnum));
			inBoundService.doUpdateInboundNumById(inBoundVo);
		}
	}

	@Override
	public void updateInBoundLineByInboundId(InBoundLineVo vo) {
		// TODO Auto-generated method stub
		inBoundLineVoMapper.updateByInboundidSelective(vo);
	}
}
