package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.fees.Fees;
import com.example.demo.model.Correspondent;
import com.example.demo.service.CorrespondentService;
import com.example.demo.student.Student;
import com.example.demo.student.StudentDetails;
import com.example.demo.vo.Response;

@RestController
@RequestMapping("/correspondent")
public class CorrespondentController {
	@Autowired
	private CorrespondentService service;
	
	@PostMapping("/post")
	public String save(@RequestBody Correspondent correspondent) {
		return service.save(correspondent);
	}
	@GetMapping("/get/{email}")
	public List<Correspondent> correspondents(@PathVariable String email){
		return service.correspondents(email);
	}
	@PostMapping("/postStudent")
	public String postStudent(@RequestBody StudentDetails details) {
		Student student=details.getStudent();
		Fees fees=details.getFees();
		fees.setId(student.getId());
		fees.setName(student.getName());
		fees.setTotalfee(service.setFees(student, fees));
		return service.postFeesAndStudent(student, fees);
	}
	@GetMapping("/getFees/{id}")
	public Fees getFees(@PathVariable int id) {
		return service.getFees(id);
	}
	@PostMapping("/postFees")
	public String postfees(@RequestBody Fees fees) {
		return service.postFees(fees);
	}
	
	@GetMapping("/getMarks/{id}")
	public Response getMarks(@PathVariable int id) {
		return service.getMarks(id);
		
	}

}
