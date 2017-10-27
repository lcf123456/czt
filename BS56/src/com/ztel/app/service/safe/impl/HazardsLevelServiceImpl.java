package com.ztel.app.service.safe.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.safe.HazardsLevelVoMapper;
import com.ztel.app.persist.mybatis.safe.HazardsVoMapper;
import com.ztel.app.service.safe.HazardsLevelService;
import com.ztel.app.vo.safe.HazardsInfoVo;
import com.ztel.app.vo.safe.HazardsLevelVo;
import com.ztel.framework.vo.Pagination;


@Service
public class HazardsLevelServiceImpl implements HazardsLevelService {
	@Autowired
	private HazardsLevelVoMapper hazardslevelVoMapper = null;
	
	@Autowired
	private HazardsVoMapper hazardsVoMapper = null;
    private Map<String, String> sortKeyMapping = new HashMap<>();
	
    public HazardsLevelServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("clevel", "clevel");
		sortKeyMapping.put("id", "id");
	}
	
  @Override
  	public List<HashMap<String, String>> getClevelCombobox() {
  		// TODO 自动生成的方法存根
  		List<HazardsLevelVo> treeList=this.hazardsVoMapper.getClevelList();
  		 List<HashMap<String, String>> boxList=new ArrayList<>();
  		if (treeList!=null&&treeList.size()>0) {
  		 for(int i=0;i<treeList.size();i++){
  			HazardsLevelVo vo=treeList.get(i);
  				 HashMap<String, String> map=new HashMap<String, String>();
  				map.put("id", vo.getId().toString());
  				map.put("clevel", vo.getClevel());
  				boxList.add(map);
  			}
  		}
  		 
  		return boxList;
  	}
	
 


}
