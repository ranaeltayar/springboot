package com.Auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RequestMapping(value={"/login"})
@RestController
public class AuthController implements ErrorController{
	@Autowired
	private AuthService userservice;
	@GetMapping("/users")	
public List<User>getUsers(){
	return (userservice.getUsers());
	
}



}
