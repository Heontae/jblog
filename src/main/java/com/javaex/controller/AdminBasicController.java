package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;

@Controller
@RequestMapping("/{id}/admin")
public class AdminBasicController {
	@Autowired
	BlogService blogService;

	// 기본설정 페이지
	@RequestMapping("/basic")
	public String basic(Model model, @PathVariable("id") String id) {
		model.addAttribute("blogVo", blogService.select(id));
		
		return "blog/admin/blog-admin-basic";
	}
	// 기본설정 변경하기
	@RequestMapping("/basicUpdate")
	public String basicUpdate(@PathVariable("id") String id, @RequestParam("blogTitle") String blogTitle,
			@RequestParam("file") MultipartFile file) {
		blogService.basicUpdate(id, blogTitle, file);

		return "redirect:/" + id;
	}
	
}
