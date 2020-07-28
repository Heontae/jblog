package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
@RequestMapping("/{id}/admin")
public class AdminWriteController {
	@Autowired
	BlogService blogService;

	// blog 정보 가져오기
	@RequestMapping("/writeForm")
	public String writeForm(Model model, @PathVariable("id") String id) {
		model.addAttribute("blogVo", blogService.select(id));

		List<CategoryVo> cateList = blogService.categoryList(id);
		model.addAttribute("cateList", cateList);
		return "blog/admin/blog-admin-write";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute PostVo postVo) {
		blogService.postInsert(postVo);
		return "redirect://{id}/admin/writeForm";
	}
}
