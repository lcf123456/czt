package com.ztel.app.persist.mybatis.sys;

import java.util.List;

import com.ztel.app.vo.sys.SysparameterVo;
import com.ztel.framework.vo.Pagination;

public interface SysparameterVoMapper {
    /**
     *
     * @mbggenerated 2017-04-12
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insert(SysparameterVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insertSelective(SysparameterVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    SysparameterVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKeySelective(SysparameterVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKey(SysparameterVo record);
    
    
    List<SysparameterVo> getSysparameterPageList(Pagination<?> page);
}