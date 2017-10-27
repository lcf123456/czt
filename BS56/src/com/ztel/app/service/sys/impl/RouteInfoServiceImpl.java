package com.ztel.app.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sys.RouteInfoVoMapper;
import com.ztel.app.persist.mybatis.sys.UserVoMapper;
import com.ztel.app.service.sys.RouteInfoService;
import com.ztel.app.service.sys.UserVoService;
import com.ztel.app.vo.gis.GroupinfoVo;
import com.ztel.app.vo.sys.RouteInfoVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.framework.vo.Pagination;

@Service
public class RouteInfoServiceImpl implements RouteInfoService{

	@Autowired
	private RouteInfoVoMapper routeInfoVoMapper = null;
	
    private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public RouteInfoServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
        //sortKeyMapping.put(key, value)
		sortKeyMapping.put("routecode", "routecode");
		sortKeyMapping.put("vehicleno", "vehicleno");
		sortKeyMapping.put("deptname", "deptname");
		sortKeyMapping.put("routetypename", "routetypename");
		sortKeyMapping.put("areatypename", "areatypename");
		sortKeyMapping.put("gisareaname", "gisareaname");
	}

	@Override
	public List<RouteInfoVo> getRouteInfoPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		List<RouteInfoVo> routeList = routeInfoVoMapper.getRoutesPageList(page);
		return routeList;
	}

	@Override
	public void insertNewRoute(RouteInfoVo routeInfoVo) {
		// TODO Auto-generated method stub
		routeInfoVoMapper.insertSelective(routeInfoVo);
	}

	@Override
	public void updateRoute(RouteInfoVo routeInfoVo) {
		// TODO Auto-generated method stub
		routeInfoVoMapper.updateByPrimaryKeySelective(routeInfoVo);
	}

	@Override
	public List<RouteInfoVo> getRoutesListByDeptid(Integer deptid) {
		// TODO Auto-generated method stub
		return routeInfoVoMapper.getRoutesList(deptid);
	}

	@Override
	public RouteInfoVo getDriverAndCashierByRotecode(String routecode) {
		// TODO Auto-generated method stub
		return routeInfoVoMapper.selectByRoutecode(routecode);
	}
	
	/**
	 * 取车组所属的几个部门，用于下拉框选择部门后显示所有车组 ，暂用于预付款车组查看的下拉框
	 * @return
	 */
	public  List<HashMap<String, String>>  getRoutesDeptListCombobox(){
		// TODO 自动生成的方法存根
				List<RouteInfoVo> treeList=this.routeInfoVoMapper.getRoutesDeptList();
			   	 List<HashMap<String, String>> boxList=new ArrayList<>();
			   	 if (treeList!=null&&treeList.size()>0) {
			   		 for(int i=0;i<treeList.size();i++){
			   			RouteInfoVo routeInfoVo=treeList.get(i);
			   			 HashMap<String, String> map=new HashMap<String, String>();
			   			 map.put("deptid", routeInfoVo.getDeptid()+"");
			   			 map.put("deptname", routeInfoVo.getDeptname());
			   			 boxList.add(map);
			   		 }
			   	 }
		     return boxList;
	}
	
	/**
	 * 取车组核算人员列表，用于下拉框选择核算员后显示所有车组 ，暂用于预付款车组查看的下拉框
	 * @return
	 */
	public  List<HashMap<String, String>>  getRoutesCalPersonListCombobox(){
		// TODO 自动生成的方法存根
		List<RouteInfoVo> treeList=this.routeInfoVoMapper.getRoutesCalPersonList();
	   	 List<HashMap<String, String>> boxList=new ArrayList<>();
	   	 if (treeList!=null&&treeList.size()>0) {
	   		 for(int i=0;i<treeList.size();i++){
	   			RouteInfoVo routeInfoVo=treeList.get(i);
	   			 HashMap<String, String> map=new HashMap<String, String>();
	   			 map.put("calcid", routeInfoVo.getCalcid()+"");
	   			 map.put("calcname", routeInfoVo.getCalcname());
	   			 boxList.add(map);
	   		 }
	   	 }
     return boxList;
	}

	@Override
	public List<RouteInfoVo> selectRoutesByCond(String keywd) {
		// TODO Auto-generated method stub
		return routeInfoVoMapper.selectRoutesByCond(keywd);
	}
	
}
