package com.ztel.app.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;


@Service
public class PubServiceImpl implements PubService {
	 @Autowired 
	 DataSource ds = null;
	 JdbcTemplate template;
	/**
	 * 取序列
	 * @param sequence：序列名称
	 * @return
	 */
	public Long getSequence(String sequence){
		initJdbcTemplate();
		// TODO Auto-generated method stub
				Long id = (Long) template.execute("select "+sequence+".nextval from dual",new PreparedStatementCallback() {  
				   public Long doInPreparedStatement(PreparedStatement pstmt) throws SQLException, DataAccessException {  
					       pstmt.execute();  
				        ResultSet rs = pstmt.getResultSet();  
				        rs.next();  
				        return rs.getLong(1);  
					    }  
					});  
		return id;
	}
	public void initJdbcTemplate()
	{
		template=new JdbcTemplate(ds);
	}

}
