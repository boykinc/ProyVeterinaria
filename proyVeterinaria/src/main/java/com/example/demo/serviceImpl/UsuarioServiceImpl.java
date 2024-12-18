package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.Veterinario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VeterinarioRepository;
import com.example.demo.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository dao;
	
	@Autowired VeterinarioRepository daoVet;
	
	@Override
	public ResponseEntity<Map<String, Object>> listUsuarios() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Usuario> usuarios = dao.findAll();
		
		if (!usuarios.isEmpty()) {
			respuesta.put("mensaje", "Lista de Usuarios");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("Usuarios", usuarios);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> listarUsuariosActivos() {
		Map<String, Object> respuesta = new HashMap<>();
		List<Usuario> usuarios = dao.findAllByEstado("A");
		
		List<Usuario> usuariosNoVeterinarios = new ArrayList<>();
		
		for(Usuario usuario : usuarios) {
			Veterinario veterinario = daoVet.findByUsuario(usuario);
			
			if(veterinario == null) {
				usuariosNoVeterinarios.add(usuario);
			}
		}
		
		if (!usuariosNoVeterinarios.isEmpty()) {
			respuesta.put("mensaje", "Lista de Usuarios activos no veterinarios");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.OK);
			respuesta.put("usuarios", usuariosNoVeterinarios);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} else {
			respuesta.put("mensaje", "No existen registros de usuarios que no sean veterinarios");
			respuesta.put("fecha", new Date());
			respuesta.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
	}

}
