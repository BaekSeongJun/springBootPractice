package com.zeus.common.aop;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class ServiceLoggerAdvice {

	//target = 목표 com.zeus.service.BoardServiceImpl
	//joinpoint = 멤버함수(모든 함수를 대상으로 지정)  *(리턴값)target*(함수명)(..)(매개변수)
	// @Before(value = "execution(* com.zeus.service.BoardService*.*(..))")
	//advice
	// public void startLog(JoinPoint jp){
	// 	log.info("********** >> start log");
	// 	log.info("********** >> start log : " + jp.getSignature());
	// 	log.info("********** >> start log : " + Arrays.toString(jp.getArgs()));
	//
	// }

	// @AfterReturning(value = "execution(* com.zeus.service.BoardService*.*(..))", returning = "result")
	// public void logReturning(JoinPoint jp, Object result){
	// 	log.info("********** >> logReturning");
	// 	log.info("********** >> logReturning : " + jp.getSignature());
	// 	log.info("********** >> logReturning : " + result);
	// }

	// @AfterThrowing(value = "execution(* com.zeus.service.BoardService*.*(..))", throwing = "e")
	// public void logException(JoinPoint jp, Exception e){
	// 	Date date = new Date();
	// 	log.info("********** >> logException " + (date.toString()));
	// 	log.info("********** >> logException");
	// 	log.info("********** >> logException : " + jp.getSignature());
	// 	log.info("********** >> logException : " + e);
	// }

	// @After(value = "execution(* com.zeus.service.BoardService*.*(..))")
	// public void endLog(JoinPoint jp){
	// 	Date date = new Date();
	// 	log.info("********** >> end log " + (date.toString()));
	// 	log.info("********** >> eng log");
	// 	log.info("********** >> eng log : " + jp.getSignature());
	// }

	@Around("execution(* com.zeus.service.BoardService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		//1 시간을 설정
		long startTime = System.currentTimeMillis();

		//2.insert, select, update, delete, list, search... 함수를 실행
		log.info("********** >> time log " + Arrays.toString(pjp.getArgs()));
		Object result = pjp.proceed();

		//3. 종료시간 설정
		long stopTime = System.currentTimeMillis();
		log.info("********** >> time log " + pjp.getSignature().getName() + "=>" + (stopTime -startTime));
		return result;
	}
}
