package com.Authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class TokenUtil {
	@Value("${Users.app.jwtExpirationMs}")
	private long TOKEN_VALIDTY;
	@Value("${Users.app.jwtSecret}")
	private String TOKEN_SECRET;
	
	public String generateToken(UserDetails UserDetails) {
		
		Map<String,Object> claims=new HashMap<>();
		claims.put("sub", UserDetails.getUsername());
		claims.put("created", new Date());
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512,TOKEN_SECRET )
				.compact();
	}
	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis()+ TOKEN_VALIDTY * 1000);
	}
	  public String getUserNameFromToken(String token) {
	        try {
	            Claims claims = getClaims(token);

	            return claims.getSubject();
	        }catch (Exception ex) {
	            return null;
	        }
	    }


	    public boolean isTokenValid(String token, UserDetails userDetails) {
	        String username = getUserNameFromToken(token);

	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	    private boolean isTokenExpired(String token) {
	        Date expiration = getClaims(token).getExpiration();
	        return expiration.before(new Date());
	    }

	    private Claims getClaims(String token) {
	        Claims claims;
	        try {
	            claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
	                    .parseClaimsJws(token)
	                    .getBody();
	        }catch (Exception ex) {
	            claims = null;
	        }

	        return claims;
	    }


}
