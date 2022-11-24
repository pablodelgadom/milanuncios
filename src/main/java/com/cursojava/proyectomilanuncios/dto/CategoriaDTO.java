package com.cursojava.proyectomilanuncios.dto;

public class CategoriaDTO {
	
	private int id_categoria;
	private String descripcion;
	
	
	public CategoriaDTO() {
		super();
	}


	public CategoriaDTO(int id_categoria, String descripcion) {
		super();
		this.id_categoria = id_categoria;
		this.descripcion = descripcion;
	}


	public int getId_categoria() {
		return id_categoria;
	}


	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
	
}
