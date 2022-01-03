package com.myproject.toy.signup.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.toy.common.authority.enumer.AutorityEnumer;
import com.myproject.toy.signup.dao.SignupMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService{
	
	private final SignupMapper 	  signupMapper;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Map<String, String> processSignup(Map<String, String> user) {
		Map<String, String> ret = new HashMap<String, String>();
		Map<String, String> param = user;
		
		if(searchSameMail(user)) {
			ret.put("STATUS", "FAIL");
			ret.put("MESSAGE", "이미 가입된 이메일입니다.");
		}
		else {
			param.put("PASSWORD", passwordEncoder.encode(user.get("PASSWORD")));
			
			// 유저 추가
			signupMapper.insertUser(param);
			
			// 유저 권한 추가 default : USER
			param.put("ROLES", AutorityEnumer.USER.toString());
			signupMapper.insertUserRole(param);
			
			ret.put("STATUS", "SUCCESS");
			ret.put("MESSAGE", "회원가입에 성공했습니다.");
		}
		
		return ret;
	}

	@Override
	public boolean searchSameMail(Map<String, String> user) {
		boolean ret = false;
		
		List<HashMap<String, Object>> param = signupMapper.searchSameMail(user);
		
		if(param.size() != 0) {
			ret = true;
		}
		
		return ret;
	}
}
