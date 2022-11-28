package com.cursojava.proyectomilanuncios.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cursojava.proyectomilanuncios.model.Role;
import com.cursojava.proyectomilanuncios.model.Usuario;
import com.cursojava.proyectomilanuncios.service.RoleService;
import com.cursojava.proyectomilanuncios.service.UsuarioService;
import com.cursojava.proyectomilanuncios.util.Usuario_v;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService us;

	@Autowired
	RoleService rs;

	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		model.addAttribute("usuario_v", new Usuario_v());
		return "login";
	}

	@PostMapping("/login")
	public String login(Model model, Usuario_v usuario_v, BindingResult result, HttpSession sesion) {
		usuario_v.validateLogin(result);
		if (result.hasErrors()) {
			return "login";
		} else {
			Usuario usuario = new Usuario(usuario_v.getUser(), usuario_v.getPassword());
			Usuario u = us.find_by_user(usuario.getUser());
			sesion.setAttribute("user", u);
			Usuario u2 = (Usuario) sesion.getAttribute("user");
			System.out.println(u2.getUser());
			System.out.println(u.getEmail());
			Set<Role> roles = u.getRoles();
			Role r = new Role(); 
			for (Role role : roles) {
				System.out.println(role);
				r.setRole(role.getRole());
				r.setFunciones(role.getFunciones());
			}
			if (us.find_by_user(usuario.getUser()) != null && usuario_v.getUser().equals(u.getUser())
					&& usuario_v.getPassword().equals(u.getPassword())) {
				model.addAttribute("mensaje", "Logeado correctamente");
				System.out.println(r.getRole());
				if (r.getRole().equals("admin")) {
					return "panel_admin";
				}
				if (r.getRole().equals("us")) {
					return "listado_anuncio_by_user";
				}

			} else
				return "login";
		}
		return "login";
	}

	@GetMapping("/registerForm")
	public String registerForm(Model model) {
		model.addAttribute("usuario_v", new Usuario_v());
		return "register";
	}

	@PostMapping("/register")
	public String register(Model model, Usuario_v usuario_v, BindingResult result) {
		usuario_v.validate(result);
		if (result.hasErrors()) {
			return "register";
		} else {
			if (us.find_by_user(usuario_v.getUser()) == null) {
				Usuario usuario = new Usuario();
				usuario.setUser(usuario_v.getUser());
				usuario.setPassword(usuario_v.getPassword());
				usuario.setEmail(usuario_v.getEmail());
				Set<Role> roles = new HashSet<Role>();
				Role rol = rs.find_by_role("us");
				System.out.println(rol.getRole());
				roles.add(rol);
				usuario.setRoles(roles);
				us.save(usuario);
				return "login";

			} else {
				// result.rejectValue("id_alumno","alumno ya existe");
				result.rejectValue("user", "badFormat", "user ya existe");
				return "register";
			}
		}
	}

}
