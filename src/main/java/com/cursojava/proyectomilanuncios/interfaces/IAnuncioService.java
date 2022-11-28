package com.cursojava.proyectomilanuncios.interfaces;

import java.util.List;

import com.cursojava.proyectomilanuncios.model.Anuncio;
import com.cursojava.proyectomilanuncios.model.Usuario;

public interface IAnuncioService {
	
	public List<Anuncio> find_by_id_categoria(int id);
	public Anuncio find_by_id(int id);
	public List<Anuncio> list_all();
	public void delete_by_id(int id);
	public void save(Anuncio anuncio);
	public List<Anuncio> list_all_by_user(String user);
	List<Anuncio> find_anuncio_by_user(String usuario);
	public List<Anuncio> find_constaint_by_titulo(String titulo);
	

}
