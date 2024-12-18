package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "tb_mascota")
@EntityListeners(AuditingEntityListener.class)
public class Mascota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_mascota;
	private String nom_masco;
	private String raza;
	private String sexo;
	private int edad;
	private double peso_masco;
	private String propietario;
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "id_especie", nullable = false)
	private Especie especie;
	
	
	

}
