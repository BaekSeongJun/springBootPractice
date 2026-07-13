package com.zeus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
                                                                                                                                                                                                                      import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeus.domain.Board;
import com.zeus.dto.BoardDTO;
import com.zeus.mapper.BoardMapper;

//데이터베이스에 요청하는부분(비지니스 로직)
//BoardDTO => Board
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	@Transactional
	public boolean insert(BoardDTO boardDTO) throws Exception {
		if (boardDTO == null || boardDTO.getTitle().isBlank()) {
			return false;
		}
		Board board = new Board();
		board.setTitle(boardDTO.getTitle());
		board.setWriter(boardDTO.getWriter());
		board.setContent(boardDTO.getContent());

		//insert into board values(~~~);
		int count = boardMapper.insert(board);
		return (count > 0) ? true : false;
	}

	@Override
	@Transactional(readOnly = true)
	public BoardDTO select(BoardDTO boardDTO) throws Exception {
		// return repository.getOne(boardNo);
		Board board = new Board();
		board.setBoardNo(boardDTO.getBoardNo());
		board = boardMapper.select(board);

		boardDTO.setBoardNo((board.getBoardNo()));
		boardDTO.setTitle(board.getTitle());
		boardDTO.setContent(board.getContent());
		boardDTO.setRegDate(board.getRegDate());
		boardDTO.setWriter(board.getWriter());

		return boardDTO;
	}

	@Override
	@Transactional
	public boolean update(BoardDTO boardDTO) throws Exception {

		Board board = new Board();
		board.setBoardNo(boardDTO.getBoardNo());
		board.setTitle(boardDTO.getTitle());
		board.setWriter(boardDTO.getWriter());
		board.setContent(boardDTO.getContent());

		int count = boardMapper.update(board);
		return (count > 0) ? true : false;
	}

	@Override
	@Transactional
	public boolean delete(BoardDTO boardDTO) throws Exception {
		Board board = new Board();
		board.setBoardNo(boardDTO.getBoardNo());
		int count = boardMapper.delete(board);
		return (count > 0) ? true : false;
	}

	@Override
	@Transactional(readOnly = true)
	public List<BoardDTO> list() throws Exception {
		List<Board> list = boardMapper.list();

		if(list.size() <= 0) {
			return null;
		}
		List<BoardDTO> list2 = new ArrayList<>();

		for (Board board : list) {
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setBoardNo(board.getBoardNo());
			boardDTO.setContent(board.getContent());
			boardDTO.setRegDate(board.getRegDate());
			boardDTO.setTitle(board.getTitle());
			boardDTO.setWriter(board.getWriter());
			list2.add(boardDTO);
		}
		return list2;
	}
}
