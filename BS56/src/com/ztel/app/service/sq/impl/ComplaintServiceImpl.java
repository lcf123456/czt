package com.ztel.app.service.sq.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sq.ComplaintVoMapper;
import com.ztel.app.service.sq.ComplaintService;
import com.ztel.app.service.sys.BaseTypeInfoService;
import com.ztel.app.service.sys.DeptVoService;
import com.ztel.app.vo.sq.ComplaintVo;
import com.ztel.app.vo.sys.BasetypeInfoVo;
import com.ztel.app.vo.sys.DeptVo;
import com.ztel.framework.vo.Pagination;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	private ComplaintVoMapper ComplaintVoMapper = null;
	
	@Autowired
	private BaseTypeInfoService baseTypeInfoService = null;
	
	@Autowired
	private DeptVoService deptVoService = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public ComplaintServiceImpl(){
		sortKeyMapping.put("id", "id");
	}
	
	public List<ComplaintVo> getComplaintList(Pagination<?> page){
		page.sortKeyToColumn(sortKeyMapping);
		return ComplaintVoMapper.getComplaintPageList(page);
	}
	
	public void doNewComplaint(ComplaintVo complaintVo){
		if(complaintVo != null ) {
			this.ComplaintVoMapper.insertSelective(complaintVo);
	}
	}
	
	public void doUpdate(ComplaintVo complaintVo){
		this.ComplaintVoMapper.updateByPrimaryKeySelective(complaintVo);
	}
	

	/**
	 * 根据id删除信息
	 */
	public void deleteByPrimaryKey(List<Integer> ids)
	{
		if(ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				this.ComplaintVoMapper.deleteByPrimaryKey(id);
			}
		}
	}
	
	/**
	 * 投诉报表、返回type类型的数量和部门
	 * @param type
	 * @return
	 */
	public Map<String, Object> getComplaintReport(String starttime,String endtime){
		Map<String, Object> map=new HashMap<String, Object>(); 
		List<ComplaintVo> complaintVoList = new ArrayList<ComplaintVo>();
		List<BasetypeInfoVo> basetypeInfoVoList = baseTypeInfoService.getBaseTypeinfoList("COMPLAINT");
		List<DeptVo> deptVoList = deptVoService.getDeptinfoForReport();
		int deptSize = deptVoList.size();
		int size = basetypeInfoVoList.size();
		int[][] ctTmp = new int[deptSize][size];//横向合计用
		int ctSum = 0;//合计数
		int ctAll = 0;
		
		//map.put("baseTypeList", basetypeInfoVoList);
		String[] baseStrArray = new String[size+2];
		//返回basetypeInfo 串
		for(int i=0;i<size;i++){
			BasetypeInfoVo basetypeInfoVo = basetypeInfoVoList.get(i);
			baseStrArray[i+1]=basetypeInfoVo.getContentlist();
		}
		baseStrArray[0] = "单位";
		baseStrArray[size+1] = "合计";
		map.put("baseTypeList", baseStrArray);
		
		if(deptVoList!=null&&deptVoList.size()>0){
			
			for(int j=0;j<deptSize;j++){
				ComplaintVo complaintVo = new ComplaintVo();
				DeptVo deptVo = deptVoList.get(j);
				complaintVo.setDeptname(deptVo.getDeptname());
				int deptid = deptVo.getId();
				
				
				String ctStr = "";
				
				ctSum = 0;
				if(basetypeInfoVoList!=null&&basetypeInfoVoList.size()>0){
					for(int i=0;i<size;i++){
						BasetypeInfoVo basetypeInfoVo = basetypeInfoVoList.get(i);
						String type = basetypeInfoVo.getId()+"";
						int ct = this.ComplaintVoMapper.getComplaintReportCt(type, deptid+"",starttime,endtime);
						ctStr = ctStr+ct+";";
						ctSum = ctSum+ct;
						ctTmp[j][i]= ct;
					}
				}
				ctAll = ctAll + ctSum;
				ctStr = ctStr +ctSum+";";
				complaintVo.setCtstr(ctStr);
				//complaintVo.setCt(ctSum);//纵向合计
				complaintVoList.add(complaintVo);
			}
			//增加合计列
			ComplaintVo complaintVo = new ComplaintVo();
			complaintVo.setDeptname("合计");
			String ctDeptSum = "";
			for(int i=0;i<size;i++){
				int ctDept = 0;
				for(int j=0;j<deptSize;j++){
					ctDept = ctDept+ctTmp[j][i];
				}
				ctDeptSum = ctDeptSum + ctDept+";";
			}
			ctDeptSum = ctDeptSum + ctAll+";";
			complaintVo.setCtstr(ctDeptSum);
			//complaintVo.setCt(ctAll);
			complaintVoList.add(complaintVo);
		}
		map.put("complaintList", complaintVoList);
		
		return map;
	}
}
