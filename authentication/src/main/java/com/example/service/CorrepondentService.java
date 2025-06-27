package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.allEntitys.Correspondent;

@Service 
public class CorrepondentService {
	@Autowired
	private RestTemplate restTemplate;
	public List<Correspondent>correspondents(String email){
		ResponseEntity<List<Correspondent>>entity=restTemplate.exchange("http://localhost:8003/correspondent/get/"+email,HttpMethod.GET,null, new ParameterizedTypeReference<List<Correspondent>>() {});
		return entity.getBody();
	}
}
