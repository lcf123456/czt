package com.ztel.app.service.wms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ztel.app.vo.wms.CustomerVo;
import com.ztel.framework.vo.Pagination;

public interface CustomerService {

	public List<CustomerVo>  getListByCond(CustomerVo customerVo);
	
	public List<CustomerVo> getCustomersPageList(Pagination<?> page);

	public void viewCustomerVo(CustomerVo customervo);
	
	public List<CustomerVo> getOneLevelPrepayCustomers(CustomerVo  customerVo);
	
	public List<CustomerVo> getPrepayCustomers(CustomerVo  customerVo);
	
	public int doCustomerAdd(CustomerVo  customerVo);
	
	public int doCustomerPLAdd(List<String>  ids);
	
	public int doBillCustomerPLAdd(String billtype,List<String>  ids);
	
	public int doDelPrepayCustomer(List<String>  ids);
	
	public int doDelBillCustomer(List<String>  ids);
	
	public List<CustomerVo> getBillCustomersPageList(Pagination<?> page);
	
	/**
	 * 预付款一级客户下拉框
	 * @return
	 */
	public List<HashMap<String, String>> getPrepaycustomerCombobox();
}
