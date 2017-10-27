package com.ztel.app.persist.mybatis.perform;

import java.util.List;

import com.ztel.app.vo.perform.KeyworkVo;

public interface KeyworkVoMapper {
	
	List<KeyworkVo> selectListByCond(KeyworkVo record);
	
    /**
     *
     * @mbggenerated 2017-10-05
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-05
     */
    int insert(KeyworkVo record);

    /**
     *
     * @mbggenerated 2017-10-05
     */
    int insertSelective(KeyworkVo record);

    /**
     *
     * @mbggenerated 2017-10-05
     */
    KeyworkVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-10-05
     */
    int updateByPrimaryKeySelective(KeyworkVo record);

    /**
     *
     * @mbggenerated 2017-10-05
     */
    int updateByPrimaryKey(KeyworkVo record);
}