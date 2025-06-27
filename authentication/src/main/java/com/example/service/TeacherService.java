package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.allEntitys.Teacher;

@Service
public class TeacherService {
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Teacher> teachers(String email){
		ResponseEntity<List<Teacher>>entity=restTemplate.exchange("http://localhost:8002/teacher/get/"+email,HttpMethod.GET,null, new ParameterizedTypeReference<List<Teacher>>() {});
		return entity.getBody();
	}
}
