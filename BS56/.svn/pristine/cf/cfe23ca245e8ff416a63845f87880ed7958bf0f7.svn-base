package com.ztel.app.service.gis.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.gis.GroupinfoVoMapper;
import com.ztel.app.service.gis.GroupinfoVoService;
import com.ztel.app.vo.gis.GroupinfoVo;
import com.ztel.framework.vo.Pagination;

@Service
public class GroupinfoVoServiceImpl implements GroupinfoVoService{

@Autowired
    private GroupinfoVoMapper GroupinfoVoMapper =null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public GroupinfoVoServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
        //sortKeyMapping.put(key, value)
		sortKeyMapping.put("id", "id");
		sortKeyMapping.put("gropucode", "gropucode");
		sortKeyMapping.put("groupname", "groupname");
		sortKeyMapping.put("member", "member");
		sortKeyMapping.put("membername", "membername");
		sortKeyMapping.put("delstatus", "delstatus");
	}
	


	@Override
	public int delGroupinfoVo(List<String> groupcodes) {
		// TODO Auto-generated method stub
		if(groupcodes != null && groupcodes.size() > 0) {
			for (String groupcode : groupcodes) {
				this.GroupinfoVoMapper.deleteByPrimaryKey(groupcode);
			}
			return groupcodes.size();
		}
		return 0;
	}

	@Override
	public int insertGroupinfoVo(GroupinfoVo GroupinfoVo) {
		// TODO Auto-generated method stub
		if (GroupinfoVo!=null&&!"".equals(GroupinfoVo.getId())) {
			return GroupinfoVoMapper.insertSelective(GroupinfoVo);
		}
		return 0;
	}

	@Override
	public int updateGroupinfoVo(GroupinfoVo GroupinfoVo) {
		// TODO Auto-generated method stub
		if (GroupinfoVo!=null&&!"".equals(GroupinfoVo.getId())) {
			return GroupinfoVoMapper.updateByPrimaryKeySelective(GroupinfoVo);
		}
		return 0;
	}

	@Override
	public List<GroupinfoVo> getGroupinfoVoList(GroupinfoVo vo) {
		// TODO 自动生成的方法存根
	//	System.out.println("2vehicleno=="+vo.getGroupinfono()+"----------vehicles="+vo.getGroupinfos());
		return this.GroupinfoVoMapper.getGroupinfoVoList(vo);
	}

	@Override
	public List<GroupinfoVo> selectAllGroupinfoList(GroupinfoVo groupinfoVo){
		return this.GroupinfoVoMapper.selectAllGroupinfoList(groupinfoVo);
	}


	@Override
	public List<HashMap<String, String>> getGroupinfoCombobox() {
		// TODO 自动生成的方法存根
		GroupinfoVo vo = new GroupinfoVo();
		List<GroupinfoVo> treeList=this.GroupinfoVoMapper.getGroupinfoVoList(vo);
	   	 List<HashMap<String, String>> boxList=new ArrayList<>();
	   	 if (treeList!=null&&treeList.size()>0) {
	   		 for(int i=0;i<treeList.size();i++){
	   			GroupinfoVo groupinfoVo=treeList.get(i);
	   			 HashMap<String, String> map=new HashMap<String, String>();
	   			 map.put("id", groupinfoVo.getId()+"");
	   			 map.put("code", groupinfoVo.getGroupcode());
	   			 map.put("name", groupinfoVo.getGroupname());
	   			 boxList.add(map);
	   		 }
	   	 }
	   	 return boxList;
	}

	}
	
