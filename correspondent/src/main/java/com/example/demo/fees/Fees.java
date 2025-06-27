package com.example.demo.fees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fees {
	
	private int id;
	private String name;
	private int totalfee;
	private int paidamount;
	private int remaingBalance;
	private String status;
	
}
