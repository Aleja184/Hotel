package com.hotel.model;

import java.sql.Date;

public class Reservas {
	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Double valor;
	private String formaPago;
	
	public Reservas(Date fechaEntrada,Date fechaSalida,Double valor,String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	
	public Reservas(Integer id,Date fechaEntrada,Date fechaSalida,Double valor,String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public double getValor() {
		return valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
