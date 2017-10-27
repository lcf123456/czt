package com.ztel.app.persist.mybatis.sys;

import com.ztel.app.vo.sys.OperationrolerelativeVo;
import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OperationrolerelativeVoMapper {
	
	int deleteByRoleid(@Param("roleid") String roleid,@Param("belongsys") String belongsys);
	 /**
    *
    * @mbggenerated 2017-05-12
    */
   OperationrolerelativeVo selectByOpidRoleid(@Param("roleid")String roleid,@Param("operationid")String operationid);
	
   /**
    * 根据角色id查找记录，用于角色登陆后取授权的功能点按钮，赋予到前台界面的全局变量
    * @param roleid
    * @return
    */
   List<OperationrolerelativeVo> selectListByRoleid(@Param("roleid")String roleid);
   /**
    * 功能点删除的同时，删除授权中的记录
    * @param operationid
    * @return
    */
   int deleteByOperationid(@Param("operationid")String operationid);
	/**
     *
     * @mbggenerated 2017-05-12
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-05-12
     */
    int insert(OperationrolerelativeVo record);

    /**
     *
     * @mbggenerated 2017-05-12
     */
    int insertSelective(OperationrolerelativeVo record);

    /**
     *
     * @mbggenerated 2017-05-12
     */
    OperationrolerelativeVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-05-12
     */
    int updateByPrimaryKeySelective(OperationrolerelativeVo record);

    /**
     *
     * @mbggenerated 2017-05-12
     */
    int updateByPrimaryKey(OperationrolerelativeVo record);
}