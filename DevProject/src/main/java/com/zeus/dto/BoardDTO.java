package com.zeus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	@Override
	public String toString() {
		return "BoardDTO [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ "]";
	}
}
