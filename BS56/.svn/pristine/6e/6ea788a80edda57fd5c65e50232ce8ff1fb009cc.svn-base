package com.ztel.app.service.perform.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.perform.UserlevelVoMapper;
import com.ztel.app.persist.mybatis.sys.UserVoMapper;
import com.ztel.app.service.perform.UserlevelService;
import com.ztel.app.vo.perform.UserlevelVo;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.sys.UserrolerelativeVo;
import com.ztel.framework.util.PubUtils;

@Service
public class UserlevelServiceImpl implements UserlevelService {
	@Autowired
	private UserlevelVoMapper userlevelVoMapper = null;
	
	@Autowired
	private UserVoMapper userVoMapper= null;

	@Override
	public List<UserlevelVo> getUserlevelList(Long id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				List<UserlevelVo> resultList = new ArrayList<UserlevelVo>();
				
				UserlevelVo userlevelVo_one = new UserlevelVo();
				List<UserlevelVo> oneMenuList = this.userlevelVoMapper.selectUserlevelList(id);
				  //判断一级栏目是否有值
				  if (oneMenuList!=null&&oneMenuList.size()>0) {
					  for (int i = 0; i < oneMenuList.size(); i++) {
						  UserlevelVo oneMenuinfo =oneMenuList.get(i);
						  oneMenuinfo.setState("closed");
						  Long oneparentId = oneMenuinfo.getUserid();
						  //根据一级id获取二级子栏目信息
						  List<UserlevelVo> TwoMenuList = this.userlevelVoMapper.selectUserlevelList(oneparentId);
						  if(TwoMenuList!=null&&TwoMenuList.size()>0){
							  for(int j=0;j<TwoMenuList.size();j++){
								  UserlevelVo TwoMenuInfoVo = TwoMenuList.get(j);
									  TwoMenuInfoVo.setState("closed");
							  }
						  }
						  else
						  {
							  oneMenuinfo.setState("open");
						  }
					}
					  resultList=oneMenuList;
				}
				
				return resultList;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doAddUserlevel(String userstr,String userid,String level) {
		Long useridL = Long.parseLong(userid);
		String codestr = PubUtils.userstrExtract(userstr);//codestr格式为0502;006;
		String[] codestrArr = codestr.split(";");
		UserrolerelativeVo userrolerelativeVo = new UserrolerelativeVo();
		//授权之前删除该角色下已授权用户,前版本为用户和角色挂接表，后修改为直接对用户表授权角色
		//userrolerelativeVoMapper.deleteByRoleid(roleid);
		//授权之前删除该角色下已授权用户
		userlevelVoMapper.deleteByUserid(useridL);
		
		if(codestrArr!=null&&codestrArr.length>0){
			for(int i=0;i<codestrArr.length;i++){
				UserVo userVo = userVoMapper.selectByCodeAndPwd(codestrArr[i], "");
				Long userid1 = userVo.getId();
					UserlevelVo userlevelVo = new UserlevelVo();
					userlevelVo.setUserid(userid1);
					userlevelVo.setParentid(useridL);
					//userlevelVo.setUserlevel(Integer.parseInt(level)+1);
					userlevelVoMapper.insertSelective(userlevelVo);
			}
		}
	}
	

	@Override
	public int doEditUserlevel(UserlevelVo userlevelVo) {
		// TODO Auto-generated method stub
		return userlevelVoMapper.updateByPrimaryKeySelective(userlevelVo);
	}

	@Override
	public int doDelUserlevel(Long userid) {
		// TODO Auto-generated method stub
		return userlevelVoMapper.deleteAllByUserid(userid);
	}

}
