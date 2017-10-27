package com.ztel.app.persist.mybatis.perform;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.perform.UserlevelVo;

public interface UserlevelVoMapper {
	
	List<UserlevelVo> selectUserlevelList(@Param("parentid")Long parentid);
	
	/**
	 * 删除父id为userid的已经授权的所有用户，为重新授权做准备
	 * @param userid
	 * @return
	 */
	int deleteByUserid(Long userid);
	
	/**
	 * 删除用户本身及已经授权的所有用户
	 * @param userid
	 * @return
	 */
	int deleteAllByUserid(Long userid);
    /**
     *
     * @mbggenerated 2017-10-09
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-09
     */
    int insert(UserlevelVo record);

    /**
     *
     * @mbggenerated 2017-10-09
     */
    int insertSelective(UserlevelVo record);

    /**
     *
     * @mbggenerated 2017-10-09
     */
    UserlevelVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-10-09
     */
    int updateByPrimaryKeySelective(UserlevelVo record);

    /**
     *
     * @mbggenerated 2017-10-09
     */
    int updateByPrimaryKey(UserlevelVo record);
}