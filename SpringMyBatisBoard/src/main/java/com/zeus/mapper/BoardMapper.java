package com.zeus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zeus.domain.Board;

public interface BoardMapper {
	public int insert(Board board) throws Exception;
	public Board select(Board board) throws Exception;
	public int update(Board board) throws Exception;
	public int delete(Board board) throws Exception;
	public List<Board> list() throws Exception;

	public List<Board> search(@Param("title") String title) throws Exception;
}
