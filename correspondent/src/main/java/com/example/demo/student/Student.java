package com.example.demo.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	private int id;
	private String name;
	private String mothername;
	private String fathername;
	private long parentmobilenumber;
	private String address;
	private String standerd;
	private String email;

}
