package com.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hotel.model.Usuario;

public class UsuarioDAO {
	final private Connection con;
	
	public UsuarioDAO(Connection con) {
		this.con = con;
	}
	
	public boolean verificar(Usuario usuario) {
		boolean resultado = false;
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT username,password FROM USUARIOS");
			try(statement){
				ResultSet resultSet = statement.executeQuery();
				System.out.println(resultSet);
				while(resultSet.next()) {
					if(resultSet.getString("USERNAME").equals(usuario.getUsername()) && resultSet.getString("PASSWORD").equals(usuario.getPassword())) {
						resultado = true;
						break;						
					}
				}
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return resultado;
	}
}
