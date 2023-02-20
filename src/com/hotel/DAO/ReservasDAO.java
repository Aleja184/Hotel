package com.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
}
