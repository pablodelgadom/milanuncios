package com.cursojava.proyectomilanuncios.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cursojava.proyectomilanuncios.dto.AnuncioDTO;
import com.cursojava.proyectomilanuncios.interfaces.IAnuncioService;
import com.cursojava.proyectomilanuncios.interfaces.ICategoriaService;
import com.cursojava.proyectomilanuncios.model.Anuncio;
import com.cursojava.proyectomilanuncios.util.Anuncio_v;
import com.cursojava.proyectomilanuncios.util.FindAnuncioForm;
import com.cursojava.proyectomilanuncios.util.Usuario_v;

@Controller
@RequestMapping("/anuncios")
public class AnuncioController {

	@Autowired
	IAnuncioService anuncioService;

	@Autowired
	ICategoriaService categoriaService;

	@GetMapping("/byCategoria/{id_categoria}")
	public String AnunciosByCategoria(@PathVariable("id_categoria") int id_categoria, Model model) {
		List<Anuncio> anuncios = anuncioService.find_by_id_categoria(id_categoria);
		List<AnuncioDTO> anunciosdto = new ArrayList();

		for (Anuncio anuncio : anuncios) {

			AnuncioDTO anunciodto = new AnuncioDTO(anuncio.getId_anuncio(), anuncio.getId_categoria(),
					anuncio.getFecha(), anuncio.getTitulo(), anuncio.getDescripcion(), anuncio.getPrecio(),
					anuncio.getUser());
			anunciosdto.add(anunciodto);
		}
		model.addAttribute("anuncios", anunciosdto);
		return "/listado_anuncio_by_categoria";

	}

	@GetMapping("/panelAdmin")
	public String panelAdmin(Model model) {
		List<Anuncio> anuncios = anuncioService.list_all();
		List<AnuncioDTO> anunciosdto = new ArrayList<AnuncioDTO>();
		for (Anuncio anuncio : anuncios) {
			AnuncioDTO anunciodto = new AnuncioDTO(anuncio.getId_anuncio(), anuncio.getId_categoria(),
					anuncio.getFecha(), anuncio.getTitulo(), anuncio.getDescripcion(), anuncio.getPrecio(),
					anuncio.getUser());
			anunciosdto.add(anunciodto);
		}
		model.addAttribute("anuncios", anunciosdto);
		return "panel_admin";
	}

	@GetMapping("/delete/{id_anuncio}")
	public String delete_categoria(@PathVariable("id_anuncio") int id_anuncio, Model model) {
		try {
			anuncioService.delete_by_id(id_anuncio);
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("mensaje", "no se puede borrar la categoria");
		}

		return panelAdmin(model);

	}

	@GetMapping("/anuncio_created")
	public String alta_anuncio(Model model) {
		Anuncio_v anuncio_v = new Anuncio_v();
		model.addAttribute("anuncio_v", anuncio_v);
		return "anuncio_created";

	}

	@PostMapping("buscar_anuncios")
	public String buscar_anuncios(Model model, FindAnuncioForm findAnuncioForm) {
		List<Anuncio> anuncios = anuncioService.find_constaint_by_titulo(findAnuncioForm.getTexto());
		List<AnuncioDTO> anuncios_dto = new ArrayList<>();
		for (Anuncio anuncio : anuncios) {
			AnuncioDTO anuncioDto = new AnuncioDTO(anuncio.getId_anuncio(), anuncio.getId_categoria(),
					anuncio.getFecha(), anuncio.getTitulo(), anuncio.getDescripcion(), anuncio.getPrecio(),
					anuncio.getUser());
			anuncios_dto.add(anuncioDto);
		}
		model.addAttribute("anuncios", anuncios_dto);
		return "listado_anuncio_by_categoria";
	}

}
