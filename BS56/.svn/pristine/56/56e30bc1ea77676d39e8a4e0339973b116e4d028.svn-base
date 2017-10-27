package com.ztel.app.service.cost.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.cost.SPLTypeConsumeVoMapper;
import com.ztel.app.persist.mybatis.cost.SPLTypeInfoVoMapper;
import com.ztel.app.persist.mybatis.sys.DeptVoMapper;
import com.ztel.app.service.cost.SPLTypeConsumeServcie;
import com.ztel.app.vo.cost.SPLTypeConsumeVo;
import com.ztel.app.vo.cost.SPLTypeInfoVo;
import com.ztel.app.vo.sys.DeptVo;
@Service
public class SPLTypeConsumeServiceImpl implements SPLTypeConsumeServcie{

	@Autowired
	private SPLTypeConsumeVoMapper splTypeConsumeVoMapper= null;
	
	@Autowired 
	private DeptVoMapper deptVoMapper = null;
	
	@Autowired 
	private SPLTypeInfoVoMapper splTypeInfoVoMapper = null;

	@Override
	public List<HashMap<String, Object>> getSPLConsumeByDept(SPLTypeConsumeVo splTypeConsumeVo) {
		//取部门领料耗用信息
		List<HashMap<String,Object>> consumeMap = splTypeConsumeVoMapper.getSPLConsumeByDept(splTypeConsumeVo);
		//处理取出的耗用信息存入map,容器中的key格式为deptid-typeid
		HashMap<String,Double> valMap=new HashMap<String,Double>();
		for (HashMap<String, Object> map : consumeMap) {
		      String deptid = null;
		      String typeid = null;
		      double amount = 0;
		      for (HashMap.Entry<String, Object> entry : map.entrySet()) {
		        if ("DEPTID".equals(entry.getKey())) {
		        	deptid = entry.getValue().toString();
		        } else if ("TYPEID".equals(entry.getKey())) {
		        	typeid = entry.getValue().toString();
		        }else{
		        	amount= ((java.math.BigDecimal) entry.getValue()).doubleValue();
		        }
		      }
		      valMap.put(deptid+"-"+typeid, amount);
		}
		//取二级部门信息
		List<DeptVo> deptList=deptVoMapper.getDeptinfoList("2", "", "");
		//取主物资类别信息
		SPLTypeInfoVo splTypeInfoVo=new SPLTypeInfoVo();
		splTypeInfoVo.setFid(2);
		splTypeInfoVo.setClevel(2);
		List<SPLTypeInfoVo> splTypeList=splTypeInfoVoMapper.getSPLTypeInfoListByCondition(splTypeInfoVo);
		//--------------------------------------------------------------------------------------------------------------------
		//组合数据
		int deptlen=deptList.size();
		int typelen=splTypeList.size();
		int deptid=0,typeid=0;
		double deptsum=0,consume=0;
		List lst=new ArrayList();
		String key="";
		for(int i=0;i<deptlen;i++){
			deptsum=0;
			HashMap map=new HashMap();
			DeptVo deptVo=deptList.get(i);
			deptid=deptVo.getId();
			map.put("deptname", deptVo.getDeptname());
			for(int j=0;j<typelen;j++){
				
				SPLTypeInfoVo infoVo=splTypeList.get(j);
				typeid=infoVo.getId();
				key=deptid+"-"+typeid;
				
				if(valMap.containsKey(key))consume=valMap.get(key);
				else consume=0;
				
				deptsum=deptsum+consume;
				
				map.put("spltype"+j, consume);
			}
			map.put("totalamount", deptsum);
			lst.add(map);
		}
		return lst;
	}
}
