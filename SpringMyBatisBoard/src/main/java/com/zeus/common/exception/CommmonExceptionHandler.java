package com.zeus.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CommmonExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		log.info("exceptionHandler" + e.toString());
		model.addAttribute("exception", e.getMessage());
		return "error/errorCommon";
	}
}
