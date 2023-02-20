package com.hotel.controller;

import com.hotel.DAO.HuespedDAO;
import com.hotel.factory.ConnectionFactory;
import com.hotel.model.Huesped;

public class HuespedController {
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public boolean registro(Huesped huesped) {
		return huespedDAO.registro(huesped);
	}
}
