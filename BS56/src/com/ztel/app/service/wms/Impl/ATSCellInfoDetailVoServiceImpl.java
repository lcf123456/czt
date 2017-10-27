package com.ztel.app.service.wms.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.ATSCellInfoDetailVoMapper;
import com.ztel.app.service.wms.ATSCellInfoDetailVoService;
import com.ztel.app.vo.wms.ATSCellInfoDetailVo;
@Service
public class ATSCellInfoDetailVoServiceImpl implements ATSCellInfoDetailVoService {

	@Autowired
	private ATSCellInfoDetailVoMapper atsCellInfoDetailVoMapper = null;
	
	//private Map<String, String> sortKeyMapping = new HashMap<>();
		
		public ATSCellInfoDetailVoServiceImpl() {
			//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
			//sortKeyMapping.put(key, value)
			//sortKeyMapping.put("createtime", "createtime");
		}
		
	@Override
	public List<ATSCellInfoDetailVo> getATSCellInfoSummary(ATSCellInfoDetailVo atsCellInfoDetailVo) {
		// TODO Auto-generated method stub
		return atsCellInfoDetailVoMapper.getATSCellInfoSummary(atsCellInfoDetailVo);
	}

}
