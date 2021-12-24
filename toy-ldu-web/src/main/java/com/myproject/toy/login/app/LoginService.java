package com.myproject.toy.login.app;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {
	Map<String, String> processLogin(Map<String, String> user, HttpServletResponse response);
}
