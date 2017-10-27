package com.ztel.app.persist.mybatis.sys;

import com.ztel.app.vo.sys.InterfaceReportmenuVo;

public interface InterfaceReportmenuVoMapper {
    /**
     *
     * @mbggenerated 2017-04-12
     */
    int deleteByPrimaryKey(Short id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insert(InterfaceReportmenuVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insertSelective(InterfaceReportmenuVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    InterfaceReportmenuVo selectByPrimaryKey(Short id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKeySelective(InterfaceReportmenuVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKey(InterfaceReportmenuVo record);
}