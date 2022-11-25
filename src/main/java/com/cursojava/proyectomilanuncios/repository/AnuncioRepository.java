package com.cursojava.proyectomilanuncios.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cursojava.proyectomilanuncios.model.Anuncio;
import com.cursojava.proyectomilanuncios.model.Usuario;

public interface AnuncioRepository extends CrudRepository<Anuncio, Integer>{

//	@Query(value= "SELECT * FROM anuncios WHERE user=?1")
//	List<Anuncio> find_anuncio_by_user(Usuario usuario);
	
}
