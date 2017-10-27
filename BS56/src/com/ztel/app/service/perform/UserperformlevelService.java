package com.ztel.app.service.perform;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.perform.UserlevelVo;
import com.ztel.app.vo.perform.UserperformlevelVo;
import com.ztel.app.vo.perform.UserperformlevelVoTmp;
import com.ztel.framework.vo.Pagination;

public interface UserperformlevelService {

	public List<UserperformlevelVo> selectUserperformlevelPageList(Pagination<?> page);
	
	public List<UserperformlevelVo> selectUserperformlevelListByCond(UserperformlevelVo userperformlevelVo);
	/**
	 * 新增时需要查询用户id，同时获取考核表中用户的考核情况
	 * @param userlevelList
	 * @param scoredate
	 * @return
	 */
	public List<UserperformlevelVoTmp> selectAddUserListByCond(List<UserlevelVo> userlevelList,String scoredate);
	
	public void insertUserperformlevel(List<UserperformlevelVo> userperformlevelVoList);
	
	public void doDel(BigDecimal id);
}
