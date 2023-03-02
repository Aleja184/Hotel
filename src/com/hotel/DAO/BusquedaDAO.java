package com.hotel.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.model.Huesped;
import com.hotel.model.Reservas;

public class BusquedaDAO {
	private Connection con;
	
	public BusquedaDAO(Connection con) {
		this.con = con;
	}
	
	public List<Huesped> listarHuesped(String apellido){
		List<Huesped> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID,NOMBRE,APELLIDO,FECHA_NACIMIENTO,NACIONALIDAD,TELEFONO,ID_RESERVA FROM HUESPEDES");
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet){
					while(resultSet.next()) {
						
						String apellidoHuesped = resultSet.getString("APELLIDO");
						
						if(apellido.equalsIgnoreCase(apellidoHuesped)) {
							Huesped huesped = new Huesped(
									resultSet.getInt("ID"),
									resultSet.getString("NOMBRE"),
									resultSet.getString("APELLIDO"),
									resultSet.getDate("FECHA_NACIMIENTO"),
									resultSet.getString("NACIONALIDAD"),
									resultSet.getString("TELEFONO"),
									resultSet.getInt("ID_RESERVA"));
							resultado.add(huesped);
						}
					}
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
	
	public List<Reservas> listarReservas(Integer id){
		List<Reservas> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID,FECHA_ENTRADA,FECHA_SALIDA,VALOR,FORMA_PAGO FROM RESERVAS");
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet){
					while(resultSet.next()) {
						Integer idReserva = resultSet.getInt("ID");
						
						if(id.equals(idReserva)) {
							Reservas reserva = new Reservas(
									resultSet.getInt("ID"),
									resultSet.getDate("FECHA_ENTRADA"),
									resultSet.getDate("FECHA_SALIDA"),
									resultSet.getDouble("VALOR"),
									resultSet.getString("FORMA_PAGO"));
							resultado.add(reserva);
						}
					}
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
}
