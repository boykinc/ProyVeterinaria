package com.example.demo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Mascota;
import com.example.demo.entity.MascotaDTO;

public interface MascotaService {
	

	public ResponseEntity<Map<String, Object>> listMascotas();
	
	public ResponseEntity<Map<String, Object>> listaMascotaPorId(Long id);
	
	public ResponseEntity<Map<String, Object>> agregaMascota(MascotaDTO mascotaDTO);
	
	public ResponseEntity<Map<String, Object>> actualizaMascota(MascotaDTO mascotaDTO , Long id);
	
	
	public  ResponseEntity<Map<String, Object>> eliminarMascota(Long id);
	
	public  ResponseEntity<Map<String, Object>> eliminarLogicoMascota(Long id);

}
