package com.Auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class AuthService implements UserDetailsService{
	@Autowired
	private AuthRepository authrepo;


	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return authrepo.findAll();
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user =authrepo.findByEmail(email);
			 if (user == null) {
		            throw new UsernameNotFoundException("user name not found");
		        }

		        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
		}
	}

