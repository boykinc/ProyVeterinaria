package com.example.demo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface EspecialidadService {
	public  ResponseEntity<Map<String , Object>> listEspecialidades();
}
