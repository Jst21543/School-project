package com.example.allEntitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
	private int id;
	private String name;
	private String subject;
	private String address;
	private long mobile;
	private String email;

}
