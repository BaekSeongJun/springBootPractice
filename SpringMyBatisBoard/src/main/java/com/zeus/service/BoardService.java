package com.zeus.service;

import java.util.List;

import com.zeus.dto.BoardDTO;

public interface BoardService {
	//insert
	public boolean insert(BoardDTO boardDTO) throws Exception;

	//select
	public BoardDTO select(BoardDTO boardDTO) throws Exception;

	//update
	public boolean update(BoardDTO boardDTO) throws Exception;

	//delete
	public boolean delete(BoardDTO boardDTO) throws Exception;

	//list
	public List<BoardDTO> list() throws Exception;
}