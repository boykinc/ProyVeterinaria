package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Mascota;
import com.example.demo.entity.Veterinario;



public interface MascotaRepository extends JpaRepository<Mascota, Long> {
	
	List<Mascota> findAllByEstado(String activo);
	
	

}
