package com.xbb.communication_gateway.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.xbb.communication_gateway.kit.AjaxJson;
import com.xbb.communication_gateway.kit.WebContextKit;




/**
 * 
* @ClassName: LoginFailureHandler 验证失败的处理方法
* @Description: TODO
* @author xubinbin 
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		WebContextKit.renderJson(response, AjaxJson.failure().setMsg("用户名或密码有误"));
	}

}