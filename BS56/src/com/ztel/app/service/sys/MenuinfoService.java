package com.ztel.app.service.sys;

import java.util.List;

import com.ztel.app.vo.sys.MenuInfoVo;


/**
 * 
 * @author lcf
 *
 */
public interface MenuinfoService {
	/**
	 * 根据所属系统id获取栏目信息
	 * @param belongsysId
	 * @return
	 */
	public List<MenuInfoVo> searchMenuinfoList(String belongsysId,String parentId);
	
	/**
	 * 根据所属系统id获取栏目信息
	 * @param belongsysId
	 * @return
	 */
	public List<MenuInfoVo> searchMenuinfoList(String belongsysId,String parentId,String roleId);
	
	/**
	 * 菜单树，功能按钮实用
	 * @param sysid
	 * @param id
	 * @return
	 */
	public List<MenuInfoVo> getMenuinfoListforTree(String sysid,String id);
	/**
	 * 为各模块主界面查找菜单
	 * @param belongsysId
	 * @return
	 */
	public List<MenuInfoVo> searchMenuinfoListforMain(String belongsysId,String roleid);
	
	/**
	* 根据所属系统获取栏目信息，分别考虑一级二级三级栏目,用于角色功能点授权
	*/
	public List<MenuInfoVo> searchMenuinfoListForOperation(String belongsysId,String roleid);
		
	/**
	 * 新增菜单
	 * @param meuInfoVo
	 */
	public void doAddMenu(MenuInfoVo meuInfoVo);
	
	public void doEditMenu(MenuInfoVo meuInfoVo);
	
	public void doDelMenu(String id);
	
	public MenuInfoVo getMenuinfoVoByid(String id);
}
