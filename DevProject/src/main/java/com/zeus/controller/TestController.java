package com.zeus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeus.dto.BoardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {

	@PostMapping(value="/test/gohome1")
	public String gohome(Model model ,@ModelAttribute BoardDTO boardDTO, @RequestParam int coin) {
		log.info("boardDTO = " + boardDTO.toString());
		log.info("coin = " + coin);

		return "test/gohome1";
	}

	@GetMapping("/test/posthome")
	public void posthome() {
	}


}
