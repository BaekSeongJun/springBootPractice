package com.zeus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zeus.domain.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@GetMapping(value = "/home")
	public String home(Model model) {
		log.info("/home");
		return "home";
	}
	@GetMapping(value = "/login/insertForm")
	public String loginInsertForm() {
		log.info("/login/insertForm");

		return "login/insertForm";
	}

	@PostMapping(value = "/login/insert")
	public String loginInsert(Member member, Model model) {
		log.info("/login/insert");

		log.info("login userId = " + member.getUserId());
		log.info("login userPw = " + member.getUserPw());
		model.addAttribute("result", "로그인 되었습니다.");
		model.addAttribute("user", member);
		return "login/success";
	}
}
