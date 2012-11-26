package com.jyams.secure.manager.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class LoginPageEntryPoint implements AuthenticationEntryPoint, InitializingBean {

	@Autowired
	private LoginPageStrategy loginPageStrategy;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException arg2) throws IOException, ServletException {
		loginPageStrategy.process(request, response);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(loginPageStrategy, "loginPageStrategy must be  specified");
	}

	public void setLoginPageStrategy(LoginPageStrategy loginPageStrategy) {
		this.loginPageStrategy = loginPageStrategy;
	}

}
