/**
 * This class was created by wangzhifang. It's distributed as
 * part of the chunmitest-pojo Mod.
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
 * File Created @ [2017年6月16日, 下午1:35:21 (CST)]
 */
package com.chunmi.chunmitest.vo;

import com.chunmi.chunmitest.po.TestRecord;

public class TestRecordVo extends TestRecord {
	/**
	 * 期望结果
	 */
	private String expectedResult;

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
	
	
}
