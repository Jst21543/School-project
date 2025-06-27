package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Attendence;
import com.example.demo.repository.AttendenceRepository;
import com.example.demo.response.AttebdenceInfo;
import com.example.demo.response.Response;
import com.example.demo.response.Student;

@Service
public class AttendenceService {
	@Autowired
	private AttendenceRepository repo;
	
	public String save(Attendence attendence) {
		attendence.setLocalDate(LocalDate.now());
		repo.save(attendence);
		return "attendence saved";
	}
	public Response get(int id) {
		List<Attendence> attendences=repo.findByid(id);
		Response response=new Response();
		Student student=new Student(attendences.get(0).getId(),attendences.get(0).getName());
		List<AttebdenceInfo>attebdenceInfos=new ArrayList<>();
		for(Attendence attendence:attendences) {
			AttebdenceInfo attebdenceInfo= new AttebdenceInfo(attendence.getLocalDate(),attendence.getAttendence());
			attebdenceInfos.add(attebdenceInfo);
		}
		response.setStudent(student);
		response.setAttendence(attebdenceInfos);
		return response;
	}
	public String saveAll(List<Attendence> attendences) {
		repo.saveAll(attendences);
		return "Attendence saved";
	}

}
