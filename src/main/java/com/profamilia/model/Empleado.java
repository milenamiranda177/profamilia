package com.profamilia.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empleado")
public class Empleado {

	
	private String nombre;
	@Id
	private String identificacion;
	private String cargo;
	private Double salario;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Empleado(String nombre, String identificacion, String cargo, Double salario) {
		super();
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.cargo = cargo;
		this.salario = salario;
	}
	public Empleado() {
		super();
	}
	
	
	
	
}
