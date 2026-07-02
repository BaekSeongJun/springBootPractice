package com.zeus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zeus.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	//문자열 한개 전송(String), 객체 전송(Member), List객체 배열전송
	@GetMapping(value="/member/model01")
	public String model01(Member member, RedirectAttributes rttr){
		// model.addAttribute("userId","123456");
		// ArrayList<Member> list = new ArrayList<Member>();
		// for(int i = 0 ; i < 3; i++) {
		// 	Member member = new Member();
		// 	member.setUserId("123456" + i);
		// 	member.setEmail("kdj@nate.com" + i);
		// 	member.setPassword("password" + i);
		// 	list.add(member);
		// }
		// model.addAttribute("list", list);
		log.info("member = " + member);
		rttr.addFlashAttribute("member", member);
		return "redirect:/member/result";
	}

	@GetMapping(value="/member/result")
	public String result() {
		log.info("result");
		return "member/result";
	}


}

