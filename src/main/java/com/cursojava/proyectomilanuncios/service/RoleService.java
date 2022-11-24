package com.cursojava.proyectomilanuncios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.proyectomilanuncios.interfaces.IRoleService;
import com.cursojava.proyectomilanuncios.model.Role;
import com.cursojava.proyectomilanuncios.repository.RoleRepository;
@Service
public class RoleService implements IRoleService {
	
	@Autowired
	RoleRepository rr;

	@Override
	public Role find_by_role(String role) {
		return rr.findById(role).orElse(null);
	}

	
	
}
