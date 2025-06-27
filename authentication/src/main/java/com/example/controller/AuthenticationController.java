package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Validation;
import com.example.jwt.JwtTokenProvider;
import com.example.service.validationService;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
	@Autowired
	private validationService service;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@PostMapping("/registraion")
	public String reistration(@RequestBody Validation validation) {
		return service.register(validation); 
	}
	@PostMapping("/login")
	public String login(@RequestBody Validation validation) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(validation.getEmail(),validation.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(authentication);
		return tokenProvider.generateToken(authentication);
	}

}
