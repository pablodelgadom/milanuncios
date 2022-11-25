package com.cursojava.proyectomilanuncios.interfaces;

import java.util.List;

import com.cursojava.proyectomilanuncios.model.Anuncio;

public interface IAnuncioService {
	
	public List<Anuncio> find_by_id_categoria(int id);
	public List<Anuncio> list_all();
	public void delete_by_id(int id);
	public List<Anuncio> list_all_by_user(String user);
	

}
