package com.ztel.app.service.safe.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.safe.FireFacilitiesVoMapper;
import com.ztel.app.persist.mybatis.safe.HiddendangerVoMapper;
import com.ztel.app.persist.mybatis.sys.BlockcustomerVoMapper;
import com.ztel.app.service.safe.FireFacilitiesService;
import com.ztel.app.service.safe.HiddendangerService;
import com.ztel.app.vo.safe.FireFacilitiesVo;
import com.ztel.app.vo.safe.HiddendangerVo;
import com.ztel.app.vo.sys.BlockcustomerVo;
import com.ztel.app.vo.sys.RoleInfoVo;
import com.ztel.framework.vo.Pagination;


@Service
public class HiddendangerServiceImpl implements HiddendangerService {
	@Autowired
	private HiddendangerVoMapper hiddendangerVoMapper = null;
    private Map<String, String> sortKeyMapping = new HashMap<>();
	
    public HiddendangerServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("createid", "createid");
		sortKeyMapping.put("id", "id");
	}
	
	//隐患记录
	@Override
	public List<HiddendangerVo> searchDangercreatPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.hiddendangerVoMapper.getDangercreatPageList(page);
	}
	//隐患核实
	@Override
	public List<HiddendangerVo> searchDangerverifyPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.hiddendangerVoMapper.getDangerverifyPageList(page);
	}
	//隐患整改
	@Override
	public List<HiddendangerVo> searchDangerrectifyPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		page.sortKeyToColumn(sortKeyMapping);
		return this.hiddendangerVoMapper.getDangerrectifyPageList(page);
	}

	@Override
	public int delHangerrectify(List<Integer> ids) {
		// TODO Auto-generated method stub
		if(ids != null && ids.size() > 0) {
			for (Integer id : ids) {
				this.hiddendangerVoMapper.deleteByPrimaryKey(id);
			}
			return ids.size();
		}
		return 0;
	}

	@Override
	public int updateHangerrectify(HiddendangerVo hiddendangerVo) {
		// TODO Auto-generated method stub
		if (hiddendangerVo!=null&&!"".equals(hiddendangerVo.getId())) {
			return hiddendangerVoMapper.updateByPrimaryKeySelective(hiddendangerVo);
		}
		return 0;
	}
	//修改隐患记录
	@Override
	public int updateHangercreate(HiddendangerVo hiddendangerVo) {
		// TODO Auto-generated method stub
		if (hiddendangerVo!=null&&!"".equals(hiddendangerVo.getId())) {
			return hiddendangerVoMapper.updateByPrimaryKeySelective(hiddendangerVo);
		}
		return 0;
	}
	
	public int insertHangercreate(HiddendangerVo hiddendangerVo) {
		// TODO Auto-generated method stub
		if (hiddendangerVo!=null&&!"".equals(hiddendangerVo.getId())) {
			return hiddendangerVoMapper.insertSelective(hiddendangerVo);
		}
		return 0;
	}
//审核隐患核实
	@Override
	public int verifyDanger(HiddendangerVo hiddendangerVo) {
		return this.hiddendangerVoMapper.updateByPrimaryKeySelective(hiddendangerVo);
	}
	//处理隐患整改
	@Override
	public int handleDanger(HiddendangerVo hiddendangerVo) {
		return this.hiddendangerVoMapper.updateByPrimaryKeySelective(hiddendangerVo);
	}
}
