package com.example.demo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface EspecieService {
	
	public ResponseEntity<Map<String, Object>> listEspecies();
	
}
