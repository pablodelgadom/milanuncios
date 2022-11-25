package com.cursojava.proyectomilanuncios.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.cursojava.proyectomilanuncios.model.Usuario;
import com.cursojava.proyectomilanuncios.service.CategoriaService;
import com.cursojava.proyectomilanuncios.util.Categoria_v;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
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
	public String create(Model model, Categoria_v categoria_v, BindingResult result, HttpSession sesion) {
		categoria_v.validate(result);
		if (result.hasErrors()) {
			return "categoria_create";
		} else {
			Categoria alumno = new Categoria(Integer.parseInt(categoria_v.getId_categoria()), categoria_v.getDescripcion());
			if (categoriaService.find_by_id(Integer.parseInt(categoria_v.getId_categoria())) != null) {
				model.addAttribute("mensaje", "Categoria ya existe");
			} else {
				categoriaService.save(alumno);
				model.addAttribute("categoria_v", new Categoria_v());
				model.addAttribute("mensaje", "Categoria dada de alta correctamente");
				return list_all_categorias(model);
			}

			return "categoria_create";
		}
	}
	
	@GetMapping("/editar/{id_categoria}")
	public String editar_categoria(@PathVariable("id_categoria") int id_categoria, Model model) {
		Categoria c = categoriaService.find_by_id(id_categoria);
		model.addAttribute("categoria_v", new Categoria_v(""+c.getId_categoria(), c.getDescripcion()));
		return "categoria_update";
	}
	
	@PostMapping("/update")
	public String modificar(Model model, Categoria_v categoria_v, BindingResult result) {
		categoria_v.validate(result);
		if (!result.hasErrors()) {
			Categoria c = new Categoria(Integer.parseInt(categoria_v.getId_categoria()), categoria_v.getDescripcion());
			categoriaService.save(c);
			model.addAttribute("categoria_v", new Categoria_v());
			model.addAttribute("mensaje", "modificado correctamente");
			return list_all_categorias(model);
		}
		return "editar_alumno";
	}

}
