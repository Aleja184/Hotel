package com.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hotel.model.Huesped;



public class HuespedDAO {
	final private Connection con;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}
	
	public boolean registro(Huesped huesped) {
		boolean resultado = false;
		try{
			final PreparedStatement statement = con.prepareStatement("INSERT INTO HUESPEDES(NOMBRE,APELLIDO,FECHA_NACIMIENTO,NACIONALIDAD,TELEFONO,ID_RESERVA)"
					+ "VALUES(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			try(statement){
				ejecutarRegistro(huesped, statement);
				resultado = true;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return resultado;
	}
	
	public void ejecutarRegistro(Huesped huesped, PreparedStatement statement) throws SQLException {
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFecha_nacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getNumero());
		statement.setInt(6, huesped.getId_reserva());
		statement.execute();
		
		final ResultSet resultSet = statement.getGeneratedKeys();
		
		try(resultSet){
			while(resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
			}
		}
	}
}
