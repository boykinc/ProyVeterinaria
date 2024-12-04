package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Mascota;



public interface MascotaRepository extends JpaRepository<Mascota, Long> {

}
