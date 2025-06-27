package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Attendence;
@Repository
public interface AttendenceRepository extends JpaRepository<Attendence, Integer> {
	public List<Attendence> findByid(int id);
}
