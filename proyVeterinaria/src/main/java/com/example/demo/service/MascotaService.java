package com.example.demo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Mascota;

public interface MascotaService {
	

	public ResponseEntity<Map<String, Object>> listMascotas();
	
	public ResponseEntity<Map<String, Object>> listaMascotaPorId(Long id);
	
	public ResponseEntity<Map<String, Object>> agregaMascota(Mascota mascota);
	
	public ResponseEntity<Map<String, Object>> actualizaMascota(Mascota mascota , Long id);
	
	
	public  ResponseEntity<Map<String, Object>> eliminarMascota(Long id);
	
	public  ResponseEntity<Map<String, Object>> eliminarLogicoMascota(Long id);

}
