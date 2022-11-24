package com.cursojava.proyectomilanuncios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cursojava.proyectomilanuncios.interfaces.IUsuarioService;
import com.cursojava.proyectomilanuncios.model.Usuario;
import com.cursojava.proyectomilanuncios.util.Usuario_v;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	IUsuarioService us;
	
	@GetMapping("/loginForm")
	public String loginForm(Model model)
	{
		model.addAttribute("usuario_v", new Usuario_v());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Model model, Usuario_v usuario_v, BindingResult result)
	{
		usuario_v.validateLogin(result);
		if (result.hasErrors()) {
			return "login";
		} else {
			Usuario usuario = new Usuario(usuario_v.getUser(), usuario_v.getPassword());
			Usuario u = us.find_by_user(usuario.getUser());
			System.out.println(u.getEmail());
			if(us.find_by_user(usuario.getUser())!=null && usuario_v.getUser().equals(u.getUser()) && usuario_v.getPassword().equals(u.getPassword()))
			{
				model.addAttribute("mensaje", "Logeado correctamente");
				return "index";
			}
			else
				return "login";
		}
		}
		
	}

