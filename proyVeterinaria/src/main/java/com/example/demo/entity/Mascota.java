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
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Mascota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_mascota;
	private String nom_masco;
	
	//id_especie  int not null
	
	private String raza;
	private String sexo;
	private LocalDateTime fec_nac;
	
	@Column(name = "precio", nullable = false, precision = 10, scale = 2)
	private BigDecimal precio;
	
	//id_usuario  int not null
	
	/*@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;*/
	
	@ManyToOne
	@JoinColumn(name = "id_especie", nullable = false)
	private Especie especie;
	
	
	

}
