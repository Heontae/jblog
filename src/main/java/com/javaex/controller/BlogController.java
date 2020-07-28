package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BlogService;

@Controller
public class BlogController {
	@Autowired
	BlogService blogService;
	
	//개인블로그 페이지
	@RequestMapping("/{id}")
	public String main(Model model ,@PathVariable("id") String id,
			@RequestParam(value = "cateNo", defaultValue = "0") int cateNo,
			@RequestParam(value = "postNo", defaultValue = "0") int postNo) {
		//정보 (id,blogTitle,logoFile)
		model.addAttribute("blogVo", blogService.select(id));
		//카테고리 리스트
		model.addAttribute("cateList", blogService.categoryList(id));
		//post 리스트
		Map<String,Object> map = blogService.postList(id,cateNo,postNo);
		model.addAttribute("map",map );
		
		return "blog/blog-main";
	}
	
}
