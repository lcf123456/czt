package com.ztel.app.service.sys.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.sys.BaseMultitypeInfoVoMapper;
import com.ztel.app.service.sys.BaseMultitypeInfoService;
import com.ztel.app.vo.gis.GroupinfoVo;
import com.ztel.app.vo.sys.BaseMultitypeInfoVo;
import com.ztel.app.vo.sys.MenuInfoVo;

@Service
public class BaseMultitypeInfoServiceImpl implements BaseMultitypeInfoService {

	@Autowired 
	private BaseMultitypeInfoVoMapper BaseMultitypeInfoVoMapper = null;
	
	@Override
	/**
	 * 根据父id获取列表
	 * @param parentId
	 * @return
	 */
	public List<BaseMultitypeInfoVo> searchBaseMultitypeInfoList(BaseMultitypeInfoVo record) {
		List<BaseMultitypeInfoVo> resultList = new ArrayList<BaseMultitypeInfoVo>();
		
//		BigDecimal parentId=new BigDecimal(Integer.parseInt(fid));
//		System.out.println("fid="+fid+",parentId="+parentId);
		  List<BaseMultitypeInfoVo> oneMenuList = this.BaseMultitypeInfoVoMapper.getBaseMultitypeInfoVoList(record);
		  //判断一级栏目是否有值
		  if (oneMenuList!=null&&oneMenuList.size()>0) {
			  for (int i = 0; i < oneMenuList.size(); i++) {
				  BaseMultitypeInfoVo oneMenuinfo =oneMenuList.get(i);
				  oneMenuinfo.setState("closed");
				  BigDecimal oneparentId = oneMenuinfo.getId();
				  
				  //重新设置父级设置
				  BaseMultitypeInfoVo record2 = new BaseMultitypeInfoVo();
				  record2.setParentid(oneparentId);
				  //record2.setId(record.getId());
				  //根据一级id获取二级子栏目信息
				  List<BaseMultitypeInfoVo> TwoMenuList = this.BaseMultitypeInfoVoMapper.getBaseMultitypeInfoVoList(record2);
				  if(TwoMenuList!=null&&TwoMenuList.size()>0){
					  for(int j=0;j<TwoMenuList.size();j++){
						  BaseMultitypeInfoVo TwoMenuInfoVo = TwoMenuList.get(j);
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
		
		return oneMenuList;
	}

	/**
	 * 新增多级类别
	 */
	public void doAddMultitype(BaseMultitypeInfoVo baseMultitypeInfoVo){
		if(baseMultitypeInfoVo.getTypelevel()!=null){
			//菜单级次需要设置，传过来的参数是父菜单的级次，需要在父菜单的基础上加1
			BigDecimal typelevel = baseMultitypeInfoVo.getTypelevel().add(new BigDecimal("1"));
			baseMultitypeInfoVo.setTypelevel(typelevel);
		}
		else
		{
			BigDecimal fid = new BigDecimal("0");
			baseMultitypeInfoVo.setParentid(fid);
			baseMultitypeInfoVo.setTypelevel(new BigDecimal("1"));
		}
		BaseMultitypeInfoVoMapper.insertSelective(baseMultitypeInfoVo);
	}
	
	public void doEditMultitype(BaseMultitypeInfoVo baseMultitypeInfoVo){
		BaseMultitypeInfoVoMapper.updateByPrimaryKeySelective(baseMultitypeInfoVo);
	}
	
	/**
	 * 获取下拉框列表
	 */
	public List<HashMap<String, String>> getMultitypeCombobox(BaseMultitypeInfoVo baseMultitypeInfoVo){
		BaseMultitypeInfoVo vo = new BaseMultitypeInfoVo();
		List<BaseMultitypeInfoVo> treeList=this.BaseMultitypeInfoVoMapper.getBaseMultitypeListByCond(baseMultitypeInfoVo);
	   	 List<HashMap<String, String>> boxList=new ArrayList<>();
	   	 if (treeList!=null&&treeList.size()>0) {
	   		 for(int i=0;i<treeList.size();i++){
	   			BaseMultitypeInfoVo groupinfoVo=treeList.get(i);
	   			 HashMap<String, String> map=new HashMap<String, String>();
	   			 map.put("id", groupinfoVo.getId()+"");
	   			 map.put("code", groupinfoVo.getTypecode());
	   			 map.put("name", groupinfoVo.getTypename());
	   			 map.put("content", groupinfoVo.getContentlist());
	   			 boxList.add(map);
	   		 }
	   	 }
	   	 return boxList;
	}
}
