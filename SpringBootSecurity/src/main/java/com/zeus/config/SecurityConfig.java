package com.zeus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.zeus.common.security.CustomAccessDeniedHandler;
import com.zeus.common.security.CustomLoginSuccessHandler;

import jakarta.servlet.DispatcherType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity // spring security 웹 보안 할성화 선언
public class SecurityConfig {

	@Bean // 자동으로 기본 시큐리티 설정이 수동 설정(커스텀 설정)으로 대체
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("시큐리티 환경설정");
		// 1. csrf 토큰 비활성화
		http.csrf(csrf -> csrf.disable());
		// 2-1. 접근제한정책(인증,인가)
		http.authorizeHttpRequests(auth -> auth.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
			.requestMatchers("/error/accessError", "/login", "/logout", "/css/**", "/js/**", "/error").permitAll()
			.requestMatchers("/board/list").permitAll() // 게시판 목록: 누구나
			.requestMatchers("/board/insertForm").hasRole("MEMBER") // 게시판 등록: 회원만
			.requestMatchers("/notice/list").permitAll() // 공지사항 목록: 누구나
			.requestMatchers("/notice/insertForm").hasRole("ADMIN") // 공지사항 등록: 관리자만
			.anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
		);
		// 2-2. 위배된 접근제한정책 처리  (포워딩(Forwarding)처리)
		//http.exceptionHandling(exception -> exception.accessDeniedPage("/error/accessError"));
		http.exceptionHandling(exception -> exception.accessDeniedHandler(createAccessDeniedHandler()));

		// 3. 기본 시큐리트가 가지고 있는 로그인폼을 활성화 => 사용자가 정의한 로그인폼으로 변경하겠다. (조심해야할것 4가지)
		http.formLogin(form -> form
			.loginPage("/login/insertForm") // 커스텀 로그인 페이지가 있다면 지정
			.loginProcessingUrl("/login") // Security 낙아채서 가져감 (POST), username, password
			// .defaultSuccessUrl("/", true) // 로그인 성공 시 이동할 기본 URL
			.successHandler(createAuthenticationSuccessHandler())
			.permitAll()
		);

		//4. 로그아웃 처리방식 (2가지 조심)
		// 로그아웃 처리를 위한 URI를 지정하고, 로그아웃한 후에 세션을 무효화 한다.
		http.logout(logout -> logout
			.logoutUrl("/logout")                // 로그아웃을 처리할 URL Post (기본값: /logout) sequrity가 낙아챔
			.logoutSuccessUrl("/")          // 로그아웃 성공 시 이동할 페이지
			.invalidateHttpSession(true)         // HTTP 세션 무효화 (기본값: true)
			.deleteCookies("JSESSIONID", "remember-me") // 로그아웃 시 관련 쿠키 삭제
			.permitAll()                         // 로그아웃 요청은 누구나 접근 가능해야 함
		);

		// 5.security 정책 설정활성화
		return http.build();
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 지정된 아이디와 패스워드로 로그인이 가능하도록 설정한다.
		auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER");
		auth.inMemoryAuthentication().withUser("manager").password("{noop}1234").roles("MANAGER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN","MANAGER","MEMBER");
	}

	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	// CustomLoginSuccessHandler를 빈으로 등록한다.
	@Bean
	public AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

}