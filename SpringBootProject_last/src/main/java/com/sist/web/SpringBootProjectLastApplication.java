package com.sist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.sist.web.dao", "com.sist.web.controller", "com.sist.web.manager"})
@SpringBootApplication
public class SpringBootProjectLastApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectLastApplication.class, args);
	}

}
