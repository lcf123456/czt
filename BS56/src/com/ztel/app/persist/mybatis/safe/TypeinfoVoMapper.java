package com.ztel.app.persist.mybatis.safe;

import java.util.List;

import com.ztel.app.vo.safe.TypeinfoVo;

public interface TypeinfoVoMapper {
    /**
     *
     * @mbggenerated 2017-10-12
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-12
     */
    int insert(TypeinfoVo record);

    /**
     *
     * @mbggenerated 2017-10-12
     */
    int insertSelective(TypeinfoVo record);

    /**
     *
     * @mbggenerated 2017-10-12
     */
    TypeinfoVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-12
     */
    int updateByPrimaryKeySelective(TypeinfoVo record);

    /**
     *
     * @mbggenerated 2017-10-12
     */
    int updateByPrimaryKey(TypeinfoVo record);


}