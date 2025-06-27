package com.example.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.service.UserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider provider;
	@Autowired
	private UserDetailsService detailsService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token=getToken(request);
		if(StringUtils.hasText(token)&& provider.validateToken(token)) {
			String email=provider.getMail(token);
			UserDetails userDetails=detailsService.loadUserByUsername(email);
			UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
		
	}
	private String getToken(HttpServletRequest request) {
		String token=request.getHeader("Authentication");
		if(StringUtils.hasText(token)&& token.startsWith("Bearer ")) {
			return token.substring(7, token.length());
		}
		return null;
	}

}
