package com.ztel.app.persist.mybatis.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sys.BusinessRoleRelativeVo;

public interface BusinessRoleRelativeVoMapper {
	
	
	List<BusinessRoleRelativeVo> getBusinessRoleRelativeList(@Param("roleid") String roleid);
	
	List<BusinessRoleRelativeVo>  getBusinessRoleRelativeGrantList(@Param("roleid") String roleid);
	/**
    *
    * @mbggenerated 2017-06-23
    */
   int deleteByRoleid(@Param("roleid") String roleid);
   
   int checkGrant(@Param("userid") String userid, @Param("roleid") String roleid);
   
    /**
     *
     * @mbggenerated 2017-06-23
     */
    int insert(BusinessRoleRelativeVo record);

    /**
     *
     * @mbggenerated 2017-06-23
     */
    int insertSelective(BusinessRoleRelativeVo record);
}