package com.zeus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zeus.dto.BoardDTO;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@GetMapping(value="/home")
	public String home() {
		return "board/home";
	}
	
	@GetMapping(value="/ajaxhome")
	public String ajaxhome() {
		return "board/ajaxhome";
	}
	
	@PostMapping(value="/insert")
	public String insert() {
		return "board/insert";
	}
	
	@RequestMapping(value="/select", method = RequestMethod.GET)
	public String select1(Model model) {
		model.addAttribute("boardNo","해당된 번호가 없음");
		return "board/select";
	}
	
	
	@RequestMapping(value="/select/{boardNo}", method = RequestMethod.GET)
	public String select2(@PathVariable("boardNo") int boardNo, Model model) {
		model.addAttribute("boardNo",boardNo);
		return "board/select";
	}

	@GetMapping(value="/select", params = "register")
	public String select3(Model model) {
		model.addAttribute("boardNo","register=값없음");
		return "board/select";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(Model model) {
		return "board/delete";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String update1(Model model) {
		return "board/update";
	}
	
	@PutMapping(value="/update/{boardNo}")
	public ResponseEntity<String> update2(@PathVariable("boardNo") int boardNo ,@RequestBody BoardDTO boardDTO) {
		log.info("boardDTO=" + boardDTO);
		ResponseEntity<String> message = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return message;
	}
	
	@PutMapping(value="/update/{boardNo}", headers = "X-HTTP-Method-Override=PUT")
	public ResponseEntity<String> update3(@PathVariable("boardNo") int boardNo ,@RequestBody BoardDTO boardDTO) {
		log.info("headers boardDTO=" + boardDTO);
		ResponseEntity<String> message = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return message;
	}
	
	
	
	
	

}
