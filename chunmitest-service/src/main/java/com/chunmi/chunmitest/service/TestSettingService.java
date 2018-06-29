/**
 * This class was created by wangzhifang. It's distributed as
 * part of the chunmitest-service Mod.
 *
 * 版权所有(C) 上海纯米电子科技有限公司 2014-2023
 * Copyright 2014-2023 CHUNMI TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * CHUNMI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CHUNMI.
 *
 * File Created @ [2017年5月31日, 上午9:58:38 (CST)]
 */
package com.chunmi.chunmitest.service;

import java.util.List;
import java.util.Map;

import com.chunmi.chunmitest.po.TestSetting;
import com.chunmi.chunmitest.vo.PageBean;

public interface TestSettingService {
	int deleteByPrimaryKey(Long id);

	int insert(TestSetting record);

	int insertSelective(TestSetting record);

	TestSetting selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TestSetting record);

	int updateByPrimaryKey(TestSetting record);

	int delSettingById(Map<String, Object> ids);

	List<TestSetting> selectAll();
	
	/**
	 * 
	 * @description: <p class="detail">生成测试结果(往record表中插入记录)</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月16日-上午10:14:28
	 * @param @param testSetting
	 * @param @return
	 * @return void
	 */
	void testResultMap(TestSetting testSetting);

	int selectConfigCounts();

	/**
	 * 
	 * @description: <p class="detail">分页查询接口配置信息</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月14日-下午4:00:17
	 * @param @param pageBean
	 * @param @return
	 * @return PageBean<TestSetting>
	 */
	PageBean<TestSetting> queryConfigByPage(PageBean<TestSetting> pageBean);
	
	PageBean<TestSetting> selectAllBySetting(TestSetting testSetting,PageBean<TestSetting> pageBean);

	/**
	 * 
	 * @description: <p class="detail">查询上一个主键id</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月16日-下午1:01:24
	 * @param @return
	 * @return Long
	 */
	Long selectLastId();

}
