package com.hotel.controller;

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
	
}
