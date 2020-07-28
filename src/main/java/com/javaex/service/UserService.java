package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		userDao.join(userVo);
		userDao.join_blog(userVo);
		userDao.join_category(userVo);
		return 1;
	}
	
	public UserVo login(UserVo userVo) {
	
		return userDao.login(userVo);
	}
	
	public boolean userIdCheck(String id) {
		UserVo userVo = userDao.userIdCheck(id);
		boolean result = true;
		if(userVo == null) {
			result = true;
		}
		else {
			result = false;
		}
		return result;
	}
}
