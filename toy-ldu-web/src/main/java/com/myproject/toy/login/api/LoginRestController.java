package com.myproject.toy.login.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.toy.login.app.LoginService;
import com.myproject.toy.login.app.UserRepository;
import com.myproject.toy.login.domain.User;
import com.myproject.toy.login.utils.JwtTokenProvider;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@Api(value = "LoginRestController", description = "로그인 REST 컨트롤러")
public class LoginRestController {
	
    private final LoginService loginService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody Map<String, String> user, HttpServletResponse response) {
    	Map<String, String> retObject = new HashMap<String, String>();
    	
    	retObject = loginService.processLogin(user, response);
        
        return ResponseEntity.ok(retObject);
    }
}
