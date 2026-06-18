package com.spring.security.oath.config.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// the server

@SpringBootApplication
public class SpringSecurityOath2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityOath2Application.class, args);
	}

}


/*

the flow is
http://localhost:8082/ui/

then after clicking it Login to OAuth here it takes to
http://localhost:8081/auth/login

after verifying

http://localhost:8082/ui/secure opens with succes


 */
