package com.myproject.toy.signup.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SignupMapper {
	void insertUser(Map<String, String> user);
	void insertUserRole(Map<String, String> user);
	List<HashMap<String, Object>> searchSameMail(Map<String, String> user);
}
