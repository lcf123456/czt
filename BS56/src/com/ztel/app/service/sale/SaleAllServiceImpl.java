package com.ztel.app.service.sale;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sale.SalecustomerVoMapper;
import com.ztel.app.persist.mybatis.sale.SaleitemVoMapper;
import com.ztel.app.persist.mybatis.sale.SaleorderheadVoMapper;
import com.ztel.app.persist.mybatis.sale.SaleorderlineVoMapper;
import com.ztel.app.vo.sale.SalecustomerVo;
import com.ztel.app.vo.sale.SaleitemVo;
import com.ztel.app.vo.sale.SaleorderheadVo;

import oracle.jdbc.OracleTypes;

@Service
public class SaleAllServiceImpl implements SaleAllService {

	@Autowired
	private SalecustomerVoMapper salecustomerVoMapper = null;
	
	@Autowired
	private SaleorderheadVoMapper saleorderheadVoMapper = null;
	
	@Autowired
	private SaleorderlineVoMapper saleorderlineVoMapper = null;
	
	@Autowired
	private SaleitemVoMapper saleitemVoMapper = null;
	
	@Autowired 
	 DataSource ds = null;
	 JdbcTemplate template;
	
	/**
	 * 接收到的营销数据插入之前先全部删除
	 */
	public void deletecustomer(){
		salecustomerVoMapper.deletecustomer();
	}
	@Override
	public void insertfromdb2toora(String sqlstr) {
		// TODO Auto-generated method stub
		salecustomerVoMapper.insertcustomertoora(sqlstr);
	}

	/**
	 * 接收到的营销数据插入之前先全部删除（订单头）
	 */
	public void deletesaleorderhead(String sqlstr){
		saleorderheadVoMapper.deletesaleorderhead(sqlstr);
	}

	/**
	 * 从营销接口取数据插入到本地（订单头）
	 * @param sqlstr
	 */
	public void insertsaleorderhead(String sqlstr){
		saleorderheadVoMapper.insertsaleorderhead(sqlstr);
	}
	
	/**
	 * 接收到的营销数据插入之前先全部删除（订单头）
	 */
	public void deletesaleorderline(String sqlstr){
		saleorderlineVoMapper.deletesaleorderline(sqlstr);
	}

	/**
	 * 从营销接口取数据插入到本地（订单头）
	 * @param sqlstr
	 */
	public void insertsaleorderline(String sqlstr){
		saleorderlineVoMapper.insertsaleorderline(sqlstr);
	}
	
	/**
	 * 营销接口商品信息同步之前先全部删除
	 */
	public void deleteitemAll(String sqlstr){
		saleitemVoMapper.deleteitemAll(sqlstr);
	}
	/**
	 *  营销接口商品信息同步之前先全部置为删除状态
	 */
	public void updateitemAllrowstatus(){
		saleitemVoMapper.updateitemAllrowstatus();
	}
	
	/**
	 *  营销接口商品信息同步之前先判断商品是否存在
	 */
	public SaleitemVo selectItembyPrimaryKey(String id){
		return saleitemVoMapper.selectByPrimaryKey(id);
	}
	
	/**
	 *  营销接口商品信息同步更新商品
	 */
	public int updateItembyPrimaryKey(SaleitemVo saleitemVo){
		return saleitemVoMapper.updateByPrimaryKeySelective(saleitemVo);
	}
	
	/**
	 *  营销接口商品信息同步插入商品
	 */
	public int insertItembyPrimaryKey(SaleitemVo saleitemVo){
		return saleitemVoMapper.insertSelective(saleitemVo);
	}
	
	/**
	 * 中间表t_sale_customer到正是表t_wms_customer的数据同步
	 */
	public String doSyncCustomer(){
		initJdbcTemplate();
		 String result = (String) template.execute(   
			     new CallableStatementCreator() {   
			        public CallableStatement createCallableStatement(Connection con) throws SQLException {   
			           String storedProc = "{call psale_wms_customer (?,?)}";// 调用的sql   
			          CallableStatement cs = con.prepareCall(storedProc);   
			           cs.registerOutParameter(1,OracleTypes.VARCHAR);// 注册输出参数的类型   
			           cs.registerOutParameter(2,OracleTypes.VARCHAR);// 注册输出参数的类型   
			           return cs;   
			        }   
			     }, new CallableStatementCallback() {   
			         public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {   
			           cs.execute();   
//			           Map map = new HashMap();
//			           map.put("p_ErrCode", cs.getString(1)); // p_ErrCode:='0'; p_ErrMsg:='零售户同步成功';  p_ErrCode:='1'; p_ErrMsg:='零售户同步过程中发生异常:'||to_char(SQLERRM);
//			           map.put("p_ErrMsg", cs.getString(2)); // 
//			           return map;
			           return cs.getString(2);
			     }   
			  });   
		return result;
	}
	
	/**
	 * 中间表t_sale_item到正是表t_wms_item的数据同步
	 */
	public String doSyncItem(){
		initJdbcTemplate();
		 String result = (String) template.execute(   
			     new CallableStatementCreator() {   
			        public CallableStatement createCallableStatement(Connection con) throws SQLException {   
			           String storedProc = "{call psale_wms_item (?,?)}";// 调用的sql   
			           CallableStatement cs = con.prepareCall(storedProc);   
			           cs.registerOutParameter(1,OracleTypes.VARCHAR);// 注册输出参数的类型   
			           cs.registerOutParameter(2,OracleTypes.VARCHAR);// 注册输出参数的类型   
			           return cs;   
			        }   
			     }, new CallableStatementCallback() {   
			         public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {   
			           cs.execute();   
//			           Map map = new HashMap();
//			           map.put("p_ErrCode", cs.getString(1)); // p_ErrCode:='0'; p_ErrMsg:='零售户同步成功';  p_ErrCode:='1'; p_ErrMsg:='零售户同步过程中发生异常:'||to_char(SQLERRM);
//			           map.put("p_ErrMsg", cs.getString(2)); // 
//			           return map;
			           return cs.getString(2);
			     }   
			  });   
		return result;
	}
	
	/**
	 * 中间表t_sale_order_head和t_sale_order_line到正是表t_wms_shiporder和t_wms_shiporder_line的数据同步
	 */
	public String doSyncOrder(final String orderdate){
		initJdbcTemplate();
		 String result = (String) template.execute(   
			     new CallableStatementCreator() {   
			        public CallableStatement createCallableStatement(Connection con) throws SQLException {   
			           String storedProc = "{call PSALE_WMS_RECEIVE (?,?,?)}";// 调用的sql   
			           CallableStatement cs = con.prepareCall(storedProc);   
			           cs.setString(1, orderdate);
			           cs.registerOutParameter(2,OracleTypes.VARCHAR);// 注册输出参数的类型   
			           cs.registerOutParameter(3,OracleTypes.VARCHAR);// 注册输出参数的类型   
			           return cs;   
			        }   
			     }, new CallableStatementCallback() {   
			         public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {   
			           cs.execute();   
//			           Map map = new HashMap();
//			           map.put("p_ErrCode", cs.getString(1)); // p_ErrCode:='0'; p_ErrMsg:='零售户同步成功';  p_ErrCode:='1'; p_ErrMsg:='零售户同步过程中发生异常:'||to_char(SQLERRM);
//			           map.put("p_ErrMsg", cs.getString(2)); // 
//			           return map;
			           return cs.getString(3);
			     }   
			  });   
		return result;
	}
	
	public void initJdbcTemplate()
	{
		template=new JdbcTemplate(ds);
	}
	
	/**
	 * 取所有商品信息，在同步到本地的页面需要统计商品数量
	 * @return
	 */
	public int selectAllItemCount(){
		return saleitemVoMapper.selectAllItemCount();
	}
	
	/**
	 * 取所有零售户信息，在同步到本地的页面需要统计零售户数量
	 * @return
	 */
	public int selectAllCustomerCount(){
		return salecustomerVoMapper.selectAllCustomerCount();
	}
	
	/**
	 * 取所有订单信息，在同步到本地的页面需要统计订单数量等信息 ,此处SaleorderheadVo里面的id为户数
	 * @return
	 */
	public SaleorderheadVo selectAllOrderheadVo(String orderdate){
		return saleorderheadVoMapper.selectAllOrderheadVo(orderdate);
	}
	
	/**
	 * 取所有订单明细数量，在同步到本地的页面需要统计订单明细数量 
	 * @return
	 */
	public int selectAllOrderlineCount(String orderdate){
		return saleorderheadVoMapper.selectAllOrderlineCount(orderdate);
	}
	/**
	 * 取订单头信息的数量，扣款同步时判断是否存在该订单数据，然后更新扣款信息
	 * @param sqlstr
	 */
	public SaleorderheadVo selectsaleorderheadBypriKey(String orderno){
		return saleorderheadVoMapper.selectByPrimaryKey(orderno);
	}
	
	/**
	 * 更新扣款信息
	 * @param orderno
	 */
	public int updateorderheadBypriKey(SaleorderheadVo saleorderheadVo){
		return saleorderheadVoMapper.updateByPrimaryKeySelective(saleorderheadVo);
	}
}
