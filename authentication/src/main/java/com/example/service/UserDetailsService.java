package com.example.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.entity.Validation;
import com.example.repository.ValidationRepository;
@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	@Autowired
	private ValidationRepository repository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println(email);
		Optional<Validation> validation=repository.findByEmail(email);
		System.out.println(validation);
		Set<String>roles=new HashSet<>();
		roles.add(validation.get().getRole());
		return new User(validation.get().getEmail(), validation.get().getPassword(), authorities(roles));
	}
	private Collection<? extends GrantedAuthority>authorities(Set<String>roles){
		return roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}

}
