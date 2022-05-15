package com.Auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class AuthApplication {
	
	@Bean 
	PasswordEncoder passwordEncoder() 
	{ return new BCryptPasswordEncoder();}

	public static void main(String[] args) { 
		SpringApplication.run(AuthApplication.class, args);
	}

}
