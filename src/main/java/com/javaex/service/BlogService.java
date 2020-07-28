package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;

	
	public BlogVo select(String id) {
		return blogDao.selectBlog(id);

	}
	
	//카테고리 리스트
	public List<CategoryVo> categoryList(String id) {
	
		return categoryDao.categoryList(id);
		
	}
	//카테고리 추가
	public CategoryVo categoryAdd(CategoryVo cateVo) {
		//카테고리 추가!
		categoryDao.insert(cateVo);
		int cateNo = cateVo.getCateNo();
		
		//직전에 추가한 카테고리 1개 가져오기
		return categoryDao.selectByNo(cateNo);
	}

	//카테고리 삭제
	public int cateDelete(String id , int cateNo) {
		CategoryVo cateVo = new CategoryVo(id,cateNo);
		
		return categoryDao.delete(cateVo);
	}
	
	
	
	//post 추가
	public int postInsert(PostVo postVo) {
		return postDao.insert(postVo);
	}
	//post 리스트
	public Map<String,Object> postList(String id,int crtNo , int postNo){
		
		Map<String, Object> map = new HashMap<String,Object>();
		List<CategoryVo> cateList = categoryDao.categoryList(id);
		
		if(crtNo==0 && cateList.size() != 0 ) {
			crtNo = cateList.get(0).getCateNo();
			List<PostVo> pList = postDao.postList(crtNo);
			map.put("postList", pList);
			
			if(pList.size() != 0) {
				postNo = pList.get(0).getPostNo();
				PostVo vo =postDao.postUser(postNo);
				map.put("postVo", vo);
			}
		}else {
			List<PostVo> pList = postDao.postList(crtNo);
			map.put("postList", pList);
			
			if(postNo == 0 && pList.size() != 0 ) {
				postNo = pList.get(0).getPostNo();
			}
			
			PostVo vo = postDao.postUser(postNo);
			map.put("postVo", vo);
			
		}
		
		return map;
	}
	

	//기본정보 변경
	public void basicUpdate(String id,String blogTitle, MultipartFile file) {
		BlogVo blogVo;
		if(!file.getOriginalFilename().equals("")) {
			// ======데이터 추출======
			String saveDir = "C:\\JavaStudy\\upload";
	
			// 원본파일 이름추출
			String orgName = file.getOriginalFilename();
	
			// 확장자
			String exName = orgName.substring(orgName.lastIndexOf("."));
	
			// 저장파일이름
			String logoFile = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
	
			// 파일경로
			String filePath = saveDir + "\\" + logoFile;
	
			// ======파일 복사======
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);
	
				bout.write(fileData);
				bout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			blogVo = new BlogVo(id,blogTitle,logoFile);
			
		}
		else {
			String logoFile = blogDao.selectBlog(id).getLogoFile();
			blogVo = new BlogVo(id,blogTitle,logoFile);
			
		}
		blogDao.blogUpdate(blogVo);
	}

}
