package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.allEntitys.Correspondent;
import com.example.allEntitys.Principal;
import com.example.allEntitys.Student;
import com.example.allEntitys.Teacher;
import com.example.entity.Validation;
import com.example.repository.ValidationRepository;

@Service
public class validationService {
	@Autowired
	private ValidationRepository repository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private PrincipalService principalService;
	@Autowired
	private CorrepondentService correpondentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	
	
	public String register(Validation validation) {
		validation.setPassword(encoder.encode(validation.getPassword()));
		List<Principal>principals=principalService.principals(validation.getEmail());
		List<Correspondent>correspondents=correpondentService.correspondents(validation.getEmail());
		List<Teacher>teachers=teacherService.teachers(validation.getEmail());
		List<Student>students=studentService.students(validation.getEmail());
		validation.setRole(principals.isEmpty()?(correspondents.isEmpty()?(teachers.isEmpty()?(students.isEmpty()? null:"STUDENT"):"TEACHER"):"CORRESPONDENT"):"PRINCIPAL");
		repository.save(validation);
		return "registration successfully";
	}

}
