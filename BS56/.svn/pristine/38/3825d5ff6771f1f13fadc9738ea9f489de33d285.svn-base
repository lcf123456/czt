package com.ztel.app.service.wms.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.ItemVoMapper;
import com.ztel.app.service.wms.ItemVoService;
import com.ztel.app.vo.wms.ItemVo;

@Service
public class ItemVoServiceImpl implements ItemVoService {
	
	@Autowired
	private ItemVoMapper itemVoMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public ItemVoServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
        //sortKeyMapping.put(key, value)
		//sortKeyMapping.put("deliveryseq", "deliveryseq");
	}

	@Override
	public List<ItemVo> selectConfiscationListByCond(ItemVo itemVo) {
		// TODO Auto-generated method stub
		return itemVoMapper.selectConfiscationListByCond(itemVo);
	}

	@Override
	public List<HashMap<String, String>> getItemComboboxByCond(ItemVo itemVo) {
		// TODO Auto-generated method stub
		List<ItemVo> treeList=selectConfiscationListByCond(itemVo);
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 if (treeList!=null&&treeList.size()>0) {
			 for(int i=0;i<treeList.size();i++){
				 ItemVo vo=treeList.get(i);
				 HashMap<String, String> map=new HashMap<String, String>();
	   			 map.put("itemno", vo.getItemno()+"");
	   			 map.put("itemname", vo.getItemname());
	   			 map.put("barcode", vo.getBigboxBar()+"");
	   			 map.put("jtsize", vo.getJtSize()+"");
	   			 map.put("itemval", vo.getItemno()+"-"+vo.getBigboxBar());
				 //System.out.println(vo.getId().toString()+"-------------");
				 //System.out.println(vo.getText()+"-------------");
				 boxList.add(map);
			 }
		}
		return boxList;
	}
	
}
