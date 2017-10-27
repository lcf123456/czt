package com.ztel.app.persist.mybatis.wms;

import java.math.BigDecimal;
import java.util.List;

import com.ztel.app.vo.wms.CigarettedamagedLineVo;

public interface CigarettedamagedLineVoMapper {
	
	List<CigarettedamagedLineVo> selectListByCond(CigarettedamagedLineVo record);
    /**
     *
     * @mbggenerated 2017-08-02
     */
    int insert(CigarettedamagedLineVo record);

    /**
     *
     * @mbggenerated 2017-08-02
     */
    int insertSelective(CigarettedamagedLineVo record);
    
    int deleteByInboundid(BigDecimal inboundid);
}