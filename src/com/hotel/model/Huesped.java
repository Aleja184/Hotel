package com.hotel.model;

import java.sql.Date;

public class Huesped {
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fecha_nacimiento;
	private String nacionalidad;
	private String numero;
	private Integer id_reserva;
	
	public Huesped(String nombre,String apellido,Date fecha_nacimiento,String nacionalidad,String numero,Integer id_reserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.numero = numero;
		this.id_reserva = id_reserva;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getNumero() {
		return numero;
	}

	public Integer getId_reserva() {
		return id_reserva;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
}
