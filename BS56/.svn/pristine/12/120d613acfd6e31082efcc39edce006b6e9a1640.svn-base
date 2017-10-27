package com.ztel.app.persist.mybatis.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sys.RouteInfoVo;
import com.ztel.framework.vo.Pagination;

public interface RouteInfoVoMapper {
	
	public List<RouteInfoVo> getRoutesPageList(Pagination<?> page);
	
	public List<RouteInfoVo> getRoutesList(@Param("deptid")Integer deptid);
	
	public RouteInfoVo selectByRoutecode(@Param("routecode")String routecode);
	
	public List<RouteInfoVo> selectRoutesByCond(@Param("keywd")String keywd);
	
	/**
	 * 取车组所属的几个部门，用于下拉框选择部门后显示所有车组 ，暂用于预付款车组查看的下拉框
	 * @return
	 */
	public List<RouteInfoVo> getRoutesDeptList();
	
	/**
	 * 取车组核算人员列表，用于下拉框选择核算员后显示所有车组 ，暂用于预付款车组查看的下拉框
	 * @return
	 */
	public List<RouteInfoVo> getRoutesCalPersonList();
    /**
     *
     * @mbggenerated 2017-04-12
     */
    int deleteByPrimaryKey(Integer routeid);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insert(RouteInfoVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insertSelective(RouteInfoVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    RouteInfoVo selectByPrimaryKey(Integer routeid);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKeySelective(RouteInfoVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKey(RouteInfoVo record);
    
}