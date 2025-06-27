package com.example.allEntitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Principal {

	private String name;
	private String address;
	private long mobile;
	private String email;

}
