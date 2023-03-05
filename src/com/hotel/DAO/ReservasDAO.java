package com.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hotel.model.Reservas;

public class ReservasDAO {
	final private Connection con;
	
	public ReservasDAO(Connection con) {
		this.con = con;
	}
	
	public boolean registro(Reservas reserva) {
		boolean resultado = false;
		try {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO RESERVAS(FECHA_ENTRADA,FECHA_SALIDA"
					+ ",VALOR,FORMA_PAGO) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			try(statement){
				ejecutarRegistro(reserva,statement);
				resultado = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	private void ejecutarRegistro(Reservas reserva, PreparedStatement statement) throws SQLException {
		statement.setDate(1, reserva.getFechaEntrada());
		statement.setDate(2, reserva.getFechaSalida());
		statement.setDouble(3, reserva.getValor());
		statement.setString(4, reserva.getFormaPago());
		statement.execute();
		
		final ResultSet resultSet = statement.getGeneratedKeys();
		
		try(resultSet){
			while(resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
			}
		}	
	}
	
	public List<Reservas> listar(){
		List<Reservas> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID,FECHA_ENTRADA,FECHA_SALIDA,VALOR,FORMA_PAGO FROM RESERVAS");
			try(statement){
				ResultSet resultSet = statement.executeQuery();
				while(resultSet.next()) {
					Reservas fila = new Reservas(
							resultSet.getInt("ID"),
							resultSet.getDate("FECHA_ENTRADA"),
							resultSet.getDate("FECHA_SALIDA"),
							resultSet.getDouble("VALOR"),
							resultSet.getString("FORMA_PAGO"));
					resultado.add(fila);
				}
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return resultado;
	}
	
	public void eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID=?");
			try(statement){
				statement.setInt(1, id);
				statement.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void modificar(Reservas reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET FECHA_ENTRADA=?,FECHA_SALIDA=?,VALOR=?,FORMA_PAGO=? WHERE ID=?");
			try(statement){
				statement.setDate(1, reserva.getFechaEntrada());
				statement.setDate(2, reserva.getFechaSalida());
				statement.setDouble(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				statement.setInt(5, reserva.getId());
				statement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
