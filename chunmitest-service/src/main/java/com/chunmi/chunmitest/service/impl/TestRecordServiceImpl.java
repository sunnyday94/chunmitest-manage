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
 * File Created @ [2017年6月15日, 下午4:02:08 (CST)]
 */
package com.chunmi.chunmitest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chunmi.chunmitest.mapper.TestRecordMapper;
import com.chunmi.chunmitest.po.TestRecord;
import com.chunmi.chunmitest.po.TestSetting;
import com.chunmi.chunmitest.service.TestRecordService;
import com.chunmi.chunmitest.service.TestSettingService;
import com.chunmi.chunmitest.vo.TestRecordVo;

@Service("testRecordService")
public class TestRecordServiceImpl implements TestRecordService {
	
	@Autowired
	private TestRecordMapper testRecordMapper;
	
	@Resource(name="testSettingService")
	private TestSettingService testSettingService;

	@Override
	public List<TestRecordVo> getTestRecordListBySettingId(Long settingId) {
		return testRecordMapper.getTestRecordListBySettingId(settingId);
	}

	@Override
	public TestRecord selectByPrimaryKey(Long recordId) {
		return testRecordMapper.selectByPrimaryKey(recordId);
	}

	@Override
	public List<TestRecord> selectBySettingId(Long settingId) {
		return testRecordMapper.selectBySettingId(settingId);
	}

	@Override
	public List<TestRecord> selectBySettingIdAndRemark(TestSetting testSetting) {
		return testRecordMapper.selectBySettingIdAndRemark(testSetting);
	}

	@Override
	public int updateTestRecordBySettingIdAndRemark(TestSetting testSetting) {
		return testRecordMapper.updateTestRecordBySettingIdAndRemark(testSetting);
	}

	@Override
	public List<String> selectTestRecordsBySettingId(Long id) {
		return testRecordMapper.selectTestRecordsBySettingId(id);
	}

	@Override
	public List<String> selectRegressionsBySettingId(Long id) {
		return testRecordMapper.selectRegressionsBySettingId(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int regressionTest(TestSetting testSetting,String testType) {
 		String remark = "回归测试";
 		testSetting.setRemark(remark);
 		try {
 			List<TestRecord> testRecordList = selectBySettingIdAndRemark(testSetting);
 			if(testRecordList!=null &&testRecordList.size()>0){
 				//如果有回归测试记录，则清除上一次回归测试记录
 				updateTestRecordBySettingIdAndRemark(testSetting);
 			}
 			//单个接口回归测试
 			if(testType.equals("single")){
 	 	 		TestSetting ts = testSettingService.selectByPrimaryKey(testSetting.getId());
 	 	 		ts.setRemark(remark);
 	 	 		testSettingService.testResultMap(ts);
 	 	 	//所有接口回归测试
 			}else if(testType.equals("all")){
 				testSettingService.testResultMap(testSetting);
 			}
 			return 1;				
		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

}
