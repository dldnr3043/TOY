package com.myproject.toy.login.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.toy.login.domain.User;
import com.myproject.toy.login.utils.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

	private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
	
	@Override
	public Map<String, String> processLogin(Map<String, String> user, HttpServletResponse response) {
		
	    Map<String, String> retObject = new HashMap<String, String>();
	
	    User member = userRepository.findByEmail(user.get("email"))
	    		.orElse(new User());
	        
        if(member.getEmail() == null) {
        	retObject.put("STATUS", "FAIL");
            retObject.put("MESSAGE", "가입되지 않은 E-MAIL 입니다.");
        }
        
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
        	retObject.put("STATUS", "FAIL");
            retObject.put("MESSAGE", "잘못된 비밀번호입니다.");
        }
        
        String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
        response.addHeader("X-AUTH-TOKEN", token);

        retObject.put("STATUS", "SUCCESS");
        retObject.put("EMAIL", member.getEmail());
        retObject.put("NAME", member.getUsername());
        retObject.put("ROLES", member.getRoles().toString());
	        
        return retObject;
	}

}
