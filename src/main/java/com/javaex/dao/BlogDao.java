package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	SqlSession sqlSession;
	
	public BlogVo selectBlog(String id) {
		return sqlSession.selectOne("blog.selectOne", id);
	}
	
	public int blogUpdate(BlogVo blogVo) {
		return sqlSession.update("blog.update",blogVo);
	}
}
