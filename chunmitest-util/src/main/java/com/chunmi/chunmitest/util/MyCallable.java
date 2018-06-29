/**
 * This class was created by wangzhifang. It's distributed as
 * part of the chunmitest-web Mod.
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
 * File Created @ [2017年6月12日, 下午6:44:24 (CST)]
 */
package com.chunmi.chunmitest.util;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chunmi.chunmitest.util.HttpUtils;

public class MyCallable implements Callable<String> {
	
	private String strUrl;
	private String requestParams;
	private String methodType;
	private String token;
	private String sign;
	
	
	/**
	 * 构造方法
	 * Creates a new instance of MyCallable.
	 * @param strUrl
	 * @param requestParams
	 * @param methodType
	 * @param token
	 * @param sign
	 */
	public MyCallable(String strUrl,String requestParams,String methodType,String token,String sign){
		this.strUrl = strUrl;
		this.requestParams = requestParams;
		this.methodType = methodType;
		this.token = token;
		this.sign = sign;
	}
	
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(MyCallable.class);

	@Override
	public String call() throws Exception {
		String result = "";
		try {
			result = HttpUtils.responseData(strUrl, requestParams, methodType, token, sign);
		} catch (Exception e) {
			logger.error("返回响应失败:{}", e.getMessage());
		}
		return result;
	}
	

	
	
}
