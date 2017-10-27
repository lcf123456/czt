package com.ztel.app.persist.mybatis.wms;

import com.ztel.app.vo.wms.OutBoundVo;
import com.ztel.framework.vo.Pagination;

import java.math.BigDecimal;
import java.util.List;

public interface OutBoundVoMapper {
	List<OutBoundVo> selectOutoundPageList(Pagination<?> page);
	
	List<OutBoundVo> selectListByCond(OutBoundVo outBoundVo);
	
    /**
     *
     * @mbggenerated 2017-08-01
     */
    int deleteByPrimaryKey(BigDecimal outboundid);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insert(OutBoundVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insertSelective(OutBoundVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    OutBoundVo selectByPrimaryKey(BigDecimal outboundid);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKeySelective(OutBoundVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKey(OutBoundVo record);
    
    /**
     * 删除
     * @param record
     * @return
     */
    int deleteByCond(OutBoundVo record);
}