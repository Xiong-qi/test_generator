package com.xbb.communication_gateway.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.xbb.communication_gateway.config.SessionConst;
import com.xbb.communication_gateway.kit.AjaxJson;
import com.xbb.communication_gateway.kit.WebContextKit;
import com.xbb.communication_gateway.model.User;



public class LoginJsonFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse)response;
		User sessionUser = (User) req.getSession().getAttribute(SessionConst.USER);
		if (null == sessionUser){
			WebContextKit.renderText(res, JSON.toJSONString(AjaxJson.sessionTimeOut()));
			return;
		}
		chain.doFilter(request, response);  
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
