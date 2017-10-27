package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.AutocallscoredemoVo;

public interface AutocallscoredemoVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(AutocallscoredemoVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(AutocallscoredemoVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    AutocallscoredemoVo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(AutocallscoredemoVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(AutocallscoredemoVo record);
}