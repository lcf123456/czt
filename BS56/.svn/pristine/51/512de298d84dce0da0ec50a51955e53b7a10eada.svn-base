package com.ztel.app.persist.mybatis.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ztel.app.vo.sys.PostInfoVo;

public interface PostInfoVoMapper {
    /**
     *
     * @mbggenerated 2017-04-12
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insert(PostInfoVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int insertSelective(PostInfoVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    PostInfoVo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKeySelective(PostInfoVo record);

    /**
     *
     * @mbggenerated 2017-04-12
     */
    int updateByPrimaryKey(PostInfoVo record);
    
    public List<PostInfoVo> getPostinfoList(@Param("superiorsid") Integer parentId,@Param("lvl") String postLevel);
}