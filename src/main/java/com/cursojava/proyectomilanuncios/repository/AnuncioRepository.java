package com.cursojava.proyectomilanuncios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cursojava.proyectomilanuncios.model.Anuncio;

public interface AnuncioRepository extends CrudRepository<Anuncio, Integer>{

	public List<Anuncio> findByTituloContainingIgnoreCase(String texto);

	@Query(value = "FROM Anuncio where categoria = :categoria and precio >= :precio_min and precio <= :precio_max")
	public List<Anuncio> anuncio_by_categoria_by_price_interval(@Param("categoria") int categoria, @Param("precio_min") double precio_min,
	@Param("precio_max") double precio_max);
	
}
