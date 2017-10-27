package com.ztel.app.persist.mybatis.wms;

import com.ztel.app.vo.wms.MantissaVo;
import java.math.BigDecimal;
import java.util.List;

public interface MantissaVoMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(MantissaVo record);

    int insertSelective(MantissaVo record);

    MantissaVo selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(MantissaVo record);

    int updateByPrimaryKey(MantissaVo record);
    
    List<MantissaVo> getMantissa(MantissaVo record);
}