package com.ztel.app.service.sys;

import java.util.HashMap;
import java.util.List;

import com.ztel.app.vo.sys.PostInfoVo;


/**
 * 
 * @author NJ
 *
 */
public interface PostinfoService {
	
	public List<PostInfoVo> getPostinfoTreeList();
	/**
	 * 取所有岗位信息
	 * @param 
	 * @return
	 */
	public List<PostInfoVo> getPostinfoList();
	public void insertPostInfo(PostInfoVo postInfoVo);
	public void updatePostInfo(PostInfoVo postInfoVo);
	public void delPostInfo(List<Integer> ids);
	
	/**
	 * 取岗位下拉列表
	 * @return
	 */
	public List<HashMap<String, String>> getPostCombobox() ;
	
}
