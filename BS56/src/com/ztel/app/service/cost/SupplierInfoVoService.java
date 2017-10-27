package com.ztel.app.service.cost;

import java.util.HashMap;
import java.util.List;
import com.ztel.app.vo.cost.SupplierInfoVo;
import com.ztel.framework.vo.Pagination;


/**
 * 
 * @author SN
 * @since 2017年6月28日
 */
public interface SupplierInfoVoService {

	/**
	 * 取供应商列表
	 * @return
	 */
	List<SupplierInfoVo> getSuppliersList(SupplierInfoVo supplierInfoVo);
	
	/**
	 * 供应商下拉列表
	 * @return
	 */
	List<HashMap<String, String>> getSuppliersCombobox(SupplierInfoVo supplierInfoVo);
	
	public int delSupplierInfoVo(List<Integer> ids);

	public int insertSupplierInfoVo(SupplierInfoVo SupplierInfoVo);

	public int updateSupplierInfoVo(SupplierInfoVo SupplierInfoVo);
	
	public int viewSupplierInfoVo(SupplierInfoVo SupplierInfoVo);
	
	List<SupplierInfoVo> getSupplierInfoVoPageList(Pagination<?> page);
	
}
