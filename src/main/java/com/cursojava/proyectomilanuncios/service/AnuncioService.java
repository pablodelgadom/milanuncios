package com.cursojava.proyectomilanuncios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.proyectomilanuncios.interfaces.IAnuncioService;
import com.cursojava.proyectomilanuncios.model.Anuncio;
import com.cursojava.proyectomilanuncios.model.Categoria;
import com.cursojava.proyectomilanuncios.repository.AnuncioRepository;
import com.cursojava.proyectomilanuncios.repository.CategoriaRepository;
import com.cursojava.proyectomilanuncios.repository.UsuarioRepository;

@Service
public class AnuncioService implements IAnuncioService {

	@Autowired
	AnuncioRepository ar;

	@Autowired
	CategoriaRepository cr;
	
	@Autowired
	UsuarioRepository usuariorepository;
	
	

	@Override
	public List<Anuncio> find_by_id_categoria(int id) {
		Categoria categoria = cr.findById(id).orElse(null);
		if (categoria == null) {
			return null;
		} else {
			return categoria.getAnuncios();
		}
	}

	@Override
	public List<Anuncio> list_all() {
		return (List<Anuncio>) ar.findAll();
	}

	@Override
	public void delete_by_id(int id) {
		ar.deleteById(id);

	}

	@Override
	public List<Anuncio> list_all_by_user(String user) {
		
//		Anuncio anuncio = 

		return null;
	}

}
