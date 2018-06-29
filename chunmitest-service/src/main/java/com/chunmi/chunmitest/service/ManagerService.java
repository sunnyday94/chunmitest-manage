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
 * File Created @ [2017年7月5日, 下午3:53:31 (CST)]
 */
package com.chunmi.chunmitest.service;

import com.chunmi.chunmitest.po.Manager;
import com.chunmi.chunmitest.vo.PageBean;

public interface ManagerService {

	/**
	 * 
	 * @description: <p class="detail">根据用户名查询用户信息</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月5日-下午3:57:16
	 * @param @param name
	 * @param @return
	 * @return Manager
	 */
	Manager selectManagerByName(String name);

	/**
	 * 
	 * @description: <p class="detail">更新用户信息</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月6日-上午11:02:30
	 * @param @param manager
	 * @param @return
	 * @return int
	 */
	int updateByPrimaryKeySelective(Manager manager);

	/**
	 * 
	 * @description: <p class="detail">分页查询管理员信息</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月6日-下午1:45:02
	 * @param @param pageBean
	 * @param @return
	 * @return PageBean<Manager>
	 */
	PageBean<Manager> selectManagerList(PageBean<Manager> pageBean);

	
	/**
	 * 
	 * @description: <p class="detail">新增管理员</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年7月6日-下午5:06:07
	 * @param @param manager
	 * @param @return
	 * @return int
	 */
	int insertSelective(Manager manager);


}
