package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.serviceImpl.EspecialidadServiceImpl;
import com.example.demo.serviceImpl.HistorialServiceImpl;
import com.example.demo.serviceImpl.UsuarioServiceImpl;
import com.example.demo.serviceImpl.VeterinarioServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthorityController {

	@Autowired
	private UsuarioServiceImpl service;
	
	@GetMapping("/usuarios")
	public ResponseEntity<Map<String, Object>> ListaUsuarios(){
		
		return service.listUsuarios();
	}
	
	@Autowired
	private HistorialServiceImpl histoService;
	
	@GetMapping("/historiales")
	public ResponseEntity<Map<String, Object>> ListaHistoriales(){
		
		return histoService.listHistoriales();
		
	}
	
	
	//Veterinario y especialidad
	
	@Autowired
	private VeterinarioServiceImpl veteService;
	
	@GetMapping("/veterinarios")
	public ResponseEntity<Map<String, Object>> ListaVeterinarios(){
		
		return veteService.listVeterinarios();
		
	}
	
	@Autowired
	private EspecialidadServiceImpl especialidadService;
	
	@GetMapping("/especialidades")
	public ResponseEntity<Map<String, Object>> ListaEspecialidades(){
		
		return especialidadService.listEspecialidades();
		
	}
	
}
