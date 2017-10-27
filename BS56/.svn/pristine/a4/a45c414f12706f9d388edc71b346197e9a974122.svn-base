package com.ztel.app.persist.mybatis.sq;

import java.util.List;

import com.ztel.app.vo.sq.RoutescoreVo;
import com.ztel.framework.vo.Pagination;

public interface RoutescoreVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(RoutescoreVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(RoutescoreVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    RoutescoreVo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(RoutescoreVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(RoutescoreVo record);

	List<RoutescoreVo> getRoutescorePageList(Pagination<?> page);
	
	List<RoutescoreVo> getViewRoutescorePageList(Pagination<?> page);

	void deleteLineKey(Integer id);
	
	List<RoutescoreVo> getRoutescoreMarketPageList(Pagination<?> page);
	
	//取主表id
	long getRouteScoreId();
	//插入主表记录
	void insertSummarySelective(RoutescoreVo routescoreVo);
	//插入从表记录
	void insertLineSelective(RoutescoreVo routescoreVo);

}