/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sq.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sq.CigfactoryVoMapper;
import com.ztel.app.persist.mybatis.sq.IndustrialdriverVoMapper;
import com.ztel.app.service.sq.CigfactoryService;
import com.ztel.app.vo.sq.CigfactoryVo;
import com.ztel.framework.vo.Pagination;

@Service
public  class CigfactoryServiceImpl implements CigfactoryService {
	
	@Autowired
	private CigfactoryVoMapper cigfactoryMapper = null;
	
	@Autowired
	private IndustrialdriverVoMapper industrialdriverVoMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public CigfactoryServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("name", "name");
		sortKeyMapping.put("id", "id");
		
	}
	
	public List<CigfactoryVo> searchCigfactoryList(Pagination<?> page) {
		
		page.sortKeyToColumn(sortKeyMapping);
		System.out.println("factoryName =  "+((CigfactoryVo)page.getParam()).getName());
		return this.cigfactoryMapper.getCigfactoryPageList(page);
	}
	@Override
	public List<HashMap<String, String>> getCigfactoryCombobox() {
		// TODO 自动生成的方法存根
		List<CigfactoryVo> treeList=this.industrialdriverVoMapper.getIndustrialdriverList();
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 if (treeList!=null&&treeList.size()>0) {
			 for(int i=0;i<treeList.size();i++){
				 CigfactoryVo vo=treeList.get(i);
				 HashMap<String, String> map=new HashMap<String, String>();
				 map.put("id", vo.getId().toString());
				 map.put("name", vo.getName());
				 //System.out.println(vo.getId().toString()+"-------------");
				 //System.out.println(vo.getText()+"-------------");
				 boxList.add(map);
			 }
		}
		 
		 return boxList;
		//return this.industrialdriverVoMapper.getIndustrialdriverList();
	}
	/**
	 * 根据id删除角色
	 */
	public void deleteCigfactoryById(List<Integer> ids)
	{
		if(ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				this.cigfactoryMapper.deleteByPrimaryKey(id);
			}
		}
	}
	
	/**
	 * 新增角色
	 */
	public void newCigfactory(CigfactoryVo cigfactory)
	{
		if(cigfactory != null && !cigfactory.getName().equals("") ) {
				this.cigfactoryMapper.insertSelective(cigfactory);
		}
	}
	/**
	 * 修改角色
	 */
	public void updateCigfactory(CigfactoryVo cigfactory)
	{
		if(cigfactory != null && !cigfactory.getName().equals("") ) {
				this.cigfactoryMapper.updateByPrimaryKeySelective(cigfactory);
		}
	}

	public static List<HashMap<String, String>> getFactorynameCombobox() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<CigfactoryVo> getCigfactoryinfoList(CigfactoryVo vo, String keywd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertCigfactoryVo() {
		// TODO Auto-generated method stub
		return 0;
	}


}
