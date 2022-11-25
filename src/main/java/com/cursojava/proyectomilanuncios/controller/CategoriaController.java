package com.cursojava.proyectomilanuncios.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cursojava.proyectomilanuncios.dto.CategoriaDTO;
import com.cursojava.proyectomilanuncios.interfaces.ICategoriaService;
import com.cursojava.proyectomilanuncios.model.Categoria;
import com.cursojava.proyectomilanuncios.util.Categoria_v;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	ICategoriaService categoriaService;
	
	@GetMapping("/listAll")
	public String list_all_categorias(Model model) {

		List<Categoria> categorias = categoriaService.list_all_categorias();
		List<CategoriaDTO> categoriasdto = new ArrayList<CategoriaDTO>();
		for (Categoria categoria : categorias) {
			CategoriaDTO categoriadto = new CategoriaDTO(categoria.getId_categoria(), categoria.getDescripcion());
			categoriasdto.add(categoriadto);
		}
		model.addAttribute("categorias", categoriasdto);
		return "categorias_admin";
	}
	
	@GetMapping("/delete/{id_categoria}")
	public String delete_categoria(@PathVariable("id_categoria") int id_categoria, Model model) {
		try {
			categoriaService.delete_categoria_by_id(id_categoria);
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("mensaje", "no se puede borrar la categoria");
		}

		return list_all_categorias(model);

	}
	
	@GetMapping("/createForm")
	public String categoriasForm(Model model) {
		model.addAttribute("categoria_v", new Categoria_v());
		return "categoria_create";
	}

	@PostMapping("/create")
	public String register(Model model, Categoria_v categoria_v, BindingResult result) {
		categoria_v.validate(result);
		if (result.hasErrors()) {
			return "categoria_create";
		} else {
			if (categoriaService.find_by_id(categoria_v.getId_categoria()) == null) {
				Categoria categoria = new Categoria();
				categoria.setDescripcion(categoria_v.getDescripcion());
				categoriaService.save(categoria);
				return list_all_categorias(model);

			} else {
				result.rejectValue("categoria", "badFormat", "Categoria ya existe");
				return "categoria_create";
			}
		}
	}
	
	

}
