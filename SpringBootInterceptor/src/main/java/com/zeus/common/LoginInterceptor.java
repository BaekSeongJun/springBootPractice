package com.zeus.common;

import java.lang.reflect.Method;

import org.jspecify.annotations.Nullable;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("/login ~~ preHandle()");
		//경로 설정
		log.info("/login ~~ preHandle()" + request.getRequestURI());
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		//객체주소
		log.info("/login ~~ preHandle()" + method.getBean());
		//함수
		log.info("/login ~~ preHandle()" + methodObj);

		//세션이 존재유무 체크 : 세션(전역객체 MAP -> 키값 : VALUE객체)
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") != null){
			session.removeAttribute("userInfo");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
		log.info("/login ~~ postHandle()");
		log.info("/login ~~ postHandle()" + request.getRequestURI());
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		//객체주소
		log.info("/login ~~ postHandle()" + method.getBean());
		//함수
		log.info("/login ~~ postHandle()" + methodObj);
		HttpSession session = request.getSession();

		ModelMap modelMap = modelAndView.getModelMap();
		Object user = modelMap.get("user");

		if(user != null){
			log.info("/login ~~ postHandle()" + user);
			//세션에 사용자 정보를 등록
			session.setAttribute("userInfo",user);
			response.sendRedirect("/home");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
		log.info("/login ~~ afterCompletion()");
		log.info("/login ~~ afterCompletion()" + request.getRequestURI());
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		//객체주소
		log.info("/login ~~ afterCompletion()" + method.getBean());
		//함수
		log.info("/login ~~ afterCompletion()" + methodObj);
	}
}
