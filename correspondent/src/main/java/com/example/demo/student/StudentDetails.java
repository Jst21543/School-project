package com.example.demo.student;

import com.example.demo.fees.Fees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetails {
	private Student student;
	private Fees fees;

}
