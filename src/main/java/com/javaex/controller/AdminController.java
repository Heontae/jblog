package com.javaex.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.CategoryVo;

@Controller
@RequestMapping("/{id}/admin")
public class AdminController {
	@Autowired
	BlogService blogService;

	// 기본설정 페이지
	@RequestMapping("/basic")
	public String basic(Model model, @PathVariable("id") String id) {
		model.addAttribute("blogVo", blogService.select(id));

		return "blog/admin/blog-admin-basic";
	}

	@RequestMapping("/basicUpdate")
	public String basicUpdate(@PathVariable("id") String id, @RequestParam("blogTitle") String blogTitle,
			@RequestParam("file") MultipartFile file) {

		blogService.basicUpdate(id, blogTitle, file);

		return "redirect:/" + id;
	}

	// 카테고리 설정
	@RequestMapping("/category")
	public String category(Model model, @PathVariable("id") String id) {
		model.addAttribute("blogVo", blogService.select(id));

		model.addAttribute("cateList", blogService.categoryList(id));

		return "blog/admin/blog-admin-cate";
	}

	// 카테고리 리스트
	@ResponseBody
	@RequestMapping("/cateList")
	public List<CategoryVo> cateList(@PathVariable("id") String id) {

		List<CategoryVo> cateList = blogService.categoryList(id);
		System.out.println(cateList.toString());
		return cateList;
	}

	// 글작성
	@RequestMapping("/write")
	public String writeForm(Model model, @PathVariable("id") String id) {
		model.addAttribute("blogVo", blogService.select(id));

		return "blog/admin/blog-admin-write";
	}
}
