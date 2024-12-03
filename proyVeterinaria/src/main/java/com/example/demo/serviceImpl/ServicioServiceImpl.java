package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Servicio;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.service.ServicioService;

public class ServicioServiceImpl implements ServicioService{

	@Autowired
	private ServicioRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listServicios() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Servicio> servicios = dao.findAll();
		
		if (!servicios.isEmpty()) {
			respuesta.put("mensaje", "Lista de Servicios");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("servicio", servicios);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros de servicios");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

}
