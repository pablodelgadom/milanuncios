package com.cursojava.proyectomilanuncios.repository;

import org.springframework.data.repository.CrudRepository;

import com.cursojava.proyectomilanuncios.model.Anuncio;

public interface AnuncioRepository extends CrudRepository<Anuncio, Integer>{

}
