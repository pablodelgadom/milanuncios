package com.cursojava.proyectomilanuncios.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cursojava.proyectomilanuncios.dto.AnuncioDTO;
import com.cursojava.proyectomilanuncios.interfaces.IAnuncioService;
import com.cursojava.proyectomilanuncios.interfaces.ICategoriaService;
import com.cursojava.proyectomilanuncios.model.Anuncio;



@Controller
@RequestMapping("/anuncios")
public class AnuncioController {
	
	@Autowired
	IAnuncioService anuncioService;
	
	@Autowired
	ICategoriaService categoriaService;

	@GetMapping("/byCategoria/{id_categoria}")
	public String AnunciosByCategoria(@PathVariable("id_categoria") int id_categoria, Model model)
	{
		List<Anuncio> anuncios = anuncioService.find_by_id_categoria(id_categoria);
		List<AnuncioDTO> anunciosdto = new ArrayList();
		
		for (Anuncio anuncio : anuncios) {
			
			AnuncioDTO anunciodto = new AnuncioDTO(anuncio.getId_anuncio(),anuncio.getId_categoria(),anuncio.getFecha(),anuncio.getTitulo(),anuncio.getDescripcion(),anuncio.getPrecio(),anuncio.getUser());
			anunciosdto.add(anunciodto);
		}
		model.addAttribute("anuncios", anunciosdto);
		return "/listado_producto_by_categoria";

	}
	
}
