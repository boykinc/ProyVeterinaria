package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Mascota;
import com.example.demo.serviceImpl.MascotaServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/mascotas")
public class MascotaController {
	
	@Autowired
	private MascotaServiceImpl mascotaService;
	
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listMascotas(){
		return mascotaService.listMascotas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listarPorId(@PathVariable Long id){

		return mascotaService.listaMascotaPorId(id);

	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> agregar(@RequestBody Mascota mascota){
		return mascotaService.agregaMascota(mascota);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> editar(@RequestBody Mascota mascota, @PathVariable Long id){
		return mascotaService.actualizaMascota(mascota, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id) {
	    return mascotaService.eliminarMascota(id);
	}
	
	@DeleteMapping("/logico/{id}")
	public ResponseEntity<Map<String, Object>> eliminarLogico(@PathVariable Long id) {
		
		return mascotaService.eliminarLogicoMascota(id);
	}

}