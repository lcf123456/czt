package com.ztel.app.service.safe.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.safe.HazardsVoMapper;
import com.ztel.app.persist.mybatis.safe.HiddendangerVoMapper;
import com.ztel.app.persist.mybatis.safe.TypeinfoVoMapper;
import com.ztel.app.persist.mybatis.sq.IndustrialdriverVoMapper;
import com.ztel.app.service.safe.TypeinfoService;
import com.ztel.app.vo.safe.TypeinfoVo;
import com.ztel.framework.vo.Pagination;


@Service
public class TypeinfoServiceImpl implements TypeinfoService {
	@Autowired
	private TypeinfoVoMapper typeinfoVoMapper = null;
	@Autowired
	private HiddendangerVoMapper hiddendangerVoMapper = null;
	@Autowired
	private HazardsVoMapper hazardsVoMapper = null;
    private Map<String, String> sortKeyMapping = new HashMap<>();
	
    public TypeinfoServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("ctype", "ctype");
		sortKeyMapping.put("id", "id");
	}
	
  @Override
  	public List<HashMap<String, String>> getCtypeCombobox() {
  		// TODO 自动生成的方法存根
  		List<TypeinfoVo> treeList=this.hiddendangerVoMapper.getCtypeList();
  		 List<HashMap<String, String>> boxList=new ArrayList<>();
  		if (treeList!=null&&treeList.size()>0) {
  		 for(int i=0;i<treeList.size();i++){
  			TypeinfoVo vo=treeList.get(i);
  				 HashMap<String, String> map=new HashMap<String, String>();
  				map.put("id", vo.getId().toString());
  				map.put("ctype", vo.getCtype());
  				boxList.add(map);
  			}
  		}
  		 
  		return boxList;
  	}
	
  @Override
	public List<HashMap<String, String>> getHazardstypeCombobox() {
		// TODO 自动生成的方法存根
		List<TypeinfoVo> treeList=this.hazardsVoMapper.getHazardstypeList();
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		if (treeList!=null&&treeList.size()>0) {
		 for(int i=0;i<treeList.size();i++){
			TypeinfoVo vo=treeList.get(i);
				 HashMap<String, String> map=new HashMap<String, String>();
				map.put("id", vo.getId().toString());
				map.put("ctype", vo.getCtype());
				boxList.add(map);
			}
		}
		 
		return boxList;
	}


}
