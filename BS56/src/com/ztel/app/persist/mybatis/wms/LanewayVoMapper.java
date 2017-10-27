package com.ztel.app.persist.mybatis.wms;

import java.util.List;

import com.ztel.app.vo.wms.LanewayVo;
import com.ztel.framework.vo.Pagination;

public interface LanewayVoMapper {
    /**
     *
     * @mbggenerated 2017-08-01
     */
    int deleteByPrimaryKey(String lanewayno);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insert(LanewayVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int insertSelective(LanewayVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    LanewayVo selectByPrimaryKey(String lanewayno);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKeySelective(LanewayVo record);

    /**
     *
     * @mbggenerated 2017-08-01
     */
    int updateByPrimaryKey(LanewayVo record);

	List<LanewayVo> selectLanewayPageList(Pagination<?> page);
}