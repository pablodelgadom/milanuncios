package com.cursojava.proyectomilanuncios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/anuncios")
public class AnuncioController {

	@GetMapping("/byCategoria/{id_categoria}")
	public String AnunciosByCategoria(@PathVariable("id_categoria") int id_categoria, Model model)
	{
		return "";
	}
	
}
