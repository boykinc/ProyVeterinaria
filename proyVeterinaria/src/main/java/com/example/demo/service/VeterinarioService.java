package com.example.demo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Veterinario;
import com.example.demo.entity.VeterinarioDTO;

public interface VeterinarioService {

	public ResponseEntity<Map<String , Object>> listVeterinarios();
	
	public ResponseEntity<Map<String, Object>> listarVeterinariosActivos();
	
	public ResponseEntity<Map<String, Object>> buscarVeterinarioPorId(Long id);
	
	public ResponseEntity<Map<String, Object>> agregarVeterinario(VeterinarioDTO veterinarioDTO);
	
	public ResponseEntity<Map<String, Object>> actualizarVeterinario(VeterinarioDTO veterinarioDTO, Long id);
	
	public ResponseEntity<Map<String, Object>> eliminarVeterinarioLogico(Long id);
}
