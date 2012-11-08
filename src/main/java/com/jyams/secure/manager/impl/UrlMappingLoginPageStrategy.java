package com.jyams.secure.manager.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class UrlMappingLoginPageStrategy implements LoginPageStrategy {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String targetUrl = null;
		String uri = request.getRequestURI();
		if (uri.indexOf("admin") != -1) {
			targetUrl = "/admin/login.action";
		} else {
			targetUrl = "/loginSuccess.action";
		}
		targetUrl = request.getContextPath() + targetUrl;
		response.sendRedirect(targetUrl);
	}
}
