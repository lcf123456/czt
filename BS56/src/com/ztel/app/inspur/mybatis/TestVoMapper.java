package com.ztel.app.inspur.mybatis;

import java.util.List;

import com.ztel.app.inspur.vo.TestVo;

public interface TestVoMapper {
	
	List<TestVo> getList();
    /**
     *
     * @mbggenerated 2017-10-24
     */
    int insert(TestVo record);

    /**
     *
     * @mbggenerated 2017-10-24
     */
    int insertSelective(TestVo record);
}