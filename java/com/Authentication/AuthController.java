package com.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	 @PostMapping(value = {"/SignIn"})
public jwtResponse SignIn(@RequestBody SignInRequest SignInRequest ) {
	final Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(SignInRequest.getUsername(),SignInRequest.getPassword())
			);
	SecurityContextHolder.getContext().setAuthentication(authentication);
	UserDetails userDetails= userservice.loadUserByUsername(SignInRequest.getUsername());
	String token = tokenUtil.generateToken(userDetails);
	jwtResponse response = new jwtResponse(token);
	return response;
}

}
