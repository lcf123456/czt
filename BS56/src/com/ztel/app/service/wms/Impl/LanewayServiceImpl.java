package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.LanewayVoMapper;
import com.ztel.app.service.wms.LanewayService;
import com.ztel.app.vo.wms.LanewayVo;
import com.ztel.framework.vo.Pagination;
@Service
public class LanewayServiceImpl implements LanewayService {

	
	@Autowired
	private LanewayVoMapper lanewayVoMapper = null;
	 @Autowired 
	 DataSource ds = null;
	 JdbcTemplate template;
	 
	 private Map<String, String> sortKeyMapping = new HashMap<>();
		
		public LanewayServiceImpl() {
			//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//			sortKeyMapping.put(key, value)
			sortKeyMapping.put("lanewayname", "lanewayname");
		}
		
	@Override
	
public List<LanewayVo> selectLanewayPageList(Pagination<?> page) {
		
		page.sortKeyToColumn(sortKeyMapping);
		return this.lanewayVoMapper.selectLanewayPageList(page);
	}
}
