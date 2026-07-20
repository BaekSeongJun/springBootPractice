package com.zeus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zeus.mapper")
public class SpringBootInterceptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootInterceptorApplication.class, args);
	}

}
