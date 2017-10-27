package com.ztel.app.service.perform.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.perform.UserperformlevelVoMapper;
import com.ztel.app.service.perform.UserperformlevelService;
import com.ztel.app.vo.perform.UserlevelVo;
import com.ztel.app.vo.perform.UserperformlevelVo;
import com.ztel.app.vo.perform.UserperformlevelVoTmp;
import com.ztel.framework.vo.Pagination;

@Service
public class UserperformlevelServiceImpl implements UserperformlevelService {
	
	@Autowired
	private UserperformlevelVoMapper userperformlevelVoMapper = null;
	

	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public UserperformlevelServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("assessdate", "assessdate");
		sortKeyMapping.put("id", "id");
	}
	
	@Override
	public List<UserperformlevelVo> selectUserperformlevelPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return userperformlevelVoMapper.selectUserperformlevelPageList(page);
	}

	@Override
	public List<UserperformlevelVo> selectUserperformlevelListByCond(UserperformlevelVo userperformlevelVo) {
		// TODO Auto-generated method stub
		return userperformlevelVoMapper.selectUserperformlevelListByCond(userperformlevelVo);
	}

	/**
	 * 新增时需要查询用户id，同时获取考核表中用户的考核情况
	 * @param userlevelList
	 * @param scoredate
	 * @return
	 */
	public List<UserperformlevelVoTmp> selectAddUserListByCond(List<UserlevelVo> userlevelList,String scoredate){
		List<UserperformlevelVoTmp> resultList = new ArrayList<UserperformlevelVoTmp>();
		UserperformlevelVo userperformlevelVo = new UserperformlevelVo();
		UserperformlevelVoTmp userperformlevelVoTmp = new UserperformlevelVoTmp();
		for(int i=0;i<userlevelList.size();i++){
			UserlevelVo userlevelVo = userlevelList.get(i);
			Long userid = userlevelVo.getUserid();
			 userperformlevelVo = new UserperformlevelVo();
			 userperformlevelVo.setUserid(userid);
			 userperformlevelVo.setCheckdatestr(scoredate);
			 
			 String levelname="",remarks="",score="";
			 List<UserperformlevelVo> userperformlevelVoList =userperformlevelVoMapper.selectUserperformlevelListByCond(userperformlevelVo);
			 if(userperformlevelVoList!=null&&userperformlevelVoList.size()>0){
				 UserperformlevelVo userperformlevelVo1 = userperformlevelVoList.get(0);
				 levelname = userperformlevelVo1.getLevelname();
				 remarks = userperformlevelVo1.getRemarks();
				 score = userperformlevelVo1.getScore()+"";
			 }
			 userperformlevelVoTmp = new UserperformlevelVoTmp();
			 userperformlevelVoTmp.setUsername(userlevelVo.getUsername());
			 userperformlevelVoTmp.setUserid(userid+"");
			 userperformlevelVoTmp.setRemarks(remarks);
			 userperformlevelVoTmp.setScore(score);
			 userperformlevelVoTmp.setRemarks(remarks);
			 userperformlevelVoTmp.setLevelname(levelname);
			 resultList.add(userperformlevelVoTmp);
		}
		return resultList;
	}
	
	@Override
	public void insertUserperformlevel(List<UserperformlevelVo> userperformlevelVoList) {
		// TODO Auto-generated method stub
		if(userperformlevelVoList!=null&&userperformlevelVoList.size()>0){
			for(int i=0;i<userperformlevelVoList.size();i++){
				UserperformlevelVo userperformlevelVo = userperformlevelVoList.get(i);
				//判断本月本用户是否已经考评，已考评只更新，未考评则插入
				UserperformlevelVo userperformlevelVo2 = new UserperformlevelVo();
				boolean result = false;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
				Date checkdate = userperformlevelVo.getCheckdate();
				String checkdatestr = sdf.format(checkdate);
				String dateStr = checkdatestr;
				if(dateStr.length()==10)dateStr = dateStr.substring(0, 7);
				userperformlevelVo.setCheckdatestr(dateStr);
				List<UserperformlevelVo> list = userperformlevelVoMapper.selectUserperformlevelListByCond(userperformlevelVo);
				if(list!=null&&list.size()>0){
					result = true;
					userperformlevelVo2=list.get(0);
				}
				if(result){
					userperformlevelVo.setId(userperformlevelVo2.getId());
					userperformlevelVoMapper.updateByPrimaryKeySelective(userperformlevelVo);
				}
				else
				userperformlevelVoMapper.insertSelective(userperformlevelVo);
			}
			
		}
		
	}


	@Override
	public void doDel(BigDecimal id) {
		// TODO Auto-generated method stub
		userperformlevelVoMapper.deleteByPrimaryKey(id);
	}

}
