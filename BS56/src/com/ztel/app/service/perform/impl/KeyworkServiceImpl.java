package com.ztel.app.service.perform.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.perform.KeyworkVoMapper;
import com.ztel.app.persist.mybatis.perform.WorkcontentVoMapper;
import com.ztel.app.service.perform.KeyworkService;
import com.ztel.app.service.perform.UserlevelService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.perform.DeptmonthLineVo;
import com.ztel.app.vo.perform.KeyworkVo;
import com.ztel.app.vo.perform.UserlevelVo;
import com.ztel.app.vo.perform.WorkcontentVo;

@Service
public class KeyworkServiceImpl implements KeyworkService {

	@Autowired
	private KeyworkVoMapper keyworkVoMapper = null;
	
	@Autowired
	private WorkcontentVoMapper workcontentVoMapper = null;
	
	@Autowired
	private UserlevelService userlevelService = null;

	
	@Override
	public List<KeyworkVo> selectKeyworkList(KeyworkVo keyworkVo) {
		// TODO Auto-generated method stub
		return keyworkVoMapper.selectListByCond(keyworkVo);
	}

	/**
	 * 获取考核列表，包括重点工作及日常工作
	 */
	@Override
	public List<DeptmonthLineVo> selectAllKeyworkList(KeyworkVo keyworkVo) {
		// TODO Auto-generated method stub
		List<DeptmonthLineVo> resultList = new ArrayList<DeptmonthLineVo>();
		
		int tmpbz = 0;//用于记录是否有重点工作或日常工作中的任何一项，0表示都没有，1表示至少有其中一个。
		int keyworkcount = 0;//临时计数，用于判断是否存在记录，没有需要特殊处理
		int safecount=0;
		 int contentwk = 0;
		 int tmpwork=0;
		//重点工作
		BigDecimal weight = new BigDecimal("0");
		List<KeyworkVo> keyworkList = keyworkVoMapper.selectListByCond(keyworkVo);
		if(keyworkList!=null&&keyworkList.size()>0){
			keyworkcount = keyworkList.size();
			for(int i =0;i<keyworkList.size();i++){
				KeyworkVo KeyworkVo1 = keyworkList.get(i);
				DeptmonthLineVo deptmonthaddVo = new DeptmonthLineVo();
				deptmonthaddVo.setId(KeyworkVo1.getId().longValue());
				deptmonthaddVo.setTypename(Constant.keywork);
				deptmonthaddVo.setScorevalue(KeyworkVo1.getWeight().multiply(Constant.keyworkweight));
				deptmonthaddVo.setContent(KeyworkVo1.getContent());
				deptmonthaddVo.setProcess(KeyworkVo1.getProcess());
				deptmonthaddVo.setSelfscore(KeyworkVo1.getWeight().multiply(Constant.keyworkweight));//权重*分值
				deptmonthaddVo.setCtype(new BigDecimal("10"));//此处10：重点工作 20:日常工作
				deptmonthaddVo.setCreatename(KeyworkVo1.getCreatename());
				//deptmonthaddVo.setCtype("10");//此处10：重点工作 20:日常工作
				
				if(KeyworkVo1.getWeight()!=null)weight = KeyworkVo1.getWeight();
				deptmonthaddVo.setWeight(weight);
				resultList.add(deptmonthaddVo);
				tmpbz=1;
			}
		} 
		//日常工作
		WorkcontentVo workcontentVo = new WorkcontentVo();
		if(keyworkVo.getWorkdate()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
			String workdate = sdf.format(keyworkVo.getWorkdate());
			workcontentVo.setWorkdatestr(workdate);
		 }
		 if(keyworkVo.getDeptid()!=null){
			 workcontentVo.setDeptid(keyworkVo.getDeptid());
		 }
		 
		 BigDecimal needCount = new BigDecimal("0");
		 List<WorkcontentVo> workcontentList = new ArrayList<WorkcontentVo>();
		 workcontentList = workcontentVoMapper.selectListByCond(workcontentVo);
		 if(workcontentList!=null && workcontentList.size()>0){
			 for(int i=0;i<workcontentList.size();i++){
				 KeyworkVo KeyworkVo = new KeyworkVo();
				 WorkcontentVo workcontentVo1 = workcontentList.get(i);
				 DeptmonthLineVo deptmonthaddVo = new DeptmonthLineVo();
				 deptmonthaddVo.setId(workcontentVo1.getId().longValue());
				 deptmonthaddVo.setContent(workcontentVo1.getMatters());//考核项目、考核内容
				 deptmonthaddVo.setProcess(workcontentVo1.getStandard());//考核标准、考核进度
				 if(workcontentVo1.getWeight()!=null)
				 deptmonthaddVo.setCtype(new BigDecimal("20"));//此处10：重点工作 20:日常工作
				 deptmonthaddVo.setCreatename(workcontentVo1.getCreateusername());
				// deptmonthaddVo.setCtype("20");//此处10：重点工作 20:日常工作
				 if(workcontentVo1.getWeight()!=null)weight = workcontentVo1.getWeight();
					deptmonthaddVo.setWeight(weight);
					
				 if(workcontentVo1.getProperty()!=null&&workcontentVo1.getProperty().equals(Constant.safe)){
					 deptmonthaddVo.setTypename("关键隐患控制");
					 deptmonthaddVo.setScorevalue(workcontentVo1.getWeight().multiply(Constant.safeweight));
					 deptmonthaddVo.setSelfscore(workcontentVo1.getWeight().multiply(Constant.safeweight));//权重*分值
					 safecount++;
				 }else if(workcontentVo1.getProperty()!=null&&workcontentVo1.getProperty().equals(Constant.contentwk)){
					 deptmonthaddVo.setTypename("日常工作");
					 deptmonthaddVo.setScorevalue(workcontentVo1.getWeight().multiply(Constant.contentwkweight));
					 deptmonthaddVo.setSelfscore(workcontentVo1.getWeight().multiply(Constant.contentwkweight));//权重*分值
					 contentwk++;
				 }else if(workcontentVo1.getProperty()!=null&&workcontentVo1.getProperty().equals(Constant.tmpwork)){
					 deptmonthaddVo.setTypename("临时性工作");
					 deptmonthaddVo.setScorevalue(workcontentVo1.getWeight().multiply(Constant.tmpworkweight));
					 deptmonthaddVo.setSelfscore(workcontentVo1.getWeight().multiply(Constant.tmpworkweight));//权重*分值
					 tmpwork++;
				 }
				 resultList.add(deptmonthaddVo);
				 tmpbz=1;
			 }
		 }
		 if(tmpbz==1 && keyworkcount==0){
			 needCount =needCount.add(Constant.keyworkweight);
		 }
		 if(tmpbz==1 && safecount==0){
			 needCount =needCount.add(Constant.safeweight);
		 }
		 if(tmpbz==1 && contentwk==0){
			 needCount =needCount.add(Constant.contentwkweight);
		 }
		 if(tmpbz==1 && tmpwork==0){
			 needCount =needCount.add(Constant.tmpworkweight);
		 }
		 if(tmpbz==1 && resultList!=null&&resultList.size()>0){
			 for(int i=0;i<resultList.size();i++){
				 DeptmonthLineVo deptmonthaddVo = resultList.get(i);
				 deptmonthaddVo.setTmpscore(needCount);
			 }
		 }
		return resultList;
	}
	
	/**
	 * 获取部长考核列表，包括重点工作及日常工作,在部长考核的新增中显示列表
	 * 根据部长id，需要获取部长管辖的用户所在部门id，从而根据部门id获取重点工作或日常工作
	 * @param keyworkVo
	 * @return
	 */
	public List<DeptmonthLineVo>  selectDeptmngAllKeyworkList(KeyworkVo keyworkVo){
		//3、对分值和权重做人数平均
				int size = 0;
		//1、先查自己的考核项列表
		List<DeptmonthLineVo> resultList = selectAllKeyworkList(keyworkVo);
		if(resultList!=null&&resultList.size()>0)size=1;
		Long userid = keyworkVo.getCreateid();
		keyworkVo.setCreateid(null);
		//2、根据用户id获取其它用户的考核项列表
		
		List<UserlevelVo> userlevelVoList = userlevelService.getUserlevelList(userid);
		if(userlevelVoList!=null&&userlevelVoList.size()>0){
			for(int i=0;i<userlevelVoList.size();i++){
				UserlevelVo userlevelVo1= userlevelVoList.get(i);
				Integer userdept = userlevelVo1.getDeptid();
				keyworkVo.setDeptid(userdept);
				List<DeptmonthLineVo> deptmonthLineVoList = selectAllKeyworkList(keyworkVo);
				if(deptmonthLineVoList!=null&&deptmonthLineVoList.size()>0)size++;
				resultList.addAll(deptmonthLineVoList);
			}
		}
		
		if(size==0)size=1;
		if(resultList!=null&&resultList.size()>0){
			for(int i=0;i<resultList.size();i++){
				DeptmonthLineVo deptmonthLineVo2 = resultList.get(i);
				deptmonthLineVo2.setScorevalue(deptmonthLineVo2.getScorevalue().divide(new BigDecimal(size),2, BigDecimal.ROUND_HALF_UP));//平均后保留2位小数
				deptmonthLineVo2.setSelfscore(deptmonthLineVo2.getSelfscore().divide(new BigDecimal(size),2, BigDecimal.ROUND_HALF_UP));
				deptmonthLineVo2.setTmpscore(deptmonthLineVo2.getTmpscore().divide(new BigDecimal(size),2, BigDecimal.ROUND_HALF_UP));
			}
		}
		
		return resultList;
	}
	
	@Override
	public void insertKeywork(KeyworkVo keyworkVo) {
		// TODO Auto-generated method stub
		keyworkVoMapper.insertSelective(keyworkVo);
	}

	@Override
	public void doDel(Integer id) {
		// TODO Auto-generated method stub
		keyworkVoMapper.deleteByPrimaryKey(id);
	}

}
