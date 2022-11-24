package com.cursojava.proyectomilanuncios.interfaces;

import java.util.List;

import com.cursojava.proyectomilanuncios.model.Categoria;


public interface ICategoriaService {
	
	public List<Categoria> list_all_categorias();
	public void delete_categoria_by_id(int id_categoria);
	public Categoria save(Categoria c);

}
