package com.cursojava.proyectomilanuncios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.proyectomilanuncios.interfaces.IUsuarioService;
import com.cursojava.proyectomilanuncios.model.Categoria;
import com.cursojava.proyectomilanuncios.model.Usuario;
import com.cursojava.proyectomilanuncios.repository.RoleRepository;
import com.cursojava.proyectomilanuncios.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	RoleRepository rolerepository;
	
	@Autowired
	UsuarioRepository usuariorepository;
	
	@Override
	public List<Usuario> usuarios_by_categoria(String categoria) {

		return null;
	}

	@Override
	public List<Usuario> list_all() {

		return (List<Usuario>) usuariorepository.findAll();
	}

	@Override
	public void delete(String user) {
		
		usuariorepository.deleteById(user);

	}

	@Override
	public void save(Usuario usuario) {
		
		usuariorepository.save(usuario);

		
	}

	@Override
	public Usuario find_by_user(String user) {
		
		return null;
	}

}
