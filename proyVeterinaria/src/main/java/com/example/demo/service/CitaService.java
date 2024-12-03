package com.example.demo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface CitaService {

	public  ResponseEntity<Map<String , Object>> listCitas();
}
