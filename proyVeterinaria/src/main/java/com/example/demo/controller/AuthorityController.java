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

import com.example.demo.entity.Usuario;
import com.example.demo.serviceImpl.EspecialidadServiceImpl;
import com.example.demo.serviceImpl.EspecieServiceImpl;
import com.example.demo.serviceImpl.MascotaServiceImpl;
import com.example.demo.serviceImpl.UsuarioServiceImpl;
import com.example.demo.serviceImpl.VeterinarioServiceImpl;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthorityController {

	@Autowired
	private UsuarioServiceImpl service;
	
	@GetMapping("/usuarios")
	public ResponseEntity<Map<String, Object>> ListaUsuarios(){
		
		return service.listUsuarios();
	}
	
	@GetMapping("/usuarios/estado")
	public ResponseEntity<Map<String, Object>> ListarPorEstado(){
		
		return service.listarUsuariosActivos();
	}

	@PostMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> crearUsuario(@RequestBody Usuario usuario) {
        return service.agregaUsuario(usuario);
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Map<String, Object>> actualizarUsuario(
	        @RequestBody Usuario usuario, 
	        @PathVariable Long id) {
	    return service.actualizaUsuario(usuario, id);
	}
	
	@DeleteMapping("/Usuariologico/{id}")
	public ResponseEntity<Map<String, Object>> eliminarUsuarioLogico(@PathVariable Long id) {
	    return service.eliminarUsuarioLogico(id);
	}

	
	// Especialidad
	
	@Autowired
	private EspecialidadServiceImpl especialidadService;
	
	@GetMapping("/especialidades")
	public ResponseEntity<Map<String, Object>> ListaEspecialidades(){
		
		return especialidadService.listEspecialidades();
		
	}
	
	//Especie y Mascota
	
		@Autowired
		private EspecieServiceImpl especieService;
		
		@GetMapping("/especies")
		public ResponseEntity<Map<String, Object>> listEspecies(){
			return especieService.listEspecies();
		}
		
		
		@Autowired
		private MascotaServiceImpl mascotaService;
		public ResponseEntity<Map<String, Object>> listMascotas(){
			return mascotaService.listMascotas();
		}
	
}
