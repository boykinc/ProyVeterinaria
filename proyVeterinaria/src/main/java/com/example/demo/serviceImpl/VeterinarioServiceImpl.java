package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Veterinario;
import com.example.demo.repository.VeterinarioRepository;
import com.example.demo.service.VeterinarioService;



@Service
public class VeterinarioServiceImpl implements VeterinarioService {

	@Autowired
	private VeterinarioRepository dao;
	
	
	@Override
	public ResponseEntity<Map<String, Object>> listVeterinarios() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Veterinario> veterinarios = dao.findAll();
		
		if (!veterinarios.isEmpty()) {
			respuesta.put("mensaje", "Lista de Veterinarios");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("Veterinarios", veterinarios);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}


	
}
