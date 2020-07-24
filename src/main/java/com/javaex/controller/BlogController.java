package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	@Autowired
	BlogService blogService;
	
	//개인블로그 페이지
	@RequestMapping("/{id}")
	public String main(Model model ,@PathVariable("id") String id) {
		//정보 (id,blogTitle,logoFile)
		model.addAttribute("blogVo", blogService.select(id));
		//카테고리 리스트
		model.addAttribute("cateList", blogService.categoryList(id));
		return "blog/blog-main";
	}
	
}
