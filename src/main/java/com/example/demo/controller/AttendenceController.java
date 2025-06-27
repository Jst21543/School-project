package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Attendence;
import com.example.demo.response.Response;
import com.example.demo.service.AttendenceService;

@RestController
@RequestMapping("/attendence")
public class AttendenceController {
	@Autowired
	private AttendenceService service;
	
	@PostMapping("/post")
	public String save(@RequestBody Attendence attendence) {
		return service.save(attendence);
	}
	@GetMapping("/get/{id}")
	public Response get(@PathVariable int id) {
		return service.get(id);
	}
	@PostMapping("/Attendences")
	public String saveAll(@RequestBody List<Attendence> attendences) {
		return service.saveAll(attendences);
	}

}
