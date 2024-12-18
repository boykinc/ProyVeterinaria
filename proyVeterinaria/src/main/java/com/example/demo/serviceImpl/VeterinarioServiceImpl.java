package com.example.demo.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Especialidad;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Veterinario;
import com.example.demo.entity.VeterinarioDTO;
import com.example.demo.repository.EspecialidadRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VeterinarioRepository;
import com.example.demo.service.VeterinarioService;

import jakarta.transaction.Transactional;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String fecha = sdf.format(new Date());
	
	@Autowired
	private VeterinarioRepository dao;
	
	@Autowired
	private EspecialidadRepository daoEsp;
	
	@Autowired
	private UsuarioRepository daoUsu;
	
	@Override
	public ResponseEntity<Map<String, Object>> listVeterinarios() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Veterinario> veterinarios = dao.findAll();
		
		if (!veterinarios.isEmpty()) {
			respuesta.put("mensaje", "Lista de Veterinarios");
			respuesta.put("fecha", fecha);
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("veterinarios", veterinarios);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("fecha", fecha);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}


	@Override
	public ResponseEntity<Map<String, Object>> listarVeterinariosActivos() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Veterinario> veterinarios = dao.findAllByEstado("A");

		if (!veterinarios.isEmpty()) {
			respuesta.put("mensaje", "Lista de Veterinarios");
			respuesta.put("fecha", fecha);
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("veterinarios", veterinarios);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("fecha", fecha);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}


	@Override
	public ResponseEntity<Map<String, Object>> buscarVeterinarioPorId(Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		Optional<Veterinario> veterinario = dao.findById(id);
		
		if(veterinario.isPresent()) {
			respuesta.put("mensaje", "Busqueda correcta");
			respuesta.put("fecha", fecha);
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("veterinario", veterinario);
			
			return ResponseEntity.ok().body(respuesta);
		}else {
			respuesta.put("mensaje", "Sin registros para el ID: " + id);
			respuesta.put("fecha", fecha);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}


	@Override
	@Transactional
	public ResponseEntity<Map<String, Object>> agregarVeterinario(VeterinarioDTO veterinarioDTO) {
	    Map<String, Object> respuesta = new HashMap<>();

	    Especialidad espEncontrada = daoEsp.findById(veterinarioDTO.getId_especialidad())
	            .orElseThrow(() -> new IllegalArgumentException("Especialidad no encontrada"));
	    Usuario usuEncontrado = daoUsu.findById(veterinarioDTO.getId_usuario())
	            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

	    Veterinario vetExistente = dao.findByUsuario(usuEncontrado);
	    if(vetExistente != null && usuEncontrado.getEstado().equals("A")) {
	    	respuesta.put("mensaje", "Ya existe un veterinario registrado con el id de usuario: " + usuEncontrado.getId_usuario());
		    respuesta.put("fecha", fecha);
		    respuesta.put("status", HttpStatus.BAD_REQUEST);

		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
	    }
	  
	    
	    Veterinario veterinario = new Veterinario();
	    veterinario.setEspecialidad(espEncontrada);
	    veterinario.setUsuario(usuEncontrado);
	    veterinario.setEstado(veterinarioDTO.getEstado());

	    dao.save(veterinario);

	    System.out.println("ID Veterinario asignado automáticamente: " + veterinario.getId_veterinario());

	    respuesta.put("mensaje", "Se ha agregado correctamente el veterinario");
	    respuesta.put("fecha", fecha);
	    respuesta.put("status", HttpStatus.CREATED);
	    respuesta.put("veterinario", veterinario);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	    
	}

	@Override
	@Transactional
	public ResponseEntity<Map<String, Object>> actualizarVeterinario(VeterinarioDTO veterinarioDTO, Long id) {
		Map<String,Object> respuesta = new HashMap<>();
		Optional<Veterinario> vetEncontrado = dao.findById(id);
		
		Especialidad espEncontrada = daoEsp.findById(veterinarioDTO.getId_especialidad())
	            .orElseThrow(() -> new IllegalArgumentException("Especialidad no encontrada"));
	    Usuario usuEncontrado = daoUsu.findById(veterinarioDTO.getId_usuario())
	            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
		
		if(vetEncontrado.isPresent()) {
			Veterinario vet = vetEncontrado.get();
			vet.setEspecialidad(espEncontrada);
			vet.setUsuario(usuEncontrado);
			vet.setEstado(veterinarioDTO.getEstado());
			
			dao.save(vet);
			
			respuesta.put("mensaje", "Veterinario modificado correctamente");
			respuesta.put("fecha", fecha);
			respuesta.put("status", HttpStatus.CREATED);
			respuesta.put("veterinario", vet);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		}else {
			respuesta.put("mensaje", "Sin registros para el ID: " + id);
			respuesta.put("fecha", fecha);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}


	@Override
	public ResponseEntity<Map<String, Object>> eliminarVeterinarioLogico(Long id) {
		Map<String,Object> respuesta = new HashMap<>();
		Optional<Veterinario> vetEncontrado = dao.findById(id);
		
		if(vetEncontrado.isPresent()) {
			Veterinario vet = vetEncontrado.get();
			vet.setEstado("I");
			dao.save(vet);
			respuesta.put("mensaje", "Veterinario eliminado correctamente");
			respuesta.put("fecha", fecha);
			respuesta.put("status", HttpStatus.NO_CONTENT);
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
		}else {
			respuesta.put("mensaje", "Sin registros para el ID: " + id);
			respuesta.put("fecha", fecha);
			respuesta.put("status", HttpStatus.NOT_FOUND);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}


	
}
