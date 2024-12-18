package com.example.demo.entity;

import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name ="tb_historial")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Historial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_historial;
	
	private Date fec_histo;
	private String diagnos_histo;
	private String trata_histo;
	private String obser_histo;
	
	
	@ManyToOne
	@JoinColumn(name = "id_mascota", nullable = false)
	private Mascota mascota;
	
	
	
	@ManyToOne
	@JoinColumn(name = "id_veterinario", nullable = false)
	private Veterinario veterinario;
	
}
