package com.hotel.controller;

import java.util.List;

import com.hotel.DAO.ReservasDAO;
import com.hotel.factory.ConnectionFactory;
import com.hotel.model.Reservas;

public class ReservasController {
	private ReservasDAO reservasDAO;
	
	public ReservasController() {
		this.reservasDAO = new ReservasDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public boolean registro(Reservas reserva) {
		return reservasDAO.registro(reserva);
	}
	
	public List<Reservas> listar(){
		return reservasDAO.listar();
	}
	
	public void eliminar(Integer id) {
		reservasDAO.eliminar(id);
	}
}
