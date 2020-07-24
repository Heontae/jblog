package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;

	public BlogVo select(String id) {
		return blogDao.selectBlog(id);

	}

	public List<CategoryVo> categoryList(String id) {
		return categoryDao.categoryList(id);
	}

	public void basicUpdate(String id,String blogTitle, MultipartFile file) {
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
		BlogVo blogVo = new BlogVo(id,blogTitle,logoFile);
		blogDao.blogUpdate(blogVo);
		
	}

}
