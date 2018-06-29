package com.chunmi.chunmitest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chunmi.chunmitest.po.Manager;
import com.chunmi.chunmitest.vo.PageRequest;

public interface ManagerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    /**
     * 
     * @description: <p class="detail">根据用户名查询用户信息</p>
     * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
     * @date: 2017年7月5日-下午4:02:57
     * @param @param name
     * @param @return
     * @return Manager
     */
	Manager selectManagerByName(@Param("name")String name);

	/**
	 * 
	 * @description: <p class="detail">查询管理员总数</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月6日-下午1:47:03
	 * @param @return
	 * @return long
	 */
	long selectManagerCount();

	
	/**
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月6日-下午1:51:05
	 * @param @param pageRequest
	 * @param @return
	 * @return List<Manager>
	 */
	List<Manager> selectManagerList(PageRequest pageRequest);
}