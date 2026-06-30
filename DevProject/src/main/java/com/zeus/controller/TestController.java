package com.zeus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
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

import com.zeus.dto.Address;
import com.zeus.dto.BoardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {

	@GetMapping(value="/test/gohome1")
	@ResponseBody
	public ArrayList<Address> gohomeGet(Model model, BoardDTO bdo) {
		ArrayList<Address> address = bdo.getAddress();
		for(Address ad : address) {
			log.info("address = " + ad.toString());
		}
		return address;
	}
	@PostMapping(value="/test/gohome1")
	public String gohome(Model model , BoardDTO boardDTO) {
		log.info("boardDTO = " + boardDTO.toString());

		ArrayList<String> list =  boardDTO.getHobby();
		if(list != null) {
			for (String str : list) {
				log.info("hobby = " + str);
			}
		}

		return "test/gohome1";
	}

	@GetMapping("/test/posthome")
	public void posthome() {
	}


}
