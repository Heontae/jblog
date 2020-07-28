package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	SqlSession sqlSession;

	public List<CategoryVo> categoryList(String id){
		return sqlSession.selectList("category.list" ,id);
	}
	
	//카테고리 한개 추가
	public int insert(CategoryVo cateVo) {
		return sqlSession.insert("category.insertSelectKey",cateVo);
	}
	//정보 하나 가져오기
	public CategoryVo selectByNo(int cateNo) {
		return sqlSession.selectOne("category.SelectByNo",cateNo);
	}
	//카테고리 삭제하기
	public int delete(CategoryVo cateVo) {
		return sqlSession.delete("category.delete",cateVo);
	}
}
