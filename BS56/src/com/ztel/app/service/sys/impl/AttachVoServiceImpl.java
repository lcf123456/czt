package com.ztel.app.service.sys.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sys.AttachVoMapper;
import com.ztel.app.service.sys.AttachVoService;
import com.ztel.app.vo.sys.AttachVo;

@Service
public class AttachVoServiceImpl implements AttachVoService {

	@Autowired
	private AttachVoMapper attachVOMapper = null;
	 @Autowired 
	 DataSource ds = null;
	 JdbcTemplate template;
	@Override
	public void insertAttachVO(AttachVo entity) {
		initJdbcTemplate();
		// TODO Auto-generated method stub
		int id = (Integer) template.execute("select s_sys_attach.nextval from dual",new PreparedStatementCallback() {  
		   public Integer doInPreparedStatement(PreparedStatement pstmt) throws SQLException, DataAccessException {  
			       pstmt.execute();  
		        ResultSet rs = pstmt.getResultSet();  
		        rs.next();  
		        return rs.getInt(1);  
			    }  
			});  
        entity.setId(id);
		attachVOMapper.insertSelective(entity);
	}

	public void initJdbcTemplate()
	{
		template=new JdbcTemplate(ds);
	}

	@Override
	public void deleteAttachVo(int id) {
		// TODO Auto-generated method stub
		attachVOMapper.deleteByPrimaryKey(id);
	}
}
