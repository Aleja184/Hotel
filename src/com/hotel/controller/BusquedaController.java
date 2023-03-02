package com.hotel.controller;

import java.util.List;

import com.hotel.DAO.BusquedaDAO;
import com.hotel.factory.ConnectionFactory;
import com.hotel.model.Huesped;
import com.hotel.model.Reservas;

public class BusquedaController {
	private BusquedaDAO busquedaDAO;
	
	public BusquedaController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.busquedaDAO = new BusquedaDAO(factory.recuperaConexion());
	}
	
	public List<Huesped> listarHuesped(String apellido){
		return busquedaDAO.listarHuesped(apellido);
	}
	
	public List<Reservas> listarReservas(Integer id){
		return busquedaDAO.listarReservas(id);
	}
}
