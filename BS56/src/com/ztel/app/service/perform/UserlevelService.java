package com.ztel.app.service.perform;

import java.util.List;

import com.ztel.app.vo.perform.UserlevelVo;

public interface UserlevelService {
	
	public List<UserlevelVo> getUserlevelList(Long id);
	
    public void doAddUserlevel(String userstr,String roleid,String level);
	
	public int doEditUserlevel(UserlevelVo userlevelVo);
	
	public int doDelUserlevel(Long userid);

}
