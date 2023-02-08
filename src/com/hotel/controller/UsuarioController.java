package com.hotel.controller;

import com.hotel.DAO.UsuarioDAO;
import com.hotel.factory.ConnectionFactory;
import com.hotel.model.Usuario;

public class UsuarioController {
	private UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public boolean validar(Usuario usuario) {
		return usuarioDAO.verificar(usuario);
	}
}
