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
 * File Created @ [2017年5月31日, 上午10:00:19 (CST)]
 */
package com.chunmi.chunmitest.service.impl;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.chunmi.chunmitest.mapper.TestRecordMapper;
import com.chunmi.chunmitest.mapper.TestSettingMapper;
import com.chunmi.chunmitest.po.TestRecord;
import com.chunmi.chunmitest.po.TestSetting;
import com.chunmi.chunmitest.service.TestSettingService;
import com.chunmi.chunmitest.util.MyCallable;
import com.chunmi.chunmitest.util.StringUtil;
import com.chunmi.chunmitest.vo.PageBean;
import com.chunmi.chunmitest.vo.PageRequest;

@Service("testSettingService")
public class TestSettingServiceImpl implements TestSettingService {
	
	@Autowired
	private TestSettingMapper testSettingMapper;
	
	@Autowired
	private TestRecordMapper testRecordMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return testSettingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TestSetting record) {
		return testSettingMapper.insert(record);
	}

	@Override
	public int insertSelective(TestSetting record) {
		return testSettingMapper.insertSelective(record);
	}

	@Override
	public TestSetting selectByPrimaryKey(Long id) {
		return testSettingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TestSetting record) {
		return testSettingMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TestSetting record) {
		return testSettingMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delSettingById(Map<String, Object> ids) {
		return testSettingMapper.delSettingById(ids);
	}

	@Override
	public List<TestSetting> selectAll() {
		return testSettingMapper.selectAll();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void testResultMap(TestSetting testSetting) {
		try {
			Long settingId = testSetting.getId();
			String url = testSetting.getRequestUrl();  //url
		    String methodType = testSetting.getRequestMode();
			if(methodType!=null && !methodType.isEmpty()){
				methodType = methodType.equals("0") ? "GET" : "POST"; //请求方式
			}
			Long concurrentNum = testSetting.getConcurrentNum()==null ? 1 : testSetting.getConcurrentNum();    //并发数
			String token = testSetting.getToken();  //请求头参数(token)
			String requestParams = testSetting.getRequestParams(); //请求参数
			String signType = testSetting.getSignType();   //sign生成规则
			String sign = null;   //请求头参数(sign)
			if(testSetting.getIsNeedSign().equals("1")){
				switch (signType) {
				case "0":
					sign = StringUtil.createSignDefault(requestParams); 
					break;
				default:
					break;
				}
			}
			String expectedResult = testSetting.getExpectedResult().trim(); //期望结果
			String remark = testSetting.getRemark();                //备注 
			TestRecord record = new TestRecord();
			String compareResult = null;     //比较结果
			if(url !=null && methodType!=null){
				for(long i =0;i<concurrentNum;i++){
					FutureTask<String> future = new FutureTask<String>(new MyCallable(url, requestParams, methodType, token, sign));
					new Thread(future).start();
					String testResult = future.get();
					compareResult = expectedResult.equals(testResult) ? "1" : "0";
					record.setSettingId(settingId);
					record.setTestResult(testResult);
					record.setCompareResult(compareResult);
					record.setRemark(remark);
					testRecordMapper.insertSelective(record);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public int selectConfigCounts() {
		return testSettingMapper.selectConfigCounts();
	}

	@Override
	public PageBean<TestSetting> queryConfigByPage(
			PageBean<TestSetting> pageBean) {
		int totalRecord = testSettingMapper.selectConfigCounts();
		PageRequest pageRequest = new PageRequest(pageBean.getStartRowNum(), pageBean.getEndRowNum());
		List<TestSetting> testSettingList = testSettingMapper.selectAllByPage(pageRequest);
		pageBean.setList(testSettingList);
		pageBean.setRows(totalRecord);
		return pageBean;
	}

	@Override
	public Long selectLastId() {
		return testSettingMapper.selectLastId();
	}
	
	public PageBean<TestSetting> selectAllBySetting(TestSetting testSetting, PageBean<TestSetting> pageBean) {
		Long total = testSettingMapper.selectCountBySetting(testSetting);
		PageRequest pageRequest = new PageRequest(pageBean.getStartRowNum(), pageBean.getEndRowNum());
		List<TestSetting> list = testSettingMapper.selectAllBySetting(testSetting, pageRequest);
		pageBean.setList(list);
		pageBean.setRows(total.intValue());
		return pageBean;
		
	}
	
	
	
}
