package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.IndustrialscoresumVo;

public interface IndustrialscoresumVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(IndustrialscoresumVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(IndustrialscoresumVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    IndustrialscoresumVo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(IndustrialscoresumVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(IndustrialscoresumVo record);
}