package com.zeus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	@GetMapping(value="/member/model01")
	public String model01(Model model){
		model.addAttribute("userId","123456");



		return "member/model01";
	}


}

