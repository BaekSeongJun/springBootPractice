package com.zeus.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Member {
	private String userId; 
	private String password; 
	private String userName; 
	private String email; 
	private LocalDate dateOfBirth; 
	private String[] hobbyArray;
	private List<String> hobbyList; 
}
