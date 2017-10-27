package com.ztel.app.service.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sale.SalecustomerVoMapper;
import com.ztel.app.persist.mybatis.sale.SaleorderheadVoMapper;
import com.ztel.app.persist.mybatis.sale.SaleorderlineVoMapper;

@Service
public class SaleAllServiceImpl implements SaleAllService {

	@Autowired
	private SalecustomerVoMapper salecustomerVoMapper = null;
	
	@Autowired
	private SaleorderheadVoMapper saleorderheadVoMapper = null;
	
	@Autowired
	private SaleorderlineVoMapper saleorderlineVoMapper = null;
	
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
	
}
