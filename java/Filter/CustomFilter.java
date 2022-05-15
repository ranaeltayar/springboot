package Filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
public class CustomFilter extends UsernamePasswordAuthenticationFilter{
    
	private AuthenticationManager authenticationmanager;
	
	
	public CustomFilter(AuthenticationManager authenticationmanager) {
		this.authenticationmanager = authenticationmanager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
		return authenticationmanager.authenticate(authenticationToken);
	
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		User user = (User)authentication.getPrincipal();
		Algorithm algorithm =Algorithm.HMAC256("secret".getBytes());
		String access_token =JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() 
						+10*60*1000)).withIssuer(request.getRequestURL().toString()).sign(algorithm);
		
		
		String refresh_token =JWT.create().withSubject(user.getPassword())
				.withExpiresAt(new Date(System.currentTimeMillis() 
						+30*60*1000)).withIssuer(request.getRequestURL().toString()).sign(algorithm);
		response.setHeader(access_token, access_token);
		response.setHeader(refresh_token, refresh_token);
	}
	}

	
	
	
