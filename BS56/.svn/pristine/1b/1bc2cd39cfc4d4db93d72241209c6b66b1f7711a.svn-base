package com.ztel.app.persist.mybatis.wms;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ztel.app.vo.wms.CustomerVo;
import com.ztel.framework.vo.Pagination;

public interface CustomerVoMapper {
	
	public List<CustomerVo> selectListByCond(CustomerVo customerVo);
	
	public List<CustomerVo> getCustomersPageList(Pagination<?> page);
	
	public List<CustomerVo> getPrepayCustomers(CustomerVo  customerVo);
	
	public List<CustomerVo> getOneLevelPrepayCustomers(Map<String, Object> paraMap);
	
	public List<CustomerVo> getOneLevelPrepayCustomersIdStr(CustomerVo  customerVo);
	
	public int delAllTwoLevelPrepayCustomerByParentid(String prepayparentid);
	
	public List<CustomerVo> getBillCustomersPageList(Pagination<?> page);
    /**
     *
     * @mbggenerated 2017-05-31
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-05-31
     */
    int insert(CustomerVo record);

    /**
     *
     * @mbggenerated 2017-05-31
     */
    int insertSelective(CustomerVo record);

    /**
     *
     * @mbggenerated 2017-05-31
     */
    CustomerVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-05-31
     */
    int updateByPrimaryKeySelective(CustomerVo record);

    /**
     *
     * @mbggenerated 2017-05-31
     */
    int updateByPrimaryKey(CustomerVo record);
    
   int viewCustomerVo(CustomerVo record);
}