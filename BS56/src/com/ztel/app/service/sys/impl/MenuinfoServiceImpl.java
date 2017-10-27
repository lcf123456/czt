/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sys.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sys.MenuinfoVoMapper;
import com.ztel.app.persist.mybatis.sys.OperationinfoVoMapper;
import com.ztel.app.persist.mybatis.sys.OperationrolerelativeVoMapper;
import com.ztel.app.service.sys.MenuinfoService;
import com.ztel.app.vo.sys.MenuInfoVo;
import com.ztel.app.vo.sys.OperationinfoVo;
import com.ztel.app.vo.sys.OperationrolerelativeVo;
import com.ztel.framework.util.FileUtil;

/**
 * @author Administrator
 * @since 2017年2月27日
 */
@Service
public class MenuinfoServiceImpl implements MenuinfoService {
	
	@Autowired
	private MenuinfoVoMapper menuinfoMapper = null;
	
	@Autowired
	private OperationinfoVoMapper operationinfoVoMapper = null;
	
	@Autowired
	private OperationrolerelativeVoMapper operationrolerelativeVoMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public MenuinfoServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
//		sortKeyMapping.put("rolename", "rolename");
//		sortKeyMapping.put("id", "id");
//		sortKeyMapping.put("updatetime", "id");
	}
	
	/**
	 *  菜单树，功能按钮实用
	 */
	public List<MenuInfoVo> getMenuinfoListforTree(String sysid,String id)
	{
		List<MenuInfoVo> deptList  =new ArrayList();  
		//先根据code判断用户是否存在
		deptList = menuinfoMapper.getMenuinfoListforTree(sysid,id);
		if(deptList!=null&&deptList.size()>0)
		{
			for(int i=0;i<deptList.size();i++)
			{
				MenuInfoVo deptVoOne = deptList.get(i);
				BigDecimal deptId = deptVoOne.getId();
				
				List<MenuInfoVo> deptChildList = menuinfoMapper.getMenuinfoListforTree(sysid,deptId+"");
				if(deptChildList!=null&&deptChildList.size()>0)
				{
					deptVoOne.setState("closed");
				}
				else{
					deptVoOne.setState("open");
				}
			}	
		}
			 return deptList;
		}
		 
	/**
	 * 根据所属系统获取栏目信息，分别考虑一级二级三级栏目
	 */
	public List<MenuInfoVo> searchMenuinfoList(String belongsysId,String fid) {
		List<MenuInfoVo> resultList = new ArrayList<MenuInfoVo>();
		
		BigDecimal parentId=new BigDecimal(Integer.parseInt(fid));
		System.out.println("fid="+fid+",parentId="+parentId);
		  List<MenuInfoVo> oneMenuList = this.menuinfoMapper.getMenuinfoList(belongsysId,fid,"","");
		  //判断一级栏目是否有值
		  if (oneMenuList!=null&&oneMenuList.size()>0) {
			  for (int i = 0; i < oneMenuList.size(); i++) {
				  MenuInfoVo oneMenuinfo =oneMenuList.get(i);
				  oneMenuinfo.setState("closed");
				  BigDecimal oneparentId = oneMenuinfo.getId();
				  //根据一级id获取二级子栏目信息
				  List<MenuInfoVo> TwoMenuList = this.menuinfoMapper.getMenuinfoList(belongsysId,oneparentId+"","","");
				  if(TwoMenuList!=null&&TwoMenuList.size()>0){
					  for(int j=0;j<TwoMenuList.size();j++){
						  MenuInfoVo TwoMenuInfoVo = TwoMenuList.get(j);
							  TwoMenuInfoVo.setState("closed");
					  }
				  }
				  else
				  {
					  oneMenuinfo.setState("open");
				  }
			}
			  resultList=oneMenuList;
		}
		
		return oneMenuList;
	}
	
	/**
	 * 根据所属系统获取栏目信息，分别考虑一级二级三级栏目
	 */
	public List<MenuInfoVo> searchMenuinfoList(String belongsysId,String fid,String roleid) {

		//获取角色已经授权的菜单
		List<BigDecimal> menuList_sq = this.menuinfoMapper.getMenuinfoListByRole(roleid); 
		
		List<MenuInfoVo> resultList = new ArrayList<MenuInfoVo>();
		BigDecimal parentId=new BigDecimal(Integer.parseInt(fid));
		System.out.println("fid="+fid+",parentId="+parentId);
		  List<MenuInfoVo> oneMenuList = this.menuinfoMapper.getMenuinfoList(belongsysId,fid,"","");
		  
		  //判断一级栏目是否有值
		  if (oneMenuList!=null&&oneMenuList.size()>0) {
			  for (int i = 0; i < oneMenuList.size(); i++) {
				  MenuInfoVo oneMenuinfo =oneMenuList.get(i);
				  oneMenuinfo.setState("closed");
				  BigDecimal oneparentId = oneMenuinfo.getId();
				//该级次下是否还有子级，没有则展开状态，否则显示的是文件夹图标
				  List<MenuInfoVo> TwoMenuList = this.menuinfoMapper.getMenuinfoList(belongsysId,oneparentId+"","","");
				  
				  //如果已经授权则勾选菜单
				  //1.如果为3级，直接勾选
				  //2、如果为2级且没有子级，也直接勾选 。如果为2级但有子集，需要判断子级是否都已经选上
				  //3。为1级时，需要判断其全部子级及孙子级是否选上
				  if(oneMenuinfo.getMenulevel().trim().equals("3")){
					  if(checkExist(oneparentId,menuList_sq)){
						  oneMenuinfo.setChecked("true");
						  }
				  }
				  if(oneMenuinfo.getMenulevel().trim().equals("2")){
					  //判断是否有子级
					  if(TwoMenuList!=null&&TwoMenuList.size()>0){
						  
					  }else{//没有子级，直接判断是否勾选
						  if(checkExist(oneparentId,menuList_sq)){
							  oneMenuinfo.setChecked("true");
							  }
					  }
					  
				  }
				  
				  
				  if(TwoMenuList!=null&&TwoMenuList.size()>0){
					  for(int j=0;j<TwoMenuList.size();j++){
						  MenuInfoVo TwoMenuInfoVo = TwoMenuList.get(j);
						  BigDecimal twoparentId = TwoMenuInfoVo.getId();
						  //if(!checkExist(twoparentId,menuList_sq))TwoMenuInfoVo.setChecked("false");
							  TwoMenuInfoVo.setState("closed");
					  }
				  }
				  else
				  {
					  oneMenuinfo.setState("open");
				  }
			}
			  resultList=oneMenuList;
		}
		
		return oneMenuList;
	}
	
	
	/**
	 * 根据所属系统获取栏目信息，分别考虑一级二级三级栏目
	 */
	/*
	public List<MenuInfoVo> searchMenuinfoList(String belongsysId) {
		List<MenuInfoVo> resultList = new ArrayList<MenuInfoVo>();
		  List<MenuInfoVo> oneMenuList = this.menuinfoMapper.getMenuinfoList(belongsysId,"0","1");
		  //判断一级栏目是否有值
		  if (oneMenuList!=null&&oneMenuList.size()>0) {
			  for (int i = 0; i < oneMenuList.size(); i++) {
				  MenuInfoVo oneMenuinfo =oneMenuList.get(i);
				  oneMenuinfo.setChecked("true");
				  oneMenuinfo.setState("closed");
				  BigDecimal oneparentId = oneMenuinfo.getId();
				  //根据一级id获取二级子栏目信息
				  List<MenuInfoVo> TwoMenuList = this.menuinfoMapper.getMenuinfoList(belongsysId,oneparentId+"","2");
				  //判断二级子栏目是否有值，有则取出用于判断是否有三级栏目
				  if (TwoMenuList!=null&&TwoMenuList.size()>0) {
					  oneMenuinfo.setChildren(TwoMenuList);
					  for (int j = 0; j < TwoMenuList.size(); j++) {
						  MenuInfoVo twoMenuinfo =TwoMenuList.get(j);
						  twoMenuinfo.setChecked("true");
						  BigDecimal twoparentId = twoMenuinfo.getId();
						//根据二级id获取三级子栏目信息
						List<MenuInfoVo> threeMenuList = this.menuinfoMapper.getMenuinfoList(belongsysId,twoparentId+"","3");
						if (threeMenuList!=null&&threeMenuList.size()>0) {
							twoMenuinfo.setChildren(threeMenuList);
						}
					}
				}
			}
			  resultList=oneMenuList;
		}
		
		return oneMenuList;
	}
	*/
	
	
	
	/**
	 * 根据所属系统获取栏目信息，分别考虑一级二级三级栏目,用户用户登陆后主界面的左侧菜单显示
	 */
	public List<MenuInfoVo> searchMenuinfoListforMain(String belongsysId,String roleid) {
		List<MenuInfoVo> resultList = new ArrayList<MenuInfoVo>();
		
		 List<MenuInfoVo> oneMenuList = this.menuinfoMapper.getMenuinfoListForMain(belongsysId,"0","1",roleid);
		  //判断一级栏目是否有值
		  if (oneMenuList!=null&&oneMenuList.size()>0) {
			  for (int i = 0; i < oneMenuList.size(); i++) {
				  MenuInfoVo oneMenuinfo =oneMenuList.get(i);
				  BigDecimal oneparentId = oneMenuinfo.getId();
				  
				  String icon = oneMenuinfo.getImgurl();
				  if(icon!=null&&icon.trim().length()>0){
					  icon = icon;
				  }
				  else{
					  icon="icon-sys";
				  }
				  oneMenuinfo.setIcon(icon);//设置二级图标
				  
				  //根据一级id获取二级子栏目信息
				  List<MenuInfoVo> TwoMenuList = this.menuinfoMapper.getMenuinfoListForMain(belongsysId,oneparentId+"","2",roleid);
				  //判断二级子栏目是否有值，有则取出用于判断是否有三级栏目
				  if (TwoMenuList!=null&&TwoMenuList.size()>0) {
					  oneMenuinfo.setMenus(TwoMenuList);//一级的子返回字串要求是menus
					  for (int j = 0; j < TwoMenuList.size(); j++) {
						  MenuInfoVo twoMenuinfo =TwoMenuList.get(j);
						  BigDecimal twoparentId = twoMenuinfo.getId();
						  twoMenuinfo.setIcon(twoMenuinfo.getImgurl());
						
						//根据二级id获取三级子栏目信息
						List<MenuInfoVo> threeMenuList = this.menuinfoMapper.getMenuinfoListForMain(belongsysId,twoparentId+"","3",roleid);
						
						if (threeMenuList!=null&&threeMenuList.size()>0) {
							twoMenuinfo.setChildren(threeMenuList);//二级的子返回字串要求是children
								//设置三级图标
								if(threeMenuList!=null && threeMenuList.size()>0)
								{
									for(int k=0;k<threeMenuList.size();k++)
									{
										MenuInfoVo threeMenuinfo =threeMenuList.get(k);
										threeMenuinfo.setIcon(threeMenuinfo.getImgurl());
									}
								}
						}else{//菜单只有二级，没有三级
							String icon2 = twoMenuinfo.getImgurl();
							  if(icon2!=null&&icon2.trim().length()>0){
								  icon2 = icon2;
							  }
							  else{
								  icon2="icon-nav";
							  }
							  twoMenuinfo.setIcon(icon2);//设置二级图标
						}
					}
				}
			}
			  resultList=oneMenuList;
		}
		
		return resultList;
	}
	
	 
/**
* 根据所属系统获取栏目信息，分别考虑一级二级三级栏目,用于角色功能点授权
*/
public List<MenuInfoVo> searchMenuinfoListForOperation(String belongsysId,String roleid) {
	List<MenuInfoVo> resultList = new ArrayList<MenuInfoVo>();

	List<OperationinfoVo>  opList = this.operationinfoVoMapper.getOperationBySysid(belongsysId);
	
	
	if(opList!=null&&opList.size()>0){
		String menucodeTmp = "";
		List<OperationinfoVo>  newopList = new ArrayList<OperationinfoVo>();
		List<MenuInfoVo>  mList = new ArrayList<MenuInfoVo>();
		MenuInfoVo menuInfoVo = new MenuInfoVo();
		int counter = 0;
		
		Map<String,List<OperationinfoVo>> map = this.formatterOperationList(belongsysId,roleid,opList);
		
		Iterator<Entry<String, List<OperationinfoVo>>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String mcode = (String)entry.getKey();
			List<OperationinfoVo> val =(List<OperationinfoVo>) entry.getValue();
			 mList = this.menuinfoMapper.getMenuinfoList(belongsysId, "", "", mcode);
			if(mList!=null&&mList.size()>0){
				menuInfoVo = mList.get(0);
				BigDecimal fid = menuInfoVo.getFid();
				MenuInfoVo fmenuInfoVo = this.menuinfoMapper.selectByPrimaryKey(fid);
				String fmenuname = fmenuInfoVo.getMenuname();
				menuInfoVo.setFmenuname(fmenuname);
				menuInfoVo.setOperationinfoVoList(val);
				resultList.add(menuInfoVo);
			}
		}
	}
	return resultList;
}

public Map<String,List<OperationinfoVo>> formatterOperationList(String belongsys,String roleid,List<OperationinfoVo> opList){
	Map<String,List<OperationinfoVo>> map = new HashMap<String,List<OperationinfoVo>>();
	List<MenuInfoVo> menuInfoVoList = new ArrayList<MenuInfoVo>();
	String menucodeTmp = ""; 
	
	for(int i=0;i<opList.size();i++){
		OperationinfoVo operationinfoVo = opList.get(i);
		String menucode = operationinfoVo.getMenucode();
		OperationrolerelativeVo operationrolerelativeVo = operationrolerelativeVoMapper.selectByOpidRoleid(roleid, operationinfoVo.getId()+"");
		  if(operationrolerelativeVo!=null){
			  operationinfoVo.setChecked("checked");
			  }else
			  {
				  operationinfoVo.setChecked("");
			  }
		
	}
	map = FileUtil.GetGroupList( opList, "menucode");
	return map;
}

//if(menucodeTmp.equals("")){
//	List<OperationinfoVo> tempList=new ArrayList<OperationinfoVo>();
//	tempList.add(opList.get(i));
//	MenuInfoVo menuInfoVo = getMenuInfVo(belongsys,menucode);
//	menuInfoVo.setOperationinfoVoList(tempList);
//	map.put(menucode, tempList);
//	
//}
//else if(menucodeTmp.equals(menucode)){
//	List<OperationinfoVo> tempList=map.get(menucode);
//	tempList.add(opList.get(i));
//}
//else
//{
//	List<OperationinfoVo> tempList=new ArrayList<OperationinfoVo>();
//	tempList.add(opList.get(i));
//	map.put(menucode, tempList);
//}

private MenuInfoVo getMenuInfVo(String belongsysId,String menucode)
{
	MenuInfoVo menuInfoVo = new MenuInfoVo();
	List<MenuInfoVo> mList = this.menuinfoMapper.getMenuinfoList(belongsysId, "", "", menucode);
	if(mList!=null&&mList.size()>0){
		menuInfoVo = mList.get(0);
		BigDecimal fid = menuInfoVo.getFid();
		MenuInfoVo fmenuInfoVo = this.menuinfoMapper.selectByPrimaryKey(fid);
		String fmenuname = fmenuInfoVo.getMenuname();
		menuInfoVo.setFmenuname(fmenuname);
	}
	return menuInfoVo;
}
	
public MenuInfoVo formatterMenuInfoVo(MenuInfoVo oneMenuinfo,MenuInfoVo TwoMenuInfoVo,String menucode,String roleid){
	MenuInfoVo menuInfoVo = new MenuInfoVo();
	List<OperationinfoVo> resultList  = new ArrayList();
	List<OperationinfoVo> operationinfoVoList = operationinfoVoMapper.getOperationinfoList(menucode);
	  if(operationinfoVoList!=null&&operationinfoVoList.size()>0){
		  //检查功能点是否已经授权，如果已经授权，则设置checked属性，以供前台显示用：如果已经授权了，则显示勾选上
		  for(int i=0;i<operationinfoVoList.size();i++){
			  OperationinfoVo  operationinfoVo=operationinfoVoList.get(i);
			  BigDecimal opid = operationinfoVo.getId();
			  OperationrolerelativeVo operationrolerelativeVo = operationrolerelativeVoMapper.selectByOpidRoleid(roleid, opid+"");
			  if(operationrolerelativeVo!=null){
				  operationinfoVo.setChecked("checked");
				  }else
				  {
					  operationinfoVo.setChecked("");
				  }
			  resultList.add(operationinfoVo);
		  }
		  TwoMenuInfoVo.setOperationinfoVoList(resultList);
		  TwoMenuInfoVo.setFmenuname(oneMenuinfo.getMenuname());
		  menuInfoVo = TwoMenuInfoVo;
	  }
	  
	return menuInfoVo;
}

	/*
	 * 判断是否已经授权，已授权true ，否则false
	 */
	private boolean checkExist(BigDecimal mid ,List<BigDecimal> menuList)
	{
		boolean exist = false;
		if(menuList!=null && menuList.size()>0){
			for(int i=0;i<menuList.size();i++){
				BigDecimal menuInfoVo = menuList.get(i);
				if(menuInfoVo!=null){
					if(menuInfoVo.intValue() ==mid.intValue() ){
						exist=true;
					}
				}
				
			}
		}
		return exist;
	}
	
	/**
	 * 新增菜单
	 */
	public void doAddMenu(MenuInfoVo menuInfoVo){
		if(menuInfoVo.getMenulevel()!=null){
			//菜单级次需要设置，传过来的参数是父菜单的级次，需要在父菜单的基础上加1
			int menulevel = Integer.parseInt(menuInfoVo.getMenulevel())+1;
			menuInfoVo.setMenulevel(menulevel+"");
		}
		else
		{
			BigDecimal fid = new BigDecimal("0");
			menuInfoVo.setFid(fid);
			menuInfoVo.setMenulevel("1");
		}
		menuinfoMapper.insertSelective(menuInfoVo);
	}
	
	public void doEditMenu(MenuInfoVo meuInfoVo){
		menuinfoMapper.updateByPrimaryKeySelective(meuInfoVo);
	}

	/**
	 * 删除菜单，不是真正物理删除，只做删除标志
	 */
	public void doDelMenu(String id){
		BigDecimal menuid = new BigDecimal(id);
		menuinfoMapper.updateStatusByPrimaryKey(menuid);
	}
	
	/**
	 * 根据id取菜单信息
	 */
	public MenuInfoVo getMenuinfoVoByid(String id){
		MenuInfoVo menuInfoVo = null;
		menuInfoVo = menuinfoMapper.selectByPrimaryKey(id);
		return menuInfoVo;
	}
}
