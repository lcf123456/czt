package com.ztel.app.service.perform;

import java.util.List;

import com.ztel.app.vo.perform.DeptmonthLineVo;
import com.ztel.app.vo.perform.KeyworkVo;

public interface KeyworkService {

public List<KeyworkVo>  selectKeyworkList(KeyworkVo keyworkVo);
/**
 * 获取副部长考核列表，包括重点工作及日常工作,在绩效考核（经理考核即副部长考核）的新增中显示列表
 * @param keyworkVo
 * @return
 */
public List<DeptmonthLineVo>  selectAllKeyworkList(KeyworkVo keyworkVo);

/**
 * 获取部长考核列表，包括重点工作及日常工作,在部长考核的新增中显示列表
 * 根据部长id，需要获取部长管辖的用户所在部门id，从而根据部门id获取重点工作或日常工作
 * @param keyworkVo
 * @return
 */
public List<DeptmonthLineVo>  selectDeptmngAllKeyworkList(KeyworkVo keyworkVo);
	
	public void insertKeywork(KeyworkVo keyworkVo);
	
	public void doDel(Integer id);
}
