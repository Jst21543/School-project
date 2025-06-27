package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Validation;
@Repository
public interface ValidationRepository extends JpaRepository<Validation, java.lang.Integer> {

	Optional<Validation> findByEmail(String email);

}
