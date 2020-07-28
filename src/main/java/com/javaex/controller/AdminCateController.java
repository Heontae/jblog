package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BlogService;
import com.javaex.vo.CategoryVo;

@Controller
@RequestMapping("/{id}/admin")
public class AdminCateController {
	
	@Autowired
	private BlogService blogService;

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
		
		return cateList;
	}

	// 카테고리 추가
	@ResponseBody
	@RequestMapping("/cateListAdd")
	public CategoryVo cateListAdd(@PathVariable("id") String id,
			@ModelAttribute CategoryVo cateVo) {
		cateVo.setId(id);
		
		return blogService.categoryAdd(cateVo);
	}

	// 카테고리 삭제
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(@PathVariable("id") String id, @RequestParam("cateNo") int cateNo ) {
		
		return blogService.cateDelete(id,cateNo);
	}

	// 포스트 수
	
}
