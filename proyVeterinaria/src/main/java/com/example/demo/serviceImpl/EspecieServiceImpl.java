package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Especie;
import com.example.demo.repository.EspecieRepository;
import com.example.demo.service.EspecieService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EspecieServiceImpl implements EspecieService{
	
	@Autowired
	private EspecieRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listEspecies() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Especie> especies = dao.findAll();
		
		if (!especies.isEmpty()) {
			respuesta.put("mensaje", "Lista de Especies");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("Especies", especies);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen Registros");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

}
