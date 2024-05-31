package com.hotel.factory;

import java.sql.Connection;

import java.sql.SQLException;

import javax.sql.DataSource;
import java.io.FileNotFoundException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.util.Properties;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;


public class ConnectionFactory {
	private DataSource datasource;
	
	public ConnectionFactory() {
		try {
			String jsonFilePath = "variables.json";
			JsonObject variables = new Gson().fromJson(new FileReader(jsonFilePath), JsonObject.class);
			String jdbcUrl = variables.get("dbJdbcUrl").getAsString();
			String user = variables.get("dbUser").getAsString();
    		String password = variables.get("dbPassword").getAsString();
			System.out.println(user);
			System.out.println(password);
			System.out.println(jdbcUrl);
			ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
        	pooledDataSource.setJdbcUrl(jdbcUrl);
        	pooledDataSource.setUser(user);
        	pooledDataSource.setPassword(password);
        	this.datasource = pooledDataSource;
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
	}
	
	public Connection recuperaConexion(){
		try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
