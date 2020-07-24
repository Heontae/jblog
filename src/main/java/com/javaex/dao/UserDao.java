package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	SqlSession sqlsession;
	
	public int join(UserVo userVo) {
		
		return sqlsession.insert("user.insert",userVo);
	}
	public int join_blog(UserVo userVo) {
		return sqlsession.insert("user.insert_blog",userVo);
	}
	
	public int join_category(UserVo userVo) {
		return sqlsession.insert("user.insert_category",userVo);
	}
	
	public UserVo login(UserVo userVo) {
		return sqlsession.selectOne("user.selectUser", userVo);
	}
}
