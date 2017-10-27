package com.ztel.app.persist.mybatis.perform;

import com.ztel.app.vo.perform.UserperformlevelVo;
import com.ztel.framework.vo.Pagination;

import java.math.BigDecimal;
import java.util.List;

public interface UserperformlevelVoMapper {
	
	List<UserperformlevelVo> selectUserperformlevelPageList(Pagination<?> page);
	
	List<UserperformlevelVo> selectUserperformlevelListByCond(UserperformlevelVo userperformlevelVo);
	
    /**
     *
     * @mbggenerated 2017-10-16
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-16
     */
    int insert(UserperformlevelVo record);

    /**
     *
     * @mbggenerated 2017-10-16
     */
    int insertSelective(UserperformlevelVo record);

    /**
     *
     * @mbggenerated 2017-10-16
     */
    UserperformlevelVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-16
     */
    int updateByPrimaryKeySelective(UserperformlevelVo record);

    /**
     *
     * @mbggenerated 2017-10-16
     */
    int updateByPrimaryKey(UserperformlevelVo record);
}