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

import com.example.demo.entity.Especie;
import com.example.demo.entity.Mascota;
import com.example.demo.entity.Veterinario;
import com.example.demo.repository.EspecieRepository;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.service.MascotaService;


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
	public ResponseEntity<Map<String, Object>> agregaMascota(Mascota mascota) {
	    Map<String, Object> respuesta = new LinkedHashMap<>();
	    Especie especie = mascota.getEspecie();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String fechaActual = LocalDateTime.now().format(formatter);

	    String sexo = mascota.getSexo();
	    String propi = mascota.getPropietario();

	    boolean inputCorrecto = false;
	    Optional<Especie> espeEncontrada = daoEspe.findById(especie.getId_especie());

	    
	    if (espeEncontrada.isPresent() && (sexo.equals("H") || sexo.equals("M")) && !propi.trim().isEmpty()) {
	        inputCorrecto = true;
	    }

	    if (inputCorrecto) {
	        dao.save(mascota);
	        respuesta.put("mensaje", "Se creó correctamente la mascota");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.CREATED);
	        respuesta.put("mascota", mascota);

	        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	    } else {
	        respuesta.put("mensaje", "Datos incorrectos: especie no encontrada, sexo inválido o propietario vacío");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.CONFLICT);

	        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> actualizaMascota(Mascota mascota, Long id) {
	    Map<String, Object> respuesta = new LinkedHashMap<>();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String fechaActual = LocalDateTime.now().format(formatter);

	    String sexo = mascota.getSexo();
	    String propi = mascota.getPropietario();

	    Optional<Mascota> mascotaExistenteOpt = dao.findById(id);

	  
	    if (!mascotaExistenteOpt.isPresent()) {
	        respuesta.put("mensaje", "La mascota con el ID especificado no existe.");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.NOT_FOUND);

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	    }

	   
	    Optional<Especie> espeEncontrada = daoEspe.findById(mascota.getEspecie().getId_especie());
	    if (!espeEncontrada.isPresent()) {
	        respuesta.put("mensaje", "La especie especificada no existe.");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.BAD_REQUEST);

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
	    }

	  
	    if (!(sexo.equals("H") || sexo.equals("M")) || propi.trim().isEmpty()) {
	        respuesta.put("mensaje", "Sexo inválido o propietario vacío.");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.BAD_REQUEST);

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
	    }

	    try {
	        // Obtener la mascota existente y actualizar sus campos
	        Mascota mascotaExistente = mascotaExistenteOpt.get();
	        mascotaExistente.setNom_masco(mascota.getNom_masco());
	        mascotaExistente.setRaza(mascota.getRaza());
	        mascotaExistente.setSexo(sexo);
	        mascotaExistente.setEdad(mascota.getEdad());
	        mascotaExistente.setPeso_masco(mascota.getPeso_masco());
	        mascotaExistente.setPropietario(propi);
	        mascotaExistente.setEstado(mascota.getEstado());
	        mascotaExistente.setEspecie(espeEncontrada.get());

	        // Guardar la mascota actualizada
	        Mascota mascotaActualizada = dao.save(mascotaExistente);

	        // Respuesta exitosa
	        respuesta.put("mensaje", "Mascota actualizada correctamente.");
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.OK);
	        respuesta.put("mascota", mascotaActualizada);

	        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	        
	    } catch (Exception e) {
	        // Manejo de errores inesperados
	        respuesta.put("mensaje", "Error al actualizar la mascota.");
	        respuesta.put("error", e.getMessage());
	        respuesta.put("fecha", fechaActual);
	        respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
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
