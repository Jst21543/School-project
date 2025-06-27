package com.example.demo.mailResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MarksDetails {
	private String testtype;
	private int telugu;
	private int english;
	private int hindi;
	private int psysics;
	private int scocial;
	private int mathamatics;
	private int naturalscience;
	
}
