package com.jyams.secure.manager.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginPageStrategy {

	void process(HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException;
}
