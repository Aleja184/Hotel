package com.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Huesped> listar(){
		List<Huesped> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID,NOMBRE,APELLIDO,FECHA_NACIMIENTO,NACIONALIDAD,TELEFONO,ID_RESERVA FROM HUESPEDES");
			try(statement){
				ResultSet resultSet = statement.executeQuery();
				while(resultSet.next()) {
					Huesped fila = new Huesped(
							resultSet.getInt("ID"),
							resultSet.getString("NOMBRE"),
							resultSet.getString("APELLIDO"),
							resultSet.getDate("FECHA_NACIMIENTO"),
							resultSet.getString("NACIONALIDAD"),
							resultSet.getString("TELEFONO"),
							resultSet.getInt("ID_RESERVA"));
					resultado.add(fila);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
	
	public void eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID=?");
			try(statement){
				statement.setInt(1, id);
				statement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void modificar(Huesped huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET NOMBRE=?,APELLIDO=?,FECHA_NACIMIENTO=?,NACIONALIDAD=?,TELEFONO=? WHERE ID=?");
			try(statement){
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, huesped.getFecha_nacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getNumero());
				statement.setInt(6, huesped.getId());
				statement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
