package com.example.jwt;

import lombok.Getter;

@Getter
public class Jwt {
	
	private String token;
	private final String tokentype="Bearer";
	Jwt(String token){
		this.token=token;
	}

}
