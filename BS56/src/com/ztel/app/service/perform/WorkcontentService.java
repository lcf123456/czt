package com.ztel.app.service.perform;

import java.util.List;

import com.ztel.app.vo.perform.WorkcontentVo;

public interface WorkcontentService {

	public List<WorkcontentVo>  selectWorkcontentList(WorkcontentVo workcontentVo);
	
	public void insertWorkcontent(WorkcontentVo workcontentVo);
	
	public void doUpdate(WorkcontentVo workcontentVo);
	
	public void doDel(Integer id);
}
