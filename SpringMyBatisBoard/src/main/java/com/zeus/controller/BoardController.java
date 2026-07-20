package com.zeus.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zeus.dto.BoardDTO;
import com.zeus.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping(value = "/board/insertForm")
	public String boardInsertForm(Model model, BoardDTO boardDTO) {
		model.addAttribute("boardDTO", boardDTO);
		return "board/insertForm";
	}

	@PostMapping(value = "/board/insert")
	public String boardInsert(@Validated BoardDTO boardDTO, BindingResult bindingResult,  Model model, RedirectAttributes rttr) throws Exception{
		boolean result = boardService.insert(boardDTO);

		if(bindingResult.hasErrors()) {
			return "board/insertForm";
		}
		if(!result) {
			rttr.addFlashAttribute("msg","게시글 입력이 실패되었습니다.");
		}else{
			rttr.addFlashAttribute("msg","게시글 입력이 성공되었습니다.");
			rttr.addAttribute("writer", boardDTO.getWriter());
		}
		return "redirect:/board/list";
	}

	//3번 계시판 리스트 요청
	@GetMapping("/board/list")
	public String boardList(Model model)throws Exception{
		List<BoardDTO> list = boardService.list();
		model.addAttribute("list", list);
		return "board/list";
	}

	//게시글 요청
	@GetMapping("/board/select")
	public String boardSelect(BoardDTO boardDTO, Model model) throws Exception {
		if(boardDTO.getBoardNo() <= 0){
			return "board/fail";
		}
		boardDTO = boardService.select(boardDTO);
		if(boardDTO == null){
			return "board/fail";
		}
		model.addAttribute("boardDTO", boardDTO);
		return "board/select";
	}

	//게시글 삭제 요청
	@GetMapping("/board/delete")
	public String boardDelete(BoardDTO boardDTO, Model model) throws Exception {
		if(boardDTO.getBoardNo() <= 0){
			return "board/fail";
		}
		boolean result = boardService.delete(boardDTO);
		if(result == false){
			return "board/fail";
		}
		return "board/success";
	}

	@GetMapping(value = "/board/updateForm")
	public String boardUpdateForm(Model model, BoardDTO boardDTO) throws Exception {
		if(boardDTO.getBoardNo() <= 0){
			return "board/fail";
		}
		boardDTO = boardService.select(boardDTO);
		if(boardDTO == null) {
			return "board/fail";
		}
		model.addAttribute("boardDTO", boardDTO);
		return "board/updateForm";
	}

	//게시판 수정 요청
	@PostMapping(value = "/board/update")
	public String boardUpdate(BoardDTO boardDTO, Model model) throws Exception{
		if(boardDTO.getBoardNo() <= 0){
			return "board/fail";
		}
		boolean result = boardService.update(boardDTO);
		if(result){
			model.addAttribute("msg","성공!");
			model.addAttribute("boardDTO", boardDTO);
		}else{
			model.addAttribute("msg","실패ㅜㅜ");
		}
		return (result)?("board/select"):("board/fail");
	}

	@PostMapping(value = "/board/search")
	public String boardSearch(BoardDTO boardDTO, Model model) throws Exception{
		List<BoardDTO> list = boardService.search(boardDTO);
		model.addAttribute("list", list);
		return "board/list";
	}
}
