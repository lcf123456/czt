package com.ztel.app.service.sale;

/**
 * 营销全部中间表接收处理服务
 * @author lcf
 *
 */
public interface SaleAllService {
	
	/**
	 * 接收到的营销数据插入之前先全部删除
	 */
	public void deletecustomer();

	/**
	 * 从营销接口取数据插入到本地
	 * @param sqlstr
	 */
	public void insertfromdb2toora(String sqlstr);
	
	/**
	 * 接收到的营销数据插入之前先全部删除（订单头）
	 */
	public void deletesaleorderhead(String sqlstr);

	/**
	 * 从营销接口取数据插入到本地（订单头）
	 * @param sqlstr
	 */
	public void insertsaleorderhead(String sqlstr);
	
	/**
	 * 接收到的营销数据插入之前先全部删除（订单头）
	 */
	public void deletesaleorderline(String sqlstr);

	/**
	 * 从营销接口取数据插入到本地（订单头）
	 * @param sqlstr
	 */
	public void insertsaleorderline(String sqlstr);
}
