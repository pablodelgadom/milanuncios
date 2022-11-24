package com.cursojava.proyectomilanuncios.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ROLES")

public class Role {
	
	@Id
	private String role;
	private String funciones;
	
	
	@ManyToMany()
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "role"), inverseJoinColumns = @JoinColumn(name = "user"))
	private Set<Usuario> usuarios;
	
	public Role() {
		super();
		
	}


	public Role(String role, String funciones) {
		super();
		this.role = role;
		this.funciones = funciones;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getFunciones() {
		return funciones;
	}


	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}
	
	
	

}
