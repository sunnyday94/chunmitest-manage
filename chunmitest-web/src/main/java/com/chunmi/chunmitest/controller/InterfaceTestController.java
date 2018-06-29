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
 * File Created @ [2017年6月14日, 上午11:27:15 (CST)]
 */
package com.chunmi.chunmitest.controller;
import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.chunmi.chunmitest.po.TestSetting;
import com.chunmi.chunmitest.service.TestRecordService;
import com.chunmi.chunmitest.service.TestSettingService;
import com.chunmi.chunmitest.util.Constants;
import com.chunmi.chunmitest.vo.Page;
import com.chunmi.chunmitest.vo.TestRecordVo;

@Controller
@RequestMapping(value="/setting")
public class InterfaceTestController {
	
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(InterfaceTestController.class);
	
	@Resource(name="testSettingService")
	private TestSettingService testSettingService;
	
	@Resource(name="testRecordService")
	private TestRecordService testRecordService;
	
	
	/**
	 * 
	 * @description: <p class="detail">接口参数配置页</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月14日-上午11:31:42
	 * @param testSetting
	 * @param @return
	 * @return ModelAndView
	 */
	@RequestMapping(value="/requestParamsConfig")
	public ModelAndView requestParamsConfig(TestSetting testSetting,String mark){
		ModelAndView mv = new ModelAndView(Constants.PAGE_MAIN);
		Page page = new Page();
		page.setPageName(Constants.PAGE_INTERFACETEST+".jsp");
		if(testSetting != null && testSetting.getId()!=null){
			testSetting = testSettingService.selectByPrimaryKey(testSetting.getId());
			/*
			if(testSetting.getRequestParams()!=null && !testSetting.getRequestParams().equals(""))
				//该行代码将请求参数中单引号和双引号转义
				testSetting.setRequestParams(testSetting.getRequestParams().replaceAll("\"","&quot;").replaceAll("'","&apos;"));
			if(testSetting.getExpectedResult()!=null && !testSetting.getExpectedResult().equals(""))
				//该行代码将期望结果中单引号和双引号转义
				testSetting.setRequestParams(testSetting.getRequestParams().replaceAll("\"","&quot;").replaceAll("'","&apos;"));
			*/
			mv.addObject("setting", testSetting);
		}
		return mv.addObject(Constants.PAGE, page).addObject("mark",mark);
	}
	
	
	
	/**
	 * 
	 * @description: <p class="detail">接口测试记录</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月15日-下午4:12:30
	 * @param @param testSetting
	 * @param @param model
	 * @param @return
	 * @return String
	 */
	@RequestMapping(value="/recordsearch",method=RequestMethod.POST)
	public String loadTestRecord(TestSetting testSetting,Model model,String requestJson,String mark){
		List<TestRecordVo> testRecordList = null;
		try {
			if(testSetting.getRequestUrl()!=null && !testSetting.getRequestUrl().isEmpty()
					&& !mark.equals("0")){
				if(requestJson!=null && !requestJson.isEmpty())
					testSetting.setRequestParams(requestJson.trim().replaceAll("&quot;","\"").replaceAll("&apos;","'"));
				//setting表插入记录
				testSettingService.insertSelective(testSetting);
				Long lastId = testSettingService.selectLastId();
				testSetting.setId(lastId);
				testSettingService.testResultMap(testSetting);
			}
			testRecordList = testRecordService.getTestRecordListBySettingId(testSetting.getId());
			model.addAttribute("testRecordList", testRecordList);
		} catch (Exception e) {
			logger.error("测试记录加载失败:{}",e.getMessage());
		}
		return Constants.PAGE_INTERFACETESTDATA;
	}
}
