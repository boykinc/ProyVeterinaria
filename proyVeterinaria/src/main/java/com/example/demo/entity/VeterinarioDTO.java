package com.example.demo.entity;

public class VeterinarioDTO {

	private Long id_especialidad;
    private Long id_usuario;
    private String estado;
    
	public Long getId_especialidad() {
		return id_especialidad;
	}
	public void setId_especialidad(Long id_especialidad) {
		this.id_especialidad = id_especialidad;
	}
	public Long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
