package com.ztel.app.persist.mybatis.sq;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sq.ComplaintVo;
import com.ztel.framework.vo.Pagination;

public interface ComplaintVoMapper {
	
	/**
	 * 获取投诉
	 * @return
	 */
	List<ComplaintVo> getComplaintPageList(Pagination<?> page);
	
	/**
	 * 投诉报表、返回type类型的数量和部门
	 * @param type
	 * @return
	 */
	int getComplaintReportCt(@Param("type") String type,@Param("deptid") String deptid,@Param("starttime") String starttime,@Param("endtime") String endtime);
	
	/**
	 * 投诉受理新增
	 * @param complaintVo
	 */
	//public void doNewComplaint(ComplaintVo complaintVo);
	
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(ComplaintVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(ComplaintVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    ComplaintVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(ComplaintVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(ComplaintVo record);
}