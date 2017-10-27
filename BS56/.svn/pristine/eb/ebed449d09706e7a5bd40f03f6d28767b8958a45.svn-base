/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sys;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.sys.RoleInfoVo;
import com.ztel.framework.vo.Pagination;

/**
 * @author lcf
 * @since 2017年2月27日
 */
public interface RoleinfoService {
	public List<RoleInfoVo> searchRoleinfoList(Pagination<?> page);
	public void deleteRoleById(List<Integer> ids);
	public void newRole(RoleInfoVo roleinfo);
	public void updateRole(RoleInfoVo roleinfo);
	
	//角色菜单权限设置
	public void doRoleSetting(String idList,String roleid);
	
	/**
	 * 角色授权给用户
	 * @param userstr 用户串 格式为姓名[工号];姓名[工号];姓名[工号]...  如：谢静[0502];殷兴强[0061];...
	 * @param roleid
	 */
		public void doRoleGrant(String userstr,String roleid);
		
		public List<RoleInfoVo> getRoleinfoList();
		/**
		 * 取角色下拉列表
		 */
		public List<HashMap<String, String>> getRoleinfoCombobox();
		
		/**
		 * 角色功能点授权提交
		 * @param opid 功能点id串
		 * @param roleid 角色id
		 * @param belongsys 所属系统
		 */
		public void doRoleOperation(List<String> opid,String roleid ,String belongsys);

}
