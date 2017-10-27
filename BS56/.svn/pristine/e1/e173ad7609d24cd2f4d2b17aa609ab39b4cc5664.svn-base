package com.ztel.app.persist.mybatis.sys;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sys.MenuInfoVo;

/**
 * 
 * @author lcf
 * @since 2017年2月27日
 */
public interface MenuinfoVoMapper {
	
	public List<MenuInfoVo> getMenuinfoList(@Param("belongsysId") String belongsysId,@Param("parentId") String parentId,@Param("menuLevel") String menuLevel,@Param("menucode") String menucode);
	
	public List<BigDecimal> getMenuinfoListByRole(@Param("roleid")String roleid);
	
	public List<MenuInfoVo> getMenuinfoListForMain(@Param("belongsysId") String belongsysId,@Param("parentId") String parentId,@Param("menuLevel") String menuLevel,@Param("roleid") String roleid);

	
	/**
	 * 根据id获取菜单信息
	 * @param id
	 * @return
	 */
	public MenuInfoVo selectByPrimaryKey(@Param("id")String id);
	/**
	 * 菜单树，功能按钮使用
	 * @param parentId
	 * @return
	 */
	public List<MenuInfoVo> getMenuinfoListforTree(@Param("belongsysId") String belongsysId,@Param("parentId") String parentId);
    /**
    *
    * @mbggenerated 2017-04-12
    */
   MenuInfoVo selectByPrimaryKey(BigDecimal id);
   /**
   *
   * @mbggenerated 2017-04-12
   */
  int insert(MenuInfoVo record);

  /**
   *
   * @mbggenerated 2017-04-12
   */
  int insertSelective(MenuInfoVo record);

  /**
   *
   * @mbggenerated 2017-04-12
   */
  int updateByPrimaryKeySelective(MenuInfoVo record);

  /**
   *
   * @mbggenerated 2017-04-12
   */
  int updateByPrimaryKey(MenuInfoVo record);
  
  /**
  * 删除菜单，不是真正物理删除，只做删除标志
  * @mbggenerated 2017-04-12
  */
 int updateStatusByPrimaryKey(@Param("id")BigDecimal id);
}
