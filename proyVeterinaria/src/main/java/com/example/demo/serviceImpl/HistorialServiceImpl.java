package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Historial;
import com.example.demo.repository.HistorialRepository;
import com.example.demo.service.HistorialService;

@Service
public class HistorialServiceImpl implements HistorialService{

	@Autowired
	private HistorialRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listHistoriales() {

		Map<String, Object> respuesta = new HashMap<>();
		List<Historial> historiales = dao.findAll();
		
		if(!historiales.isEmpty()) {
			
			respuesta.put("mensaje", "Lista de historiales");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("Historiales", historiales);
			
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}
		else {
			
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

}
