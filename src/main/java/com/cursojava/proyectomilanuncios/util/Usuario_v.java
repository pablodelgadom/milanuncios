package com.cursojava.proyectomilanuncios.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class Usuario_v {

	private String user;
	private String password;
	private String email;
	private String role;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Usuario_v() {
		super();
	}

	public Usuario_v(String user, String password, String email, String role) {
		super();
		this.user = user;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public boolean validateLogin(Errors errors) {
		if (!StringUtils.hasText(user)) {
			errors.rejectValue("user", "badFormat", "Rellene el user");
		}

		if (!StringUtils.hasText(password)) {
			errors.rejectValue("password", "badFormat", "rellene el password");
		}
		return errors.hasErrors();
	}
	
	public boolean validate(Errors errors) {
		if (!StringUtils.hasText(user)) {
			errors.rejectValue("user", "badFormat", "Rellene el user");
		}

		if (!StringUtils.hasText(password)) {
			errors.rejectValue("password", "badFormat", "rellene el password");
		}
		if (!StringUtils.hasText(email)) {
			errors.rejectValue("email", "badFormat", "rellene el email");
		}

		return errors.hasErrors();
	}
}
