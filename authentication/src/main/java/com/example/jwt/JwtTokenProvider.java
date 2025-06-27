package com.example.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	public String generateToken(Authentication authentication) {
		String email=authentication.getName();
		Date currentime=new Date();
		Date expirsTime=new Date(currentime.getTime()+(30*60*1000));
		String token=Jwts.builder()
						.setSubject(email)
						.setIssuedAt(currentime)
						.setExpiration(expirsTime)
						.signWith(SignatureAlgorithm.HS512, "SecurityKey")
						.compact();
		return token;
	}
	public String getMail(String token) {
		Claims claims=Jwts.parser().setSigningKey("SecurityKey").parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey("SecurityKey").parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}
