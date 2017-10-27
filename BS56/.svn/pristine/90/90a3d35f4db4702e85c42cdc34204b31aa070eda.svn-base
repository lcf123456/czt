package com.ztel.app.persist.mybatis.sq;

import java.util.List;

import com.ztel.app.vo.sq.EvalitemVo;
import com.ztel.framework.vo.Pagination;

public interface EvalitemVoMapper {
	
	/**
	 * 取自动语音考核项列表
	 * @param page
	 * @return
	 */
	public List<EvalitemVo> getAutoItemPageList(Pagination<?> page);
	
	/**
	 * 根据条件查询考核项列表
	 * @param evalitemVo
	 * @return
	 */
	public List<EvalitemVo> getItemList(EvalitemVo evalitemVo);
	
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(EvalitemVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(EvalitemVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    EvalitemVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(EvalitemVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(EvalitemVo record);
}