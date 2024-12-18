package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Especialidad;
import com.example.demo.repository.EspecialidadRepository;
import com.example.demo.service.EspecialidadService;

@Service
public class EspecialidadServiceImpl implements EspecialidadService{

	@Autowired
	private EspecialidadRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listEspecialidades() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Especialidad> especialidades = dao.findAll();
		
		if (!especialidades.isEmpty()) {
			respuesta.put("mensaje", "Lista de Especialidades");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("especialidades", especialidades);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

}
