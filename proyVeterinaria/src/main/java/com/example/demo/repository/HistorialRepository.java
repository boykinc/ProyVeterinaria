package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Historial;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {

}
