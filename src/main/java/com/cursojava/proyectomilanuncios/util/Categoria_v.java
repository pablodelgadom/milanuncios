package com.cursojava.proyectomilanuncios.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class Categoria_v {

	private String id_categoria;
	private String descripcion;

	public Categoria_v() {
		super();

	}

	public Categoria_v(String id_categoria, String descripcion) {
		super();
		this.id_categoria = id_categoria;
		this.descripcion = descripcion;
	}

	public String getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(String id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean validate(Errors errors) {
		if (!StringUtils.hasText(descripcion)) {
			errors.rejectValue("descripcion", "badFormat", "Rellene el user");
		}

		return errors.hasErrors();
	}

}
