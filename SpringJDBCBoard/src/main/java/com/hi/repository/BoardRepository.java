package com.hi.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hi.domain.Board;

@Repository
public class BoardRepository {

	//jdbc 템플릿
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean insert(Board board) throws Exception {
		String query = "INSERT INTO board(boardNo, title, content, writer) VALUES (board_seq.nextval, ?, ?, ?)";
		int count = jdbcTemplate.update(query,board.getTitle(),board.getContent(),board.getWriter());

		return (count <= 0)?false:true;
	}

	public Board select(Board board) throws Exception {
		if(board.getBoardNo() <=0){
			return null;
		}
		String query = "SELECT * FROM board WHERE boardNo = ?";
		List<Board> result = jdbcTemplate.query(query, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board b = new Board();
				b.setBoardNo(rs.getInt("boardNo"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setWriter(rs.getString("writer"));
				b.setRegDate(rs.getTimestamp("regDate"));
				return b;
			}
		}, board.getBoardNo());

		return result.isEmpty()?null:result.get(0);
	}

	public boolean update(Board board) throws Exception {
		String query = "UPDATE board SET title = ?, content = ?, writer = ? WHERE boardNo = ?";
		int count = jdbcTemplate.update(query,board.getTitle(),board.getContent(),board.getWriter(),board.getBoardNo());
		return (count <= 0)?false:true;
	}


	public boolean delete(Board board) throws Exception {
		String query = "DELETE FROM board WHERE boardNo = ?";
		int count = jdbcTemplate.update(query,board.getBoardNo());
		return (count <= 0)?false:true;
	}

	public List<Board> list() throws Exception {
		String query = "SELECT * FROM board WHERE boardNo > 0 ORDER BY regDate DESC";
		List<Board> list = jdbcTemplate.query(query, new RowMapper<Board>(){
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				board.setBoardNo(rs.getInt("boardNo"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setRegDate(rs.getDate("regDate"));
				return board;
			}
		});
		return list;
	}
}
