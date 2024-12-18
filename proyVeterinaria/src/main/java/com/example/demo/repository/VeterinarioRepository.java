package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.Veterinario;


@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{

	List<Veterinario> findAllByEstado(String activo);
	
	Veterinario findByUsuario(Usuario usuario);
	
}
