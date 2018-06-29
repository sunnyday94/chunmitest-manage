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
 * File Created @ [2017年8月29日, 下午5:28:26 (CST)]
 */
package com.chunmi.chunmitest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chunmi.chunmitest.util.Constants;
import com.chunmi.chunmitest.vo.Page;

@Controller
public class LoginController {
	
	/**
	 * 
	 * @description: <p class="detail">登录成功后跳转到主页面</p>
	 * @author: <a href="mailto:wangzhifang@chunmi.com ">wangzhifang</a>
	 * @date: 2017年8月29日-下午5:30:17
	 * @param @return
	 * @return ModelAndView
	 */
	@RequestMapping(value="index")
	public ModelAndView goToMainPage(){
		ModelAndView mav = new ModelAndView(Constants.PAGE_MAIN);
		Page page = new Page();
		page.setPageName(null);
		return mav.addObject(Constants.PAGE,page);
	}

}
