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
 * File Created @ [2017年7月5日, 下午3:06:23 (CST)]
 */
package com.chunmi.chunmitest.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chunmi.chunmitest.po.Manager;
import com.chunmi.chunmitest.util.Constants;

public class ManagerCheckFilter implements Filter{

	@Override
	public void destroy() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String requestPath = request.getServletPath();
		if(!requestPath.endsWith(".css")&&!requestPath.endsWith(".js")&&!requestPath.endsWith("check.do")&&!requestPath.endsWith("login.jsp")
				&&!requestPath.endsWith(".jpg")&&!requestPath.endsWith(".png")){
			Manager manager = (Manager) request.getSession().getAttribute(Constants.LOGIN_MANAGER);
			if(manager==null){
				//重定向到登录页面
				response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/view/login.jsp");
				return;
			}
		}
		chain.doFilter(req,res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		// TODO Auto-generated method stub
		
	}

}
