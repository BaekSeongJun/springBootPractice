package com.zeus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zeus.dto.Address;
import com.zeus.dto.BoardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {


	@PostMapping(value="/test/gohome1")
	public String gohome(Model model ,  BoardDTO bd) {
		ArrayList<MultipartFile> pictures = bd.getPicture();
		for(MultipartFile picture : pictures) {
			log.info("picture original file name = " + picture.getOriginalFilename());
			log.info("picture size = " + picture.getSize());
			log.info("picture content type = " + picture.getContentType());
			picture.getResource();
		}
		return "test/gohome1";
	}

	@PostMapping(value="/test/gohome2")
	public ResponseEntity<String> gohome2(Model model, @RequestBody ArrayList<BoardDTO> bd) {

		for(BoardDTO b : bd) {
			log.info("b.getBoardNo() = " + b.getBoardNo());
			log.info("b.getTitle() = " + b.getTitle());
		}

		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}

	@PostMapping(value="/test/gohome3" ,produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> gohome3(MultipartFile file) {

		log.info("picture original file name = " + file.getOriginalFilename());
		log.info("picture size = " + file.getSize());
		log.info("picture content type = " + file.getContentType());

		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}

	@GetMapping("/test/posthome")
	public void posthome() {
	}

	@GetMapping("/test/ajaxhome3")
	public void ajaxhome3() {
	}

	@GetMapping("/test/ajaxhome4")
	public void ajaxhome4() {
	}

	@GetMapping(value="/board/ajaxhome2")
	public void ajaxhome() {
	}


}
