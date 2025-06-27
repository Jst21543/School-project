package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.allEntitys.Principal;

@Service
public class PrincipalService {
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Principal> principals(String email){
		ResponseEntity<List<Principal>>entity=restTemplate.exchange("http://localhost:8004/principal/get/"+email,HttpMethod.GET,null, new ParameterizedTypeReference<List<Principal>>() {});
		return entity.getBody();
	}
}
