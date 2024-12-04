package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Mascota;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.service.MascotaService;


@Service
public class MascotaServiceImpl implements MascotaService{
	
	@Autowired
	private MascotaRepository dao;
	
	@Override
	public ResponseEntity<Map<String, Object>> listMascotas(){
		Map<String, Object> respuesta = new HashMap<>();
		List<Mascota> mascotas = dao.findAll();
		
		if (!mascotas.isEmpty()) {
			respuesta.put("mensaje", "Lista de Mascotas");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("Mascotas", mascotas);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

}
