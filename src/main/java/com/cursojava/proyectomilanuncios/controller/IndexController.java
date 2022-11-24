package com.cursojava.proyectomilanuncios.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cursojava.proyectomilanuncios.dto.CategoriaDTO;
import com.cursojava.proyectomilanuncios.interfaces.ICategoriaService;
import com.cursojava.proyectomilanuncios.model.Categoria;


@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	ICategoriaService categoriaService;
	
	@GetMapping("/")
	public String list_all_categorias(Model model) {

		List<Categoria> categorias = categoriaService.list_all_categorias();
		List<CategoriaDTO> categoriasdto = new ArrayList();
		for (Categoria categoria : categorias) {
			CategoriaDTO categoriadto = new CategoriaDTO(categoria.getId_categoria(), categoria.getDescripcion());
			categoriasdto.add(categoriadto);
		}
		model.addAttribute("categorias", categoriasdto);
		return "index";
	}
	
	
	
	
	
}
	

