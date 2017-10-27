package com.ztel.app.persist.mybatis.account;

import com.ztel.app.vo.account.DistributionModeVo;
import java.math.BigDecimal;
import java.util.List;

public interface DistributionModeVoMapper {
	List<DistributionModeVo> getDIstributionModes();
	
	int updateAllModeDisable();
    /**
     *
     * @mbggenerated 2017-09-04
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-04
     */
    int insert(DistributionModeVo record);

    /**
     *
     * @mbggenerated 2017-09-04
     */
    int insertSelective(DistributionModeVo record);

    /**
     *
     * @mbggenerated 2017-09-04
     */
    DistributionModeVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-09-04
     */
    int updateByPrimaryKeySelective(DistributionModeVo record);

    /**
     *
     * @mbggenerated 2017-09-04
     */
    int updateByPrimaryKey(DistributionModeVo record);
}