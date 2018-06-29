package com.chunmi.chunmitest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chunmi.chunmitest.po.TestRecord;
import com.chunmi.chunmitest.po.TestSetting;
import com.chunmi.chunmitest.vo.TestRecordVo;

public interface TestRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TestRecord record);

    int insertSelective(TestRecord record);

    TestRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestRecord record);

    int updateByPrimaryKey(TestRecord record);

    /**
     * 
     * @description: <p class="detail">根据settingId查询测试记录</p>
     * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
     * @date: 2017年6月15日-下午4:03:31
     * @param @param settingId
     * @param @return
     * @return List<TestRecordVo>
     */
	List<TestRecordVo> getTestRecordListBySettingId(@Param("settingId")Long settingId);

	
	/**
	 * 
	 * @description: <p class="detail">添加测试记录(集合)</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月16日-下午5:10:35
	 * @param @param recordList
	 * @param @return
	 * @return int
	 */
	int insertTestRecordList(@Param("recordList") List<TestRecord> recordList);
	
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
	 * @description: <p class="detail">根据settingId和remark查询记录表中的回归测试记录</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月4日-上午11:43:50
	 * @param @param testSetting
	 * @param @return
	 * @return List<TestRecord>
	 */
	List<TestRecord> selectBySettingIdAndRemark(TestSetting testSetting);

	
	/**
	 * 
	 * @description: <p class="detail">根据settingId和remark更新记录表中的回归测试记录</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月4日-下午12:00:09
	 * @param @param testSetting
	 * @param @return
	 * @return int
	 */
	int updateTestRecordBySettingIdAndRemark(TestSetting testSetting);

	
	/**
	 * 
	 * @description: <p class="detail">根据settingId查询测试结果集</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月5日-下午1:45:37
	 * @param @param id
	 * @param @return
	 * @return List<String>
	 */
	List<String> selectTestRecordsBySettingId(@Param("id")Long id);

	
	/**
	 * 
	 * @description: <p class="detail">根据settingId查询回归测试结果集</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月5日-下午1:50:28
	 * @param @param id
	 * @param @return
	 * @return List<String>
	 */
	List<String> selectRegressionsBySettingId(@Param("id")Long id);

	
}