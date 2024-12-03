package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.repository.CitaRepository;
import com.example.demo.service.CitaService;
import com.example.demo.entity.Cita;

public class CitaServiceImpl implements CitaService {

	@Autowired
	private CitaRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listCitas() {
		
			
		Map<String, Object> respuesta = new HashMap<>();
		List<Cita> citas = dao.findAll();
		
		if (!citas.isEmpty()) {
			respuesta.put("mensaje", "Lista de Citas");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("cita", citas);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros de citas");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

}
