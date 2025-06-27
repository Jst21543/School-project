package com.example.demo.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	private StudentInfo studentInfo;
	private List<MarksDetails> marksDetails;
	private Message message;

}
