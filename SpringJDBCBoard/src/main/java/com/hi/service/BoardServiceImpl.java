package com.hi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hi.domain.Board;
import com.hi.dto.BoardDTO;
import com.hi.repository.BoardRepository;

//데이터베이스에 요청하는부분(비지니스 로직)
//BoardDTO => Board
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Override
	public boolean insert(BoardDTO boardDTO) throws Exception {
		if(boardDTO == null || boardDTO.getTitle().isBlank()){
			return false;
		}
		Board board = new Board();
		board.setTitle(boardDTO.getTitle());
		board.setWriter(boardDTO.getWriter());
		board.setContent(boardDTO.getContent());

		return boardRepository.insert(board);
	}

	@Override
	public BoardDTO select(BoardDTO boardDTO) throws Exception {
		if(boardDTO == null || boardDTO.getBoardNo() <= 0){
			return null;
		}
		Board board = new Board();
		board.setBoardNo(boardDTO.getBoardNo());
		board = boardRepository.select(board);
		boardDTO.setBoardNo(board.getBoardNo());
		boardDTO.setContent(board.getContent());
		boardDTO.setTitle(board.getTitle());
		boardDTO.setWriter(board.getWriter());
		boardDTO.setRegDate(board.getRegDate());
		return boardDTO;
	}

	@Override
	public boolean update(BoardDTO boardDTO) throws Exception {
		if(boardDTO == null || boardDTO.getBoardNo() <= 0){
			return false;
		}
		Board board = new Board();
		board.setBoardNo(boardDTO.getBoardNo());
		board.setTitle(boardDTO.getTitle());
		board.setWriter(boardDTO.getWriter());
		board.setContent(boardDTO.getContent());
		boardRepository.update(board);
		return true;
	}

	@Override
	public boolean delete(BoardDTO boardDTO) throws Exception {
		if(boardDTO == null || boardDTO.getBoardNo() <= 0){
			return false;
		}
		Board board = new Board();
		board.setBoardNo(boardDTO.getBoardNo());
		return boardRepository.delete(board);
	}

	@Override
	public List<BoardDTO> list() throws Exception {
		List<Board> list = boardRepository.list();
		if(list.size() <= 0){
			return null;
		}
		List<BoardDTO> list2 = new ArrayList<BoardDTO>();
		for(Board board : list){
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setBoardNo(board.getBoardNo());
			boardDTO.setTitle(board.getTitle());
			boardDTO.setContent(board.getContent());
			boardDTO.setWriter(board.getWriter());
			boardDTO.setRegDate(board.getRegDate());
			list2.add(boardDTO);
		}
		return list2;
	}
}
