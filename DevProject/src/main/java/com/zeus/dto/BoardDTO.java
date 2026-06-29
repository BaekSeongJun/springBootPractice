package com.zeus.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class BoardDTO {
	private int boardNo;
	private String title;
	private String content;
	private String writer;
}
