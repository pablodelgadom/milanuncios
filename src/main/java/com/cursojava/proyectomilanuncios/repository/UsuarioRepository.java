package com.cursojava.proyectomilanuncios.repository;

import org.springframework.data.repository.CrudRepository;

import com.cursojava.proyectomilanuncios.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

}
