package com.cursojava.proyectomilanuncios.dto;

public class UsuarioDTO {

	private String user;
	private String email;
	private String password;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public UsuarioDTO(String user, String email, String password) {
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
