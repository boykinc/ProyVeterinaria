package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Especialidad;
import com.example.demo.entity.Especie;
import com.example.demo.entity.Mascota;
import com.example.demo.entity.MascotaDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Veterinario;
import com.example.demo.repository.EspecieRepository;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.service.MascotaService;

import jakarta.transaction.Transactional;


@Service
public class MascotaServiceImpl implements MascotaService{
	
	@Autowired
	private MascotaRepository dao;
	
	@Autowired
	private EspecieRepository daoEspe;
	
	@Override
	public ResponseEntity<Map<String, Object>> listMascotas(){
		Map<String, Object> respuesta = new LinkedHashMap<>();
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

	@Override
	public ResponseEntity<Map<String, Object>> listaMascotaPorId(Long id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		Optional<Mascota> mascota = dao.findById(id);

	    if (mascota.isPresent()) {
	        respuesta.put("mensaje", "Mascota Encontrada");
	        respuesta.put("fecha", new Date());
	        respuesta.put("status", HttpStatus.OK);
	        respuesta.put("mascota", mascota.get());
	        return ResponseEntity.ok().body(respuesta);
	    } else {
	        respuesta.put("mensaje", "No se encuentra un registro para el ID: " + id);
	        respuesta.put("fecha", new Date());
	        respuesta.put("status", HttpStatus.NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}

	@Override
	@Transactional
	public ResponseEntity<Map<String, Object>> agregaMascota(MascotaDTO mascotaDTO) {
	    Map<String, Object> respuesta = new LinkedHashMap<>();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String fechaActual = LocalDateTime.now().format(formatter);
	    Especie espEncontrada = daoEspe.findById(mascotaDTO.getId_especie())
	            .orElseThrow(() -> new IllegalArgumentException("Especie no encontrada"));
		  
	    Mascota mascota = new Mascota();
	    mascota.setEspecie(espEncontrada);
	    mascota.setNom_masco(mascotaDTO.getNom_masco());
	    mascota.setRaza(mascotaDTO.getRaza());
	    mascota.setSexo(mascotaDTO.getSexo());
	    mascota.setEdad(mascotaDTO.getEdad());
	    mascota.setPeso_masco(mascotaDTO.getPeso_masco());
	    mascota.setPropietario(mascotaDTO.getPropietario());
	    mascota.setEstado(mascotaDTO.getEstado());

	    dao.save(mascota);

	    respuesta.put("mensaje", "Se ha agregado correctamente el veterinario");
	    respuesta.put("fecha", fechaActual);
	    respuesta.put("status", HttpStatus.CREATED);
	    respuesta.put("Mascotas", mascota);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}

	@Override
	@Transactional
	public ResponseEntity<Map<String, Object>> actualizaMascota(MascotaDTO mascotaDTO, Long id) {
	    Map<String, Object> respuesta = new LinkedHashMap<>();
	    
	    Optional<Mascota> masEncontrada = dao.findById(id);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String fechaActual = LocalDateTime.now().format(formatter);

	    Especie espEncontrada = daoEspe.findById(mascotaDTO.getId_especie())
	            .orElseThrow(() -> new IllegalArgumentException("Especie no encontrada"));

	    
		if(masEncontrada.isPresent()) {
			Mascota mas = masEncontrada.get();
			mas.setEspecie(espEncontrada);
			  mas.setNom_masco(mascotaDTO.getNom_masco());
			    mas.setRaza(mascotaDTO.getRaza());
			    mas.setSexo(mascotaDTO.getSexo());
			    mas.setEdad(mascotaDTO.getEdad());
			    mas.setPeso_masco(mascotaDTO.getPeso_masco());
			    mas.setPropietario(mascotaDTO.getPropietario());
			    mas.setEstado(mascotaDTO.getEstado());	
			dao.save(mas);
			
			respuesta.put("mensaje", "Mascota modificado correctamente");
			respuesta.put("fecha", fechaActual);
			respuesta.put("status", HttpStatus.CREATED);
			respuesta.put("mascota", mas);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		}else {
			respuesta.put("mensaje", "Sin registros para el ID: " + id);
			respuesta.put("fecha", fechaActual);
			respuesta.put("status", HttpStatus.NOT_FOUND);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarMascota(Long id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 String fechaActual = LocalDateTime.now().format(formatter);
		 
		 Optional<Mascota> mascotaExiste = dao.findById(id);
		
		if (mascotaExiste.isPresent()) {
	        dao.delete(mascotaExiste.get());

	        respuesta.put("mensaje", "Mascota eliminada con éxito");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.OK);

	        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	    } else {
	        respuesta.put("mensaje", "No se realizo la Eliminacion, Mascota no encontrada");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.NOT_FOUND);

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarLogicoMascota(Long id) {
		Map<String, Object> respuesta = new LinkedHashMap<>();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 String fechaActual = LocalDateTime.now().format(formatter);
		Optional<Mascota> masEncontrado = dao.findById(id);
		
		
		if(masEncontrado.isPresent()) {
			Mascota mas = masEncontrado.get();
			mas.setEstado("I");
			dao.save(mas);
			respuesta.put("mensaje", "Mascota eliminado correctamente");
			respuesta.put("fecha", fechaActual);
			respuesta.put("status", HttpStatus.NO_CONTENT);
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
		}else {
			respuesta.put("mensaje", "Sin registros para el ID: " + id);
			respuesta.put("fecha", fechaActual);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}
	
	
	
	

}
