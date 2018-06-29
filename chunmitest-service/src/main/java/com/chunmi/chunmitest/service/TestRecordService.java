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
 * File Created @ [2017年6月15日, 下午4:00:40 (CST)]
 */
package com.chunmi.chunmitest.service;

import java.util.List;

import com.chunmi.chunmitest.po.TestRecord;
import com.chunmi.chunmitest.po.TestSetting;
import com.chunmi.chunmitest.vo.TestRecordVo;

public interface TestRecordService {
	/**
	 * 
	 * @description: <p class="detail">根据settingId查询测试记录</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月15日-下午4:01:29
	 * @param @param settingId
	 * @param @return
	 * @return List<TestRecordVo>
	 */
	List<TestRecordVo> getTestRecordListBySettingId(Long settingId);

	/**
	 * 
	 * @description: <p class="detail">根据主键查询测试记录</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月20日-下午12:10:26
	 * @param @param recordId
	 * @param @return
	 * @return TestRecord
	 */
	TestRecord selectByPrimaryKey(Long recordId);
	
	/**
	 * 
	 * @description: <p class="detail">根据settingId查询测试结果)</p>
	 * @author: <a href="mailto:zhangqian@chunmi.com ">zhangqian</a>
	 * @date: 2017年6月26日-上午午11:10:35
	 * @param @param Long
	 * @param @return
	 * @return List<TestRecord>
	 */
	List<TestRecord> selectBySettingId(Long settingId);

	/**
	 * 
	 * @description: <p class="detail">根据settingId和remark更新记录表中回归测试记录</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月4日-上午11:49:01
	 * @param @param testSetting
	 * @param @return
	 * @return int
	 */
	int updateTestRecordBySettingIdAndRemark(TestSetting testSetting);

	
	/**
	 * 
	 * @description: <p class="detail">根据settingId和remark查询记录表中回归测试记录</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月4日-上午11:58:52
	 * @param @param testSetting
	 * @param @return
	 * @return List<TestRecord>
	 */
	List<TestRecord> selectBySettingIdAndRemark(TestSetting testSetting);

	
	/**
	 * 
	 * @description: <p class="detail">根据settingId查询测试结果集</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月5日-下午1:44:03
	 * @param @param id
	 * @param @return
	 * @return List<String>
	 */
	List<String> selectTestRecordsBySettingId(Long id);

	
	/**
	 * 
	 * @description: <p class="detail">根据settingId查询回归测试结果集</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月5日-下午1:48:58
	 * @param @param id
	 * @param @return
	 * @return List<String>
	 */
	List<String> selectRegressionsBySettingId(Long id);

	/**
	 * 
	 * @description: <p class="detail">回归测试(单个接口)</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月7日-下午12:39:07
	 * @param @param testSetting
	 * @param @return
	 * @return int
	 */
	int  regressionTest(TestSetting testSetting,String testType);

	

}
