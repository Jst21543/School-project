package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.allEntitys.Student;

@Service
public class StudentService {
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Student> students(String email){
		ResponseEntity<List<Student>>entity=restTemplate.exchange("http://localhost:8001/student/get/"+email,HttpMethod.GET,null, new ParameterizedTypeReference<List<Student>>() {});
		return entity.getBody();
	}
}
