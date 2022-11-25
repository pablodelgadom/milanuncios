package com.cursojava.proyectomilanuncios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.proyectomilanuncios.interfaces.ICategoriaService;
import com.cursojava.proyectomilanuncios.model.Categoria;
import com.cursojava.proyectomilanuncios.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService{
	
	@Autowired
	CategoriaRepository cr;

	@Override
	public List<Categoria> list_all_categorias() {
		return (List<Categoria>) cr.findAll();
	}

	@Override
	public void delete_categoria_by_id(int id_categoria) {
		cr.deleteById(id_categoria);		
	}

	@Override
	public Categoria save(Categoria c) {
		return cr.save(c);
	}

	@Override
	public Categoria find_by_id(int id_categoria) {
		return cr.findById(id_categoria).orElse(null);
	}
	
	

}
