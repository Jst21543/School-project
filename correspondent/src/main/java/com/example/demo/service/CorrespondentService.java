package com.example.demo.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.fees.Fees;
import com.example.demo.mailResponse.Mail;
import com.example.demo.mailResponse.MarksDetails;
import com.example.demo.model.Correspondent;
import com.example.demo.repository.CorrespondentRepository;
import com.example.demo.student.Student;
import com.example.demo.vo.Message;
import com.example.demo.vo.Response;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class CorrespondentService {
	@Autowired
	private CorrespondentRepository repo;
	@Autowired
	private RestTemplate restTemplate;
	
	public static final String feesService="FEES-SERVICE";
	public static final String marksService="MARKS-SERVICE";
	
	
	public String save(Correspondent correspondent) {
		repo.save(correspondent);
		return "correspondent saved";
	}
	public List<Correspondent> correspondents(String email){
		return repo.findByEmail(email);
	}
		
	public String postFeesAndStudent(Student student,Fees fees) {
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Student> entity1= new HttpEntity<>(student, headers);
		HttpEntity<Fees> entity2= new HttpEntity<>(fees, headers);
		restTemplate.exchange("http://localhost:8000/student/post",HttpMethod.POST,entity1, String.class);
		restTemplate.exchange("http://localhost:8000/fees/post",HttpMethod.POST,entity2, String.class);
		return "details saved";
	}
	@Retry(name=feesService ,fallbackMethod = "getFessFallback")
	public Fees getFees(int id) {
		ResponseEntity<Fees> fees=restTemplate.getForEntity("http://localhost:8000/fees/getFees/"+id, Fees.class);
		Fees fee=fees.getBody();
		fee.setRemaingBalance(fee.getTotalfee()-fee.getPaidamount());
		fee.setStatus((fee.getPaidamount()>=fee.getTotalfee())? "fee payment completed" : (fee.getPaidamount()>=fee.getTotalfee()) ? "half amount payed" : (fee.getPaidamount()<fee.getTotalfee()) ? "below half amount payed":"did'nt make any payment");
		return fees.getBody();
	}
	public Fees getFessFallback(Exception exception) {
		Fees fees=new Fees();
		fees.setStatus("fees service is under maintainence");
		return fees;
	}
	
	@Retry(name=feesService, fallbackMethod = "postFeesFallback")
	public String postFees(Fees fees) {
		Fees old=getFees(fees.getId());
		old.setPaidamount(old.getPaidamount()+fees.getPaidamount());
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Fees> entity1= new HttpEntity<>(old, headers);
		restTemplate.exchange("http://localhost:8000/fees/post",HttpMethod.POST,entity1, String.class);
		return "fees payment updated";
	}
	public String postFeesFallback(Exception e) {
		return "fees service is under maintainence";
	}
	@Retry(name = marksService, fallbackMethod = "getMarksFallback")
	public Response getMarks(int id) {
		ResponseEntity<Response> responseEntity=restTemplate.getForEntity("http://localhost:8000/marks/getMarks/"+id, Response.class);
		Response response=responseEntity.getBody();
		response.setMessage(new Message("success"));
		return response;
	}
	public Response getMarksFallback(Exception e) {
		Response response=new Response();
		response.setMessage(new Message("not getting"));
		return response;
	}
	
	public String sendMail(String standerd) {
		RequestEntity<Student> request = RequestEntity
			     .post(URI.create("http://localhost:8000/Student/getStudents"+standerd))
			     .accept(MediaType.APPLICATION_JSON)
			     .body(new Student());
		ParameterizedTypeReference<List<Student>> myBean =
			     new ParameterizedTypeReference<List<Student>>() {};
		ResponseEntity<List<Student>> entity =restTemplate.exchange(request,myBean);
		List<Student> students=entity.getBody();
		for(int i=0;i<students.size();i++) {
			Student s=students.get(i);
			ResponseEntity<Response> responseEntity=restTemplate.getForEntity("http://localhost:8000/marks/getMarks"+s.getId(), Response.class);
			Response response=responseEntity.getBody();
			List<com.example.demo.vo.MarksDetails> details=response.getMarksDetails();
			com.example.demo.vo.MarksDetails marks=details.stream().reduce((first,second)->second).orElse(null);
			com.example.demo.mailResponse.Student student=new com.example.demo.mailResponse.Student(s.getId(),s.getName(),s.getMothername(),s.getFathername());
			MarksDetails marksDetails=new MarksDetails(marks.getTesttype(),marks.getTelugu(),marks.getEnglish(),marks.getHindi(),marks.getPsysics(),marks.getScocial(),marks.getMathamatics(),marks.getNaturalscience());
			com.example.demo.mailResponse.Response response2=new com.example.demo.mailResponse.Response(student,marksDetails);
			Mail mail=new Mail();
			mail.setBody(response2.toString());
			mail.setTo(s.getEmail());
			mail.setSubject("Student marks");
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Mail>httpEntity= new HttpEntity<>(mail,headers);
			restTemplate.exchange("http://localhost:8000/mail/postMail", HttpMethod.POST, httpEntity, String.class);
		}
		return "marks are sended to all parents";
	}
	
	public int setFees(Student student,Fees fees) {
		String standerd=student.getStanderd();
		switch (standerd) {
		case "1st": {
			fees.setTotalfee(1000);
			break;
		}
		case "2nd":{
			fees.setTotalfee(1500);
			break;
		}
		case "3rd":{
			fees.setTotalfee(2000);
			break;
		}
		case "4th":{
			fees.setTotalfee(2500);
			break;
		}
		case "5th":{
			fees.setTotalfee(3000);
			break;
		}
		case "6th":{
			fees.setTotalfee(3500);
			break;
		}
		case "7th":{
			fees.setTotalfee(4000);
			break;
		}
		case "8th":{
			fees.setTotalfee(4500);
			break;
		}
		case "9th":{
			fees.setTotalfee(5000);
			break;
		}
		case "10th":{
			fees.setTotalfee(5500);
			break;
		}
		}
		return fees.getTotalfee();
		
	}

}
