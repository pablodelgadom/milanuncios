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

import com.cursojava.proyectomilanuncios.dto.AnuncioDTO;
import com.cursojava.proyectomilanuncios.interfaces.IAnuncioService;
import com.cursojava.proyectomilanuncios.interfaces.ICategoriaService;
import com.cursojava.proyectomilanuncios.interfaces.IUsuarioService;
import com.cursojava.proyectomilanuncios.model.Anuncio;
import com.cursojava.proyectomilanuncios.model.Categoria;
import com.cursojava.proyectomilanuncios.model.Usuario;
import com.cursojava.proyectomilanuncios.service.AnuncioService;
import com.cursojava.proyectomilanuncios.service.CategoriaService;
import com.cursojava.proyectomilanuncios.service.UsuarioService;
import com.cursojava.proyectomilanuncios.util.Anuncio_v;
import com.cursojava.proyectomilanuncios.util.Categoria_v;
import com.cursojava.proyectomilanuncios.util.FindAnuncioForm;

@Controller
@RequestMapping("/anuncios")
public class AnuncioController {

	@Autowired
	AnuncioService anuncioService;

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/byCategoria/{id_categoria}")
	public String AnunciosByCategoria(@PathVariable("id_categoria") int id_categoria, Model model) {
		List<Anuncio> anuncios = anuncioService.find_by_id_categoria(id_categoria);
		List<AnuncioDTO> anunciosdto = new ArrayList<AnuncioDTO>();

		for (Anuncio anuncio : anuncios) {

			AnuncioDTO anunciodto = new AnuncioDTO(anuncio.getId_anuncio(), anuncio.getId_categoria(),
					anuncio.getFecha(), anuncio.getTitulo(), anuncio.getDescripcion(), anuncio.getPrecio(),
					anuncio.getUser());
			anunciosdto.add(anunciodto);
		}
		model.addAttribute("anuncios", anunciosdto);
		return "/listado_anuncios";

	}

	@GetMapping("/panelUser")
	public String find_anuncio_by_user(Model model, HttpSession sesion) {

		Usuario u = (Usuario) sesion.getAttribute("user");
		List<Anuncio> anuncios = anuncioService.find_anuncio_by_user(u.getUser());
		List<AnuncioDTO> anunciosdto = new ArrayList<AnuncioDTO>();

		for (Anuncio anuncio : anuncios) {

			AnuncioDTO anunciodto = new AnuncioDTO(anuncio.getId_anuncio(), anuncio.getId_categoria(),
					anuncio.getFecha(), anuncio.getTitulo(), anuncio.getDescripcion(), anuncio.getPrecio(),
					anuncio.getUser());
			anunciosdto.add(anunciodto);
		}
		model.addAttribute("anuncios", anunciosdto);
		return "/listado_anuncio_by_user";

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

	@GetMapping("/delete2/{id_anuncio}")
	public String delete_categoria2(@PathVariable("id_anuncio") int id_anuncio, Model model, HttpSession sesion) {
		try {
			anuncioService.delete_by_id(id_anuncio);
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("mensaje", "no se puede borrar la categoria");
		}

		return find_anuncio_by_user(model, sesion);

	}

	@GetMapping("/anuncio_created")
	public String alta_anuncio(Model model) {
		Anuncio_v anuncio_v = new Anuncio_v();
		model.addAttribute("anuncio_v", anuncio_v);
		return "anuncio_created";

	}

	@PostMapping("/grabar_anuncio")
	public String grabar_anuncio(Model model, Anuncio_v anuncio_v, BindingResult result, HttpSession sesion) {
		anuncio_v.validate(result);
		if (result.hasErrors()) {
			return "anuncio_created";
		} else {

			// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//				Date  fecha = (Date) format.parse(anuncio_v.getFecha());

			Usuario user = (Usuario) sesion.getAttribute("user");

			Anuncio anuncio = new Anuncio(0, Integer.parseInt(anuncio_v.getId_categoria()), "2022-11-28",
					anuncio_v.getTitulo(), anuncio_v.getDescripcion(), Double.parseDouble(anuncio_v.getPrecio()),
					user.getUser());
			anuncioService.save(anuncio);

		}
		model.addAttribute("anuncio_v", new Anuncio_v());
		return find_anuncio_by_user(model, sesion);

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
		return "listado_anuncio_by_user";
	}

	@GetMapping("/editar/{id_anuncio}")
	public String editar_anuncio(@PathVariable("id_anuncio") int id_anuncio, Model model) {
		Anuncio c = anuncioService.find_by_id(id_anuncio);
		model.addAttribute("anuncio_v", new Anuncio_v("" + c.getId_anuncio(),""+c.getCategoria(),c.getFecha(), c.getTitulo(),c.getDescripcion(),String.valueOf(c.getPrecio()),c.getUser()));
		return "anuncio_update";
	}

	@PostMapping("/update")
	public String modificar(Model model, Anuncio_v anuncio_v, BindingResult result, HttpSession sesion) {
		anuncio_v.validate(result);
		if (!result.hasErrors()) {
			Anuncio a = new Anuncio(Integer.parseInt(anuncio_v.getId_anuncio()),Integer.parseInt(anuncio_v.getId_categoria()),anuncio_v.getFecha(),anuncio_v.getTitulo(), anuncio_v.getDescripcion(),Double.parseDouble(anuncio_v.getPrecio()),anuncio_v.getUser());
			anuncioService.save(a);
			model.addAttribute("anuncio_v", new Anuncio_v());
			model.addAttribute("mensaje", "modificado correctamente");
			return find_anuncio_by_user(model, sesion);
		}
		return find_anuncio_by_user(model, sesion);
	}

}
