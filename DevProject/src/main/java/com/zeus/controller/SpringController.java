package com.zeus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zeus.dto.CodeLabelValue;
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
		member.setPassword("123456");
		member.setUserName("홍길동");
		member.setEmail("zeus@nate.com");
		member.setIntroduction("안녕하세요 저는 홍길동입니다.");
		member.setForeigner(true);

		List<String> hobbyArray = new ArrayList<>();
		hobbyArray.add("Sports");
		hobbyArray.add("Music");
		hobbyArray.add("Movie");
		member.setHobbyArray(hobbyArray);

		//check박스
		// List<CodeLabelValue> hobbyList = new ArrayList<>();
		// hobbyList.add(new CodeLabelValue("01","농구"));
		// hobbyList.add(new CodeLabelValue("02","여행"));
		// hobbyList.add(new CodeLabelValue("03","등산"));
		Map<String,String> hobbyMap = new LinkedHashMap<>();
		hobbyMap.put("농구","01");
		hobbyMap.put("여행","02");
		hobbyMap.put("등산","03");
		member.setHobbyMap(hobbyMap);
		// model.addAttribute("hobbyList", hobbyList);
		model.addAttribute("member", member);
		return "spring/form1";
	}

	@PostMapping(value = "/spring/register")
	public String register(Model model,@Validated Member member, BindingResult result, RedirectAttributes rttr) {
		log.info("/spring/register");
		if (result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			List<ObjectError> globalErrors = result.getGlobalErrors();
			List<FieldError> fieldErrors = result.getFieldErrors();
			log.info("allErrors.size() = " + allErrors.size());
			log.info("globalErrors.size() = " + globalErrors.size());
			log.info("fieldErrors.size() = " + fieldErrors.size());
			for (int i = 0; i < allErrors.size(); i++)
			{ ObjectError objectError =
				allErrors.get(i); log.info("allError = " +
				objectError);
			}
			for (int i = 0; i < globalErrors.size(); i++)
			{ ObjectError objectError =
				globalErrors.get(i); log.info("globalError = "
				+ objectError);
			}

			for (int i = 0; i < fieldErrors.size(); i++)
			{ FieldError fieldError = fieldErrors.get(i);

				log.info("fieldError = " + fieldError);
				log.info("fieldError.getDefaultMessage() = " +
					fieldError.getDefaultMessage());
			}
			rttr.addFlashAttribute("error","입력값에 에러가 발생했습니다."); // 뷰 파일명
			return "redirect:/spring/form1";
		}
		log.info("member.getUserId() = " + member.getUserId());
		return "spring/success";
	}


	// @PostMapping(value = "/spring/register")
	// public ResponseEntity<Member> register(Model model,@Validated Member member) {
	// 	log.info("/spring/register");
	// 	return new ResponseEntity<>(member, HttpStatus.OK);
	// }
}
