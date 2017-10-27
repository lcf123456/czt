package com.ztel.app.service.cost.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.cost.SupplierInfoVoMapper;
import com.ztel.app.service.cost.SupplierInfoVoService;
import com.ztel.app.vo.cost.SupplierInfoVo;
import com.ztel.framework.vo.Pagination;

@Service
public class SupplierInfoVoServiceImpl implements SupplierInfoVoService{

@Autowired
    private SupplierInfoVoMapper supplierInfoVoMapper =null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public SupplierInfoVoServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
        //sortKeyMapping.put(key, value)
		sortKeyMapping.put("id", "id");
		sortKeyMapping.put("supplier", "supplier");
		sortKeyMapping.put("ctype", "ctype");
		sortKeyMapping.put("delstatus", "delstatus");
	}
	

	/**
	 * 供应商下拉列表
	 */
	@Override
	public List<HashMap<String, String>> getSuppliersCombobox(SupplierInfoVo supplierInfoVo) {
		 // TODO Auto-generated method stub
		 List<SupplierInfoVo> treeList=getSuppliersList(supplierInfoVo);
	   	 List<HashMap<String, String>> boxList=new ArrayList<>();
	   	 if (treeList!=null&&treeList.size()>0) {
	   		 for(int i=0;i<treeList.size();i++){
	   			SupplierInfoVo vo=treeList.get(i);
	   			 HashMap<String, String> map=new HashMap<String, String>();
	   			 map.put("id", vo.getId()+"");
	   			 map.put("supplier", vo.getSupplier());
	   			 boxList.add(map);
	   		 }
	   	 }
	   	 return boxList;
	}

	@Override
	public int delSupplierInfoVo(List<Integer> ids) {
		// TODO Auto-generated method stub
		if(ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				this.supplierInfoVoMapper.deleteByPrimaryKey(id);
			}
			return ids.size();
		}
		return 0;
	}

	@Override
	public int insertSupplierInfoVo(SupplierInfoVo supplierInfoVo) {
		// TODO Auto-generated method stub
		if (supplierInfoVo!=null&&!"".equals(supplierInfoVo.getId())) {
			return supplierInfoVoMapper.insertSelective(supplierInfoVo);
		}
		return 0;
	}

	@Override
	public int updateSupplierInfoVo(SupplierInfoVo supplierInfoVo) {
		// TODO Auto-generated method stub
		if (supplierInfoVo!=null&&!"".equals(supplierInfoVo.getId())) {
			return supplierInfoVoMapper.updateByPrimaryKeySelective(supplierInfoVo);
		}
		return 0;
	}

	@Override
	public List<SupplierInfoVo> getSuppliersList(SupplierInfoVo supplierInfoVo) {
		// TODO 自动生成的方法存根
		return this.supplierInfoVoMapper.getSuppliersList(supplierInfoVo);
	}

	@Override
	public List<SupplierInfoVo> getSupplierInfoVoPageList(Pagination<?> page) {
		// TODO 自动生成的方法存根
		List<SupplierInfoVo> supplierinfoList = supplierInfoVoMapper.getSupplierInfoVoPageList(page);
		return supplierinfoList;
	}


	@Override
	public int viewSupplierInfoVo(SupplierInfoVo supplierinfoVo) {
		// TODO 自动生成的方法存根

		return 0;
	}



	}
	
