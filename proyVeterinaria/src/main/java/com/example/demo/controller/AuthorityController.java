package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.serviceImpl.UsuarioServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthorityController {

	@Autowired
	private UsuarioServiceImpl service;
	
	@GetMapping("/usuarios")
	public ResponseEntity<Map<String, Object>> ListaUsuarios(){
		
		return service.listUsuarios();
	}
	
}
