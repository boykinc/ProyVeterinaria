package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="tb_cita")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Cita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cita;
	
	// Relación con la entidad Mascota
	/*@ManyToOne
	@JoinColumn(name = "id_mascota", nullable = false)
	private Mascota mascota; */
	
	// Relación con la entidad Veterinario
	/*@ManyToOne
	@JoinColumn(name = "id_veterinario", nullable = false)
	private Veterinario veterinario; */

	private LocalDateTime fec_cita;
	    
	private LocalDateTime fec_registrado; 

	private String motivo;

	private String estado;


	
}
