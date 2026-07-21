package com.zeus.common.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {

		log.info("onAuthenticationSuccess");
		User customUser = (User)auth.getPrincipal();
		log.info("username = " + customUser.getUsername());
		super.onAuthenticationSuccess(request, response, auth);

	}
}
