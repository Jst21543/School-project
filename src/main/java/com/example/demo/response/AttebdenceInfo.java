package com.example.demo.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttebdenceInfo {
	private LocalDate localDate;
	private String Attendece;

}
