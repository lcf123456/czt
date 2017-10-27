/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sys.PostInfoVoMapper;
import com.ztel.app.service.sys.PostinfoService;
import com.ztel.app.vo.sys.PostInfoVo;

/**
 * @author NJ
 * @since 2017年2月27日
 */
@Service
public class PostinfoServiceImpl implements PostinfoService {
	
	@Autowired
	private PostInfoVoMapper postInfoVoMapper = null;
	
	@Override
	public List<PostInfoVo> getPostinfoTreeList() {
		List<PostInfoVo> resultList = new ArrayList<PostInfoVo>();
		int defaultParentId=0;
		  List<PostInfoVo> oneLevelList = this.postInfoVoMapper.getPostinfoList(defaultParentId, "10");
		  //判断一级岗位是否有值
		  if (oneLevelList!=null&&oneLevelList.size()>0) {
			  for (int i = 0; i < oneLevelList.size(); i++) {
				  PostInfoVo onePostinfo =oneLevelList.get(i);
				  int oneparentId = onePostinfo.getId();
				  onePostinfo.setIcon("");
				  //根据一级id获取二级子岗位信息
				  List<PostInfoVo> twoLevelList = this.postInfoVoMapper.getPostinfoList(oneparentId, "20");
				  //判断二级子岗位是否有值，有则取出用于取三级岗位
				  if (twoLevelList!=null&&twoLevelList.size()>0) {
					  onePostinfo.setChildren(twoLevelList);
					  for (int j = 0; j < twoLevelList.size(); j++) {
						  PostInfoVo twoPostinfo =twoLevelList.get(j);
						  int twoparentId = twoPostinfo.getId();
						  twoPostinfo.setIcon("");
						//根据二级id获取三级子岗位信息
						List<PostInfoVo> threeLevelList = this.postInfoVoMapper.getPostinfoList(twoparentId, "30");
						if (threeLevelList!=null&&threeLevelList.size()>0) {
							twoPostinfo.setChildren(threeLevelList);
							for(int k=0;k<threeLevelList.size();k++){
								PostInfoVo threePostinfo =threeLevelList.get(k);
								int threeparentId = threePostinfo.getId();
								threePostinfo.setIcon("");
								//根据三级id获取四级子岗位信息
								List<PostInfoVo> fourLevelList = this.postInfoVoMapper.getPostinfoList(threeparentId, "40");
								if(fourLevelList!=null&&fourLevelList.size()>0){
									threePostinfo.setChildren(fourLevelList);
									for(int l=0;l<fourLevelList.size();l++){
										PostInfoVo fourPostinfo =fourLevelList.get(l);
										int fourparentId = fourPostinfo.getId();
										fourPostinfo.setIcon("");
										//根据四级id获取五级子岗位信息
										List<PostInfoVo> fiveLevelList = this.postInfoVoMapper.getPostinfoList(fourparentId, "50");
										if(fiveLevelList!=null&&fiveLevelList.size()>0){
											fourPostinfo.setChildren(fiveLevelList);
										}
									}
								}
							}
						}
					}
				}
			}
			  resultList=oneLevelList;
		}
		
		return resultList;
	}

	@Override
	public List<PostInfoVo> getPostinfoList() {
		List<PostInfoVo> resultList = new ArrayList<PostInfoVo>();
		int defaultParentId=0;
		  List<PostInfoVo> oneLevelList = this.postInfoVoMapper.getPostinfoList(defaultParentId, "10");
		  //判断一级岗位是否有值
		  if (oneLevelList!=null&&oneLevelList.size()>0) {
			  for (int i = 0; i < oneLevelList.size(); i++) {
				  PostInfoVo onePostinfo =oneLevelList.get(i);
				  int oneparentId = onePostinfo.getId();
				  resultList.add(onePostinfo);
				  //根据一级id获取二级子岗位信息
				  List<PostInfoVo> twoLevelList = this.postInfoVoMapper.getPostinfoList(oneparentId, "20");
				  //判断二级子岗位是否有值，有则取出用于取三级岗位
				  if (twoLevelList!=null&&twoLevelList.size()>0) {
					  for (int j = 0; j < twoLevelList.size(); j++) {
						  PostInfoVo twoPostinfo =twoLevelList.get(j);
						  int twoparentId = twoPostinfo.getId();
						  resultList.add(twoPostinfo);
						//根据二级id获取三级子岗位信息
						List<PostInfoVo> threeLevelList = this.postInfoVoMapper.getPostinfoList(twoparentId, "30");
						if (threeLevelList!=null&&threeLevelList.size()>0) {
							for(int k=0;k<threeLevelList.size();k++){
								PostInfoVo threePostinfo =threeLevelList.get(k);
								int threeparentId = threePostinfo.getId();
								resultList.add(threePostinfo);
								//根据三级id获取四级子岗位信息
								List<PostInfoVo> fourLevelList = this.postInfoVoMapper.getPostinfoList(threeparentId, "40");
								if(fourLevelList!=null&&fourLevelList.size()>0){
									for(int l=0;l<fourLevelList.size();l++){
										PostInfoVo fourPostinfo =fourLevelList.get(l);
										int fourparentId = fourPostinfo.getId();
										resultList.add(fourPostinfo);
										//根据四级id获取五级子岗位信息
										List<PostInfoVo> fiveLevelList = this.postInfoVoMapper.getPostinfoList(fourparentId, "50");
										if(fiveLevelList!=null&&fiveLevelList.size()>0){
											for(int z=0;z<fiveLevelList.size();z++){
												PostInfoVo fivePostinfo =fiveLevelList.get(z);
												resultList.add(fivePostinfo);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return resultList;
	}

	@Override
	public void insertPostInfo(PostInfoVo postInfoVo) {
		if (postInfoVo!=null) {
			postInfoVoMapper.insertSelective(postInfoVo);
		}
	}

	@Override
	public void updatePostInfo(PostInfoVo postInfoVo) {
		// TODO Auto-generated method stub
		if (postInfoVo!=null) {
			postInfoVoMapper.updateByPrimaryKeySelective(postInfoVo);
		}
	}

	@Override
	public void delPostInfo(List<Integer> ids) {
		// TODO Auto-generated method stub
		if(ids!=null&&ids.size()>0){
			for(int i=0;i<ids.size();i++){
				postInfoVoMapper.deleteByPrimaryKey(ids.get(i));
			}
		}
	}

	@Override
	public List<HashMap<String, String>> getPostCombobox() {
		 // TODO Auto-generated method stub
		 List<PostInfoVo> treeList=getPostinfoList();
		 List<HashMap<String, String>> boxList=new ArrayList<>();
		 if (treeList!=null&&treeList.size()>0) {
			 for(int i=0;i<treeList.size();i++){
				 PostInfoVo vo=treeList.get(i);
				 HashMap<String, String> map=new HashMap<String, String>();
				 map.put("id", vo.getId().toString());
				 map.put("postname", vo.getPostname());
				 //System.out.println(vo.getId().toString()+"-------------");
				 //System.out.println(vo.getText()+"-------------");
				 boxList.add(map);
			 }
		}
		 
		 return boxList;
	}
	

}
