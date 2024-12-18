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
	
	
	
	@Override
	public ResponseEntity<Map<String, Object>> agregaUsuario(Usuario usuario) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        usuario.setEstado("A");
	        
	        Usuario usuarioGuardado = dao.save(usuario);

	        response.put("mensaje", "Usuario creado exitosamente.");
	        response.put("usuario", usuarioGuardado);
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        
	        response.put("mensaje", "Error al crear el usuario.");
	        response.put("error", e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}


	@Override
	public ResponseEntity<Map<String, Object>> actualizaUsuario(Usuario usuario, Long id) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        Optional<Usuario> usuarioExistente = dao.findById(id);

	        if (usuarioExistente.isPresent()) {
	            
	            Usuario usuarioActualizado = usuarioExistente.get();
	            usuarioActualizado.setNom_usu(usuario.getNom_usu());
	            usuarioActualizado.setApe_usu(usuario.getApe_usu());
	            usuarioActualizado.setFono_usu(usuario.getFono_usu());
	            usuarioActualizado.setDirec_usu(usuario.getDirec_usu());
	            usuarioActualizado.setEstado(usuario.getEstado());

	            Usuario usuarioGuardado = dao.save(usuarioActualizado);

	            response.put("mensaje", "Usuario actualizado exitosamente.");
	            response.put("usuario", usuarioGuardado);
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("mensaje", "Usuario no encontrado.");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    } catch (Exception e) {
	        response.put("mensaje", "Error al actualizar el usuario.");
	        response.put("error", e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarUsuarioLogico(Long id) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        Optional<Usuario> usuarioExistente = dao.findById(id);

	        if (usuarioExistente.isPresent()) {
	            Usuario usuario = usuarioExistente.get();
	            
	            if ("A".equals(usuario.getEstado())) {
	                usuario.setEstado("I");
	                
	                dao.save(usuario);

	                response.put("mensaje", "Usuario eliminado lógicamente (estado cambiado a 'I').");
	                response.put("usuario", usuario);
	                return ResponseEntity.ok(response);
	            } else {
	                response.put("mensaje", "El usuario ya está inactivo.");
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	            }
	        } else {
	            response.put("mensaje", "Usuario no encontrado.");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    } catch (Exception e) {
	        response.put("mensaje", "Error al realizar la eliminación lógica del usuario.");
	        response.put("error", e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

}
