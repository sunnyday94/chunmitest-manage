package com.chunmi.chunmitest.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chunmi.chunmitest.po.TestSetting;
import com.chunmi.chunmitest.vo.PageRequest;

public interface TestSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TestSetting record);

    int insertSelective(TestSetting record);

    TestSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestSetting record);

    int updateByPrimaryKey(TestSetting record);
    
    int delSettingById(Map<String, Object> ids);
    
    List<TestSetting> selectAll();

	int selectConfigCounts();

	/**
	 * 
	 * @description: <p class="detail">分页查询接口配置</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月14日-下午4:10:27
	 * @param @param pageRequest
	 * @param @return
	 * @return List<TestSetting>
	 */
	List<TestSetting> selectAllByPage(@Param("pageRequest") PageRequest pageRequest);

	/**
	 * 
	 * @description: <p class="detail">查询上一个主键id</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年6月16日-下午1:02:16
	 * @param @return
	 * @return Long
	 */
	Long selectLastId();
	
	List<TestSetting> selectAllBySetting(@Param("setting") TestSetting setting,@Param("pageRequest") PageRequest pageRequest);
	
	Long selectCountBySetting(@Param("setting")TestSetting setting);
}
