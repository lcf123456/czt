package com.ztel.app.persist.mybatis.cost;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.cost.SupplierInfoVo;
import com.ztel.framework.vo.Pagination;

public interface SupplierInfoVoMapper {
	

    /**
     *
     * @mbggenerated 2017-06-27
     */
    int insert(SupplierInfoVo record);

    /**
     *
     * @mbggenerated 2017-06-27
     */
    int insertSelective(SupplierInfoVo record);
    /**
    *
    * @mbggenerated 2017-06-27
    */    
    
    int deleteByPrimaryKey(Integer id);
    /**
    *
    * @mbggenerated 2017-06-27
    */
   int updateByPrimaryKeySelective(SupplierInfoVo record);

   /**
    *
    * @mbggenerated 2017-06-27
    */
   int updateByPrimaryKey(SupplierInfoVo record);
   /**
   *
   * @mbggenerated 2017-06-27
   */
   int viewSupplierInfoVo(SupplierInfoVo supplierInfoVo);
   
   List<SupplierInfoVo> getSupplierInfoVoPageList(Pagination<?> page);
   
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
   
}