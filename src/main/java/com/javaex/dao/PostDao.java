package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	SqlSession sqlSession;

	public int insert(PostVo postVo){
		return sqlSession.insert("post.insert" ,postVo);
	}
	
	public List<PostVo> postList(int cateNo){
		
		return sqlSession.selectList("post.List", cateNo);
	}
	
	public PostVo postUser(int postNo){
		
		return sqlSession.selectOne("post.User", postNo);
	}
}
