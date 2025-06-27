package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendence {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int attendenceid;
	private int id;
	private String name;
	private LocalDate localDate;
	private String attendence;

}
