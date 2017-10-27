package com.ztel.app.persist.mybatis.sys;

import com.ztel.app.vo.sys.OperationinfoVo;
import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OperationinfoVoMapper {
	
	public List<OperationinfoVo> getOperationinfoList(@Param("menucode") String menucode);
	/**
	 *  根据系统id获取功能点信息，用于角色功能点授权时授权页面弹出显示列表
	 * @param belongsys
	 * @return
	 */
	public List<OperationinfoVo> getOperationBySysid(@Param("belongsys") String belongsys);
    /**
     *
     * @mbggenerated 2017-05-10
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-05-10
     */
    int insert(OperationinfoVo record);

    /**
     *
     * @mbggenerated 2017-05-10
     */
    int insertSelective(OperationinfoVo record);

    /**
     *
     * @mbggenerated 2017-05-10
     */
    OperationinfoVo selectByPrimaryKey(BigDecimal id);

    /**
     *
     * @mbggenerated 2017-05-10
     */
    int updateByPrimaryKeySelective(OperationinfoVo record);

    /**
     *
     * @mbggenerated 2017-05-10
     */
    int updateByPrimaryKey(OperationinfoVo record);
}