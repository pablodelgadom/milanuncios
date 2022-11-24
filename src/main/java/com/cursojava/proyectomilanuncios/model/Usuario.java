package com.cursojava.proyectomilanuncios.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "USUARIOS")

public class Usuario {
	
	@Id
	private String user;
	private String email;
	private String password;
	
	@ManyToMany()
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "role"))
	private Set<Role> roles;
	
	
	public Usuario() {
		super();
	}


	public Usuario(String user, String email, String password) {
		super();
		this.user = user;
		this.email = email;
		this.password = password;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	

}
