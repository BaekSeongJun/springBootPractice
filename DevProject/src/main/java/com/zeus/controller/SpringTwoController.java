package com.zeus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeus.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SpringTwoController {

	@GetMapping(value = "/spring/method01")
	public String method01(){

		return "spring/method01";
	}

	@GetMapping(value = "/spring/method02")
	public void method02(){

	}

	@GetMapping(value = "/spring/method03")
	@ResponseBody
	public Member methood03(){
		Member member = new Member();
		return member;
	}

	@GetMapping(value = "/spring/method04")
	public ResponseEntity<Member> method04(){
		return new ResponseEntity<>(new Member(), HttpStatus.OK);
	}




}
