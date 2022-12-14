package com.cursojava.proyectomilanuncios.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.proyectomilanuncios.interfaces.IAnuncioService;
import com.cursojava.proyectomilanuncios.model.Anuncio;
import com.cursojava.proyectomilanuncios.model.Categoria;
import com.cursojava.proyectomilanuncios.model.Usuario;
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
		

		return null;
	}

	@Override
	public List<Anuncio> find_anuncio_by_user(String usuario) {
		
		List<Anuncio> anuncios;
		Usuario usuarioaux = usuariorepository.findById(usuario).orElse(null);
		if (usuarioaux == null) {
			return null;
		} else {
			anuncios = new ArrayList<Anuncio>(usuarioaux.getAnuncios());
			return anuncios;
		}
	}


	@Override
	public List<Anuncio> find_constaint_by_titulo(String titulo) {

		return ar.findByTituloContainingIgnoreCase(titulo);
	}

	@Override
	public void save(Anuncio anuncio) {
		ar.save(anuncio);
		
	}

	@Override
	public Anuncio find_by_id(int id) {
		return ar.findById(id).orElse(null);
	}

	

}
