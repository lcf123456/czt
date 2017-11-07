package com.ztel.app.service.sale;

import java.util.List;

import com.ztel.app.vo.sale.SalecustomerVo;
import com.ztel.app.vo.sale.SaleitemVo;
import com.ztel.app.vo.sale.SaleorderheadVo;

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
	
	/**
	 * 营销接口商品信息同步之前先全部删除
	 * @param sqlstr
	 */
	public void deleteitemAll(String sqlstr);
	/**
	 *  营销接口商品信息同步之前先全部置为删除状态
	 */
	public void updateitemAllrowstatus();
	
	/**
	 *  营销接口商品信息同步之前先判断商品是否存在
	 */
	public SaleitemVo selectItembyPrimaryKey(String id);
	
	/**
	 *  营销接口商品信息同步更新商品
	 */
	public int updateItembyPrimaryKey(SaleitemVo saleitemVo);
	
	/**
	 *  营销接口商品信息同步插入商品
	 */
	public int insertItembyPrimaryKey(SaleitemVo saleitemVo);
	
	/**
	 * 中间表t_sale_customer到正是表t_wms_customer的数据同步
	 */
	public String doSyncCustomer();
	
	/**
	 * 中间表t_sale_item到正是表t_wms_item的数据同步
	 */
	public String doSyncItem();
	
	/**
	 * 中间表t_sale_order_head和t_sale_order_line到正式表t_wms_shiporder和t_wms_shiporder_line的数据同步
	 */
	public String doSyncOrder(String orderdate);
	
	/**
	 * 中间表t_sale_order_head到正式表t_wms_shiporder银行结算数据同步
	 */
	public String doSyncOrderStmtflag(String orderdate);
	
	/**
	 * 取所有商品数量，在同步到本地的页面需要统计商品数量
	 * @return
	 */
	public int selectAllItemCount();
	
	/**
	 * 取所有零售户数量，在同步到本地的页面需要统计零售户数量
	 * @return
	 */
	public int selectAllCustomerCount();
	
	/**
	 * 取所有订单信息，在同步到本地的页面需要统计订单数量等信息 ,此处SaleorderheadVo里面的id为户数
	 * @return
	 */
	public SaleorderheadVo selectAllOrderheadVo(String orderdate);
	
	/**
	 * 取所有订单明细数量，在同步到本地的页面需要统计订单明细数量 
	 * @return
	 */
	public int selectAllOrderlineCount(String orderdate);
	
	/**
	 * 取订单头信息的数量，扣款同步时判断是否存在该订单数据，然后更新扣款信息
	 * @param orderno
	 */
	public SaleorderheadVo selectsaleorderheadBypriKey(String orderno);
	
	/**
	 * 更新扣款信息
	 * @param orderno
	 */
	public int updateorderheadBypriKey(SaleorderheadVo saleorderheadVo);
}
