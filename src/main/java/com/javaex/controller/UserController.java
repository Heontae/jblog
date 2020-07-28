package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//회원가입 폼
	@RequestMapping("/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	//회원가입
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
		return "user/joinSuccess";
	}
	
	//로그인 폼
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
		
	//로그인하기
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo , HttpSession session) {
		UserVo authVo = userService.login(userVo);
		
		if(authVo != null) {
			session.setAttribute("session", authVo);
			return "redirect:/";
		}
		else {
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	//로그아웃하기
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("session");
		session.invalidate();
		return "redirect:/";
	}
	//아이디체크(ajax)
	@ResponseBody
	@RequestMapping("/idCheck")
	public boolean UserIdCheck(@RequestParam("id")String id) {
		return userService.userIdCheck(id);
	}
}
