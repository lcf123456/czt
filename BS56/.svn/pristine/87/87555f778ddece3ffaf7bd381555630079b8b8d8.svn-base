package com.ztel.app.persist.mybatis.safe;

import java.util.List;

import com.ztel.app.vo.safe.HazardsLevelVo;
import com.ztel.app.vo.safe.HazardsVo;
import com.ztel.app.vo.safe.TypeinfoVo;
import com.ztel.framework.vo.Pagination;

public interface HazardsVoMapper {
    /**
     *
     * @mbggenerated 2017-10-18
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insert(HazardsVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int insertSelective(HazardsVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    HazardsVo selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKeySelective(HazardsVo record);

    /**
     *
     * @mbggenerated 2017-10-18
     */
    int updateByPrimaryKey(HazardsVo record);

	List<HazardsVo> getHazardsPageList(Pagination<?> page);

	List<TypeinfoVo> getHazardstypeList();

	List<HazardsVo> getHazardsAuditPageList(Pagination<?> page);

	List<HazardsLevelVo> getClevelList();

	List<HazardsVo> getHazardsControlPageList(Pagination<?> page);

	List<HazardsVo> getHazardsStatPageList(Pagination<?> page);

	
}