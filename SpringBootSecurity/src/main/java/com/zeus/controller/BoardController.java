package com.zeus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@RequestMapping("/board/list")
	public void list() {
		log.info("list : 모두가 접근 가능");
	}

	@RequestMapping("/board/insertForm")
	public void insertForm() {
		log.info("insertForm : 로그인한 회원만 접근 가능");
	}
}