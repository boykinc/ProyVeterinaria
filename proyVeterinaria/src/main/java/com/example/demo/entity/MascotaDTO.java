package com.example.demo.entity;

public class MascotaDTO {
	
	private Long id_especie;
	private String nom_masco;
	private String raza;
	private String sexo;
	private int edad;
	private double peso_masco;
	private String propietario;
	private String estado;
	
	public Long getId_especie() {
		return id_especie;
	}
	public void setId_especie(Long id_especie) {
		this.id_especie = id_especie;
	}
	public String getNom_masco() {
		return nom_masco;
	}
	public void setNom_masco(String nom_masco) {
		this.nom_masco = nom_masco;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getPeso_masco() {
		return peso_masco;
	}
	public void setPeso_masco(double peso_masco) {
		this.peso_masco = peso_masco;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
