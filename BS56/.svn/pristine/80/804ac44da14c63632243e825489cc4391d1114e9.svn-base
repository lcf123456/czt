package com.ztel.app.service.sys;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.sys.RouteInfoVo;
import com.ztel.framework.vo.Pagination;


/**
 * 
 * @author NJ
 *
 */
public interface RouteInfoService {
	
	
	/**
	 * 取车组列表
	 * @param page
	 * @return
	 */
	public List<RouteInfoVo> getRouteInfoPageList(Pagination<?> page);
	
	
	/**
	 * 新增车组
	 * @param routeInfoVo
	 */
	public void insertNewRoute(RouteInfoVo routeInfoVo);
	
	/**
	 * 修改车组
	 * @param routeInfoVo
	 */
	public void updateRoute(RouteInfoVo routeInfoVo);
	
	/**
	 * 根据部门id查找车组列表
	 * @param roleid
	 * @return
	 */
	public List<RouteInfoVo> getRoutesListByDeptid(Integer deptid);
	
	/**
	 * 根据车组CODE查找车组人员信息
	 * @param roleid
	 * @return
	 */
	public RouteInfoVo getDriverAndCashierByRotecode(String routecode);
	
	/**
	 * 取车组所属的几个部门，用于下拉框选择部门后显示所有车组 ，暂用于预付款车组查看的下拉框
	 * @return
	 */
	public  List<HashMap<String, String>>  getRoutesDeptListCombobox();
	
	/**
	 * 取车组核算人员列表，用于下拉框选择核算员后显示所有车组 ，暂用于预付款车组查看的下拉框
	 * @return
	 */
	public  List<HashMap<String, String>>  getRoutesCalPersonListCombobox();
}
