package com.example.demo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface UsuarioService {

	public  ResponseEntity<Map<String , Object>> listUsuarios();
}
