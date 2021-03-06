package com.ztel.app.persist.mybatis.wms;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.wms.MoveareastockVo;
import com.ztel.framework.vo.Pagination;

public interface MoveareastockVoMapper {
	
	List<MoveareastockVo> selectMoveareastockPageList(Pagination<?> page);
	
	MoveareastockVo selectByPrimaryKey(BigDecimal id);
	
    /**
     *
     * @mbggenerated 2017-09-20
     */
    int insert(MoveareastockVo record);

    /**
     *
     * @mbggenerated 2017-09-20
     */
    int insertSelective(MoveareastockVo record);
    
    int updateByPrimaryKeySelective(MoveareastockVo record);
}