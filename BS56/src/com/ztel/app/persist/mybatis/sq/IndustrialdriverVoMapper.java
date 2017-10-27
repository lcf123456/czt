package com.ztel.app.persist.mybatis.sq;

import com.ztel.app.vo.sq.CigfactoryVo;
import com.ztel.app.vo.sq.IndustrialdriverVo;

import com.ztel.framework.vo.Pagination;

import java.math.BigDecimal;
import java.util.List;

public interface IndustrialdriverVoMapper {
    /**
     *
     * @mbggenerated 2017-04-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insert(IndustrialdriverVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int insertSelective(IndustrialdriverVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    IndustrialdriverVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKeySelective(IndustrialdriverVo record);

    /**
     *
     * @mbggenerated 2017-04-18
     */
    int updateByPrimaryKey(IndustrialdriverVo record);

    public	List<IndustrialdriverVo> getIndustrialdriverPageList(Integer factoryid);
    public	List<CigfactoryVo> getIndustrialdriverList();
    List<IndustrialdriverVo> getIndustrialdriverPageList(Pagination<?> page);

	
}