package com.ztel.app.service.wms.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.ConsignorVoMapper;
import com.ztel.app.service.wms.ConsignorService;
import com.ztel.app.vo.wms.ConsignorVo;

@Service
public class ConsignorServiceImpl implements ConsignorService {
	
	@Autowired
	private ConsignorVoMapper consignorVoMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public ConsignorServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
        //sortKeyMapping.put(key, value)
		//sortKeyMapping.put("deliveryseq", "deliveryseq");
	}

	/**
	 * 取货主列表信息
	 */
//	@Override
//	public List<ConsignorVo> getConsignorPageList(Pagination<?> page) {
//		// TODO Auto-generated method stub
//		//page.sortKeyToColumn(sortKeyMapping);
//		List<ConsignorVo> consignorList = consignorVoMapper.getConsignorPageList(page);
//		return consignorList;
//	}
	

	@Override
	public List<ConsignorVo> getConsignorList(ConsignorVo consignorVo) {
		List<ConsignorVo> custList = null;
		custList = consignorVoMapper.getConsignorList(consignorVo);
		return custList;
	}

	@Override
	public List<HashMap<String, String>> getConsignsorComboboxByCond(ConsignorVo consignorVo) {
		// TODO Auto-generated method stub
		List<ConsignorVo> treeList=getConsignorList(consignorVo);
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 if (treeList!=null&&treeList.size()>0) {
			 for(int i=0;i<treeList.size();i++){
				 ConsignorVo vo=treeList.get(i);
				 HashMap<String, String> map=new HashMap<String, String>();
	   			 map.put("code", vo.getCode()+"");
	   			 map.put("company", vo.getComapany()+"");
				 //System.out.println(vo.getCode().toString()+"-------------");
				 //System.out.println(vo.getText()+"-------------");
				 boxList.add(map);
			 }
		}
		return boxList;
	}

	//@Override
	//public void viewConsignorVo(ConsignorVo consignorvo) {
		// TODO 自动生成的方法存根
		
	
}
