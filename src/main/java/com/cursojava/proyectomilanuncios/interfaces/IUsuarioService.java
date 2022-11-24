package com.cursojava.proyectomilanuncios.interfaces;

import java.util.List;

import com.cursojava.proyectomilanuncios.model.Usuario;


public interface IUsuarioService {
	
	public List<Usuario> usuarios_by_categoria(String categoria);
	public List<Usuario> list_all();
	public void delete(String user);
	public void save(Usuario usuario);
	public Usuario find_by_user(String user);


}
