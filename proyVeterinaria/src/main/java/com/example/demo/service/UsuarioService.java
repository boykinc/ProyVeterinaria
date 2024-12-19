package com.example.demo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Usuario;

public interface UsuarioService {

	public  ResponseEntity<Map<String , Object>> listUsuarios();
	
	public ResponseEntity<Map<String , Object>> listarUsuariosActivos();
	
	public ResponseEntity<Map<String, Object>> listaUsuarioPorId(Long id);
	
	public ResponseEntity<Map<String, Object>> agregaUsuario(Usuario usuario);
	
	public ResponseEntity<Map<String, Object>> actualizaUsuario(Usuario usuario , Long id);
	
	public ResponseEntity<Map<String, Object>> eliminarUsuarioLogico(Long id);

}
