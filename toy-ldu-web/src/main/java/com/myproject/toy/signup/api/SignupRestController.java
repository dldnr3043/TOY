package com.myproject.toy.signup.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.toy.signup.app.SignupService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@Api(value = "SignupRestController", description = "회원가입 REST 컨트롤러")
public class SignupRestController {
	
	private final SignupService signupService;
	
	// 로그인
    @PostMapping("/signup")
    public ResponseEntity<?> SignUp(@RequestBody Map<String, String> user) {
    	Map<String, String> retObject = new HashMap<String, String>();
    	
    	retObject = signupService.processSignup(user);
        
        return ResponseEntity.ok(retObject);
    }
}
