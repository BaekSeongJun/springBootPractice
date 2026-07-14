package com.zeus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeus.domain.Member;
import com.zeus.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/insertForm")
	public String memberInsertForm(Member member, Model model) {
		model.addAttribute("member", member);
		return "member/insertForm";
	}

	@PostMapping("/insert")
	public String memberInsert(Member member ,Model model)throws Exception{
		boolean result = memberService.insert(member);
		model.addAttribute("method", "입력");
		if(result){
			model.addAttribute("msg","성공");
		}else{
			model.addAttribute("msg","실패");
		}
		return "member/success";
	}

	@GetMapping("/list")
	public String memberList(Member member, Model model)throws Exception{
		List<Member> list = memberService.list();
		model.addAttribute("list", list);
		return "member/list";
	}

	@GetMapping("/updateForm")
	public String memberUpdateForm(Member member, Model model)throws Exception{
		model.addAttribute("member", member);
		return "member/updateForm";
	}

	@PostMapping("/update")
	public String memberUpdate(Member member) throws Exception{
		memberService.update(member);
		return "redirect:/member/list";
	}

	@PostMapping("/delete")
	public String memberDelete(Member member, Model model) throws Exception{
		boolean result = memberService.delete(member);
		model.addAttribute("method", "삭제");
		if(result) {
			model.addAttribute("msg", "성공");
		}else{
			model.addAttribute("msg", "실패");
		}
		return "member/success";
	}

	@GetMapping("/select")
	public String memberSelect(Member member,Model model)throws Exception{
		member = memberService.select(member);
		model.addAttribute("member", member);
		return "member/select";
	}

}
