package com.zeus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.zeus.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SpringController {

	@GetMapping(value="/spring/form1")
	public String springForm1(Model model){
		log.info("/spring/form1");
		Member member = new Member();
		member.setUserId("hong");
		member.setUserName("홍길동");
		member.setEmail("zeus@nate.com");
		model.addAttribute("member", member);
		return "spring/form1";
	}

	@PostMapping(value = "/spring/register")
	public ResponseEntity<Member> register(@ModelAttribute Member member) {
		log.info("member = " + member);
		return new ResponseEntity<>(member, HttpStatus.OK);
	}
}
