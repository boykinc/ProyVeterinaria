package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.VeterinarioDTO;
import com.example.demo.serviceImpl.VeterinarioServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/veterinario")
public class VeterinarioController {

	@Autowired
	private VeterinarioServiceImpl service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> ListaVeterinarios(){
		
		return service.listVeterinarios();
		
	}
	
	@GetMapping("/estado")
	public ResponseEntity<Map<String, Object>> listarPorEstado(){
		
		return service.listarVeterinariosActivos();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listarPorId(@PathVariable Long id){
		
		return service.buscarVeterinarioPorId(id);
		
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> agregar(@RequestBody VeterinarioDTO veterinarioDTO) {
		
		return service.agregarVeterinario(veterinarioDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editar(@RequestBody VeterinarioDTO veterinarioDTO, @PathVariable Long id) {
		
		return service.actualizarVeterinario(veterinarioDTO, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminarLogico(@PathVariable Long id) {
		
		return service.eliminarVeterinarioLogico(id);
	}
	
	@DeleteMapping("/recuperar/{id}")
	public ResponseEntity<Map<String, Object>> recuperarVeterinario(@PathVariable Long id) {
		
		return service.recuperarVeterinario(id);
	}
	
}
