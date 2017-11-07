package com.ztel.app.persist.mybatis.wms;

import java.util.List;

import com.ztel.app.vo.wms.ItemVo;
import com.ztel.framework.vo.Pagination;

public interface ItemVoMapper {
	
	List<ItemVo> selectConfiscationListByCond(ItemVo itemVo);
	
	List<ItemVo> selectListByCond(ItemVo itemVo);
    /**
     *
     * @mbggenerated 2017-08-08
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2017-08-08
     */
    int insert(ItemVo record);

    /**
     *
     * @mbggenerated 2017-08-08
     */
    int insertSelective(ItemVo record);

    /**
     *
     * @mbggenerated 2017-08-08
     */
    ItemVo selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2017-08-08
     */
    int updateByPrimaryKeySelective(ItemVo record);

    /**
     *
     * @mbggenerated 2017-08-08
     */
    int updateByPrimaryKey(ItemVo record);

	List<ItemVo> selectBrandinfoPageList(Pagination<?> page);

	List<ItemVo> selectIteminfoPageList(Pagination<?> page);

	int deleteByPrimaryKey(Integer id);
}