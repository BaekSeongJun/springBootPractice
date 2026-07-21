package com.zeus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		log.info("메인화면입니다.");

		return "home";
	}

	///accessError
	@RequestMapping("/error/accessError")
	public String accessError() {
		log.info("접근권한위배");

		return "error/accessError";
	}

}