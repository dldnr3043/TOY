package com.myproject.toy.signup.app;

import java.util.Map;

public interface SignupService {
	Map<String, String> processSignup(Map<String, String> user);
	boolean searchSameMail(Map<String, String> user);
}
