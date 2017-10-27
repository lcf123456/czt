/*
 * Copyright (c) 2017, All rights reserved.
 */
package com.ztel.app.service.sq;

import java.util.List;

import com.ztel.app.vo.sq.RoutescoreVo;
import com.ztel.framework.vo.Pagination;

/**
 * @author Sn
 * @since 2017年5月24日
 */
public interface RoutescoreVoService {
	
    List<RoutescoreVo> getRoutescorePageList(Pagination<?> page);
    
	public int delRoutescore(List<Integer> ids);

	public int viewRoutescore(RoutescoreVo routescoreVo);
	
	List<RoutescoreVo> getViewRoutescorePageList(Pagination<?> page);

	public int delRoutescoreline(List<Integer> ids);

	/**
     * 市场督察列表
     * @param page
     * @return
     */
    List<RoutescoreVo> getRoutescoreMarketPageList(Pagination<?> page);
    
    //取主表id
    public long getRouteScoreId();
  	//插入主表记录
    public void insertSummarySelective(RoutescoreVo routescoreVo);
  	//插入从表记录
    public void insertLineSelective(RoutescoreVo routescoreVo);
}
