package com.ztel.app.service.wms.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.ItemVoMapper;
import com.ztel.app.service.wms.ItemService;
import com.ztel.app.vo.sys.DeptVo;
import com.ztel.app.vo.sys.SysparameterVo;
import com.ztel.app.vo.wms.ConsignorVo;
import com.ztel.app.vo.wms.ItemVo;
import com.ztel.framework.vo.Pagination;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemVoMapper itemVoMapper = null;
	
	@Override
	public List<ItemVo> selectListByCond(ItemVo itemVo) {
		// TODO Auto-generated method stub
		return itemVoMapper.selectListByCond(itemVo);
	}
	/**
	 * 获取品牌信息
	 */
	@Override
	public List<ItemVo> getBrandinfoList(Pagination<?> page) {
		// TODO Auto-generated method stub
		return itemVoMapper.selectBrandinfoPageList(page);
	}
	
	@Override
	public int updateBrandinfo(ItemVo itemVo) {
		// TODO Auto-generated method stub
		if (itemVo!=null&&!"".equals(itemVo.getId())) {
			return itemVoMapper.updateByPrimaryKeySelective(itemVo);
		}
		return 0;
	}
	
	public List<HashMap<String, String>> getItemComboboxByCond(ItemVo itemVo){
		List<ItemVo> treeList= this.itemVoMapper.selectListByCond(itemVo);
		List<HashMap<String, String>> boxList=new ArrayList<>();
		 if (treeList!=null&&treeList.size()>0) {
			 for(int i=0;i<treeList.size();i++){
				 ItemVo itemVo2=treeList.get(i);
				 HashMap<String, String> map=new HashMap<String, String>();
				 map.put("itemno", itemVo2.getItemno());
				 map.put("itemname", itemVo2.getItemname());
				 boxList.add(map);
			 }
		}
		 return boxList;
	}
}
