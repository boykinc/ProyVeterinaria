package com.example.demo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface MascotaService {
	
	public ResponseEntity<Map<String, Object>> listMascotas();

}
