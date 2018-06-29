package com.chunmi.chunmitest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chunmi.chunmitest.po.TestSetting;
import com.chunmi.chunmitest.service.TestRecordService;
import com.chunmi.chunmitest.service.TestSettingService;
import com.chunmi.chunmitest.util.Constants;
import com.chunmi.chunmitest.vo.Page;
import com.chunmi.chunmitest.vo.PageBean;


@Controller
@RequestMapping(value="/setting")
public class TestSettingController {
	
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(TestSettingController.class);
	
	@Autowired
	private TestSettingService testSettingService;
	
	
	@Autowired
	private TestRecordService testRecordService;
	 
	
	/***
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">zhangqian</a>
	 * @date: 2017年5月31日-下午14:28:22
	 * @param @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "settinglist", method = RequestMethod.GET)
	public ModelAndView list(PageBean<TestSetting> pageBean,TestSetting testSetting) {
		ModelAndView mv = new ModelAndView(Constants.PAGE_MAIN);
		Page page = new Page();
		page.setPageName(Constants.PAGE_SETTINGLIST+".jsp");
		mv.addObject(Constants.PAGE, page);
		mv.addObject("pb", testSettingService.selectAllBySetting(testSetting, pageBean));
		mv.addObject("testSetting", testSetting);
		return mv;
	}


	/***
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">zhangqian</a>
	 * @date: 2017年5月31日-下午14:28:22
	 * @param @return
	 * @return ModelAndView
	 */
	@RequestMapping(value="delSetting",method = RequestMethod.POST)
	@ResponseBody
	public void delSetting(Long[] ids) {
		try {
			if (null != ids && ids.length > 0) {
				Map<String, Object> map = new HashMap<>();
				map.put("ids", ids);
				testSettingService.delSettingById(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/***
	 * 
	 * @description: <p class="detail">根据接口配置信息查询测试结果</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月5日-下午14:28:22
	 * @param @return
	 * @return Map<String,Object>
	 */
	@RequestMapping(value="showResultById",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> showResultById(@RequestParam(value="id")Long id) {
		List<String> testRecords = null;
		List<String> regressionRecords = null;
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			testRecords = testRecordService.selectTestRecordsBySettingId(id);
			regressionRecords = testRecordService.selectRegressionsBySettingId(id);
		} catch (Exception e) {
			logger.error("查询结果集失败:{}",e.getMessage());
		}
		if(testRecords!=null && testRecords.size()>0){
			map.put("results",testRecords);
		}
		if(regressionRecords!=null && regressionRecords.size()>0){
			map.put("regressions",regressionRecords );
		}
		return map;
		
	}

	/***
	 * 
	 * @description: <p class="detail">根据id查询接口配置信息</p>
	 * @author: <a href="mailto:zhangqian@chunmi.com ">zhangqian</a>
	 * @date: 2017年5月31日-下午14:28:22
	 * @param @return
	 * @return TestSetting 
	 */
 	@RequestMapping(value="showSettingById",method = RequestMethod.GET)
 	@ResponseBody
	public TestSetting toUpdateSetting(@RequestParam(value="id")Long id,HttpServletRequest request) { 
		 return testSettingService.selectByPrimaryKey(id);
  
	}
 	
 	/**
 	 * 
 	 * @description: <p class="detail">回归测试(单个接口)</p>
 	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
 	 * @date: 2017年7月4日-上午11:25:14
 	 * @param @param settingId
 	 * @param @return
 	 * @return int
 	 */
 	@RequestMapping(value="regressionTesting",method = RequestMethod.POST)
 	@ResponseBody
 	public int regressionTesting(TestSetting testSetting,String testType){
 		return testRecordService.regressionTest(testSetting,testType);
 	}
 	
 	
 	/**
 	 * 
 	 * @description: <p class="detail">一键回归测试(所有接口)</p>
 	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
 	 * @date: 2017年7月7日-下午2:20:58
 	 * @param @param testType
 	 * @param @return
 	 * @return String
 	 */
 	@RequestMapping(value="allRegressionTesting",method = RequestMethod.POST)
 	@ResponseBody
 	public String allRegressionTesting(String testType){
 		int record = 0;
 		List<TestSetting> testSettingList = testSettingService.selectAll();
 		if(testSettingList!=null && testSettingList.size()>0){
 			for(TestSetting ts : testSettingList){
 				testRecordService.regressionTest(ts, testType);
 				record++;
 			}
 		}
 		return (testSettingList.size()==record) ? "1" : "0";
 	}
		 
	 
}
