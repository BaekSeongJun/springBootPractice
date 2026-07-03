package com.zeus.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Member {

	@NotBlank
	private String userId; 
	private String password;
	@NotBlank @Size(max=3)
	private String userName;
	@Email
	private String email;
	private String introduction;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private boolean foreigner;
	private List<String> hobbyArray;
	private List<String> hobbyValue;
	// private List<CodeLabelValue> hobbyList;
	private Map<String,String> hobbyMap;
}
