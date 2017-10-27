package com.ztel.app.persist.mybatis.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sys.OperationlogVo;

public interface OperationlogVoMapper {
	
	public List<OperationlogVo> getOperationlogList(@Param("keyword") String keyword);
    /**
     *
     * @mbggenerated 2017-06-16
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-06-16
     */
    int insert(OperationlogVo record);

    /**
     *
     * @mbggenerated 2017-06-16
     */
    int insertSelective(OperationlogVo record);

    /**
     *
     * @mbggenerated 2017-06-16
     */
    OperationlogVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-06-16
     */
    int updateByPrimaryKeySelective(OperationlogVo record);

    /**
     *
     * @mbggenerated 2017-06-16
     */
    int updateByPrimaryKey(OperationlogVo record);
}