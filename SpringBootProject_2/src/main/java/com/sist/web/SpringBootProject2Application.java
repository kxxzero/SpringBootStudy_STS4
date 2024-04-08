package com.sist.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan(basePackages = {"com.sist.web.vo", "com.sist.web.dao", "com.sist.web.service", "com.sist.web.controller"})
@MapperScan(basePackages = "com.sist.web.dao")
public class SpringBootProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProject2Application.class, args);
	}

}
