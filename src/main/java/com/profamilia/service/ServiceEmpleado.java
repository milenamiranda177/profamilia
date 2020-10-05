package com.profamilia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.profamilia.model.Empleado;

import com.profamilia.repository.RepositoryEmpleado;

@Service
public class ServiceEmpleado {
	@Autowired
	RepositoryEmpleado repositorio;
	
	@Transactional
	public List<Empleado> buscarTodos() {
		return repositorio.buscarTodos();
	}
	
	@Transactional
	public void crearEmpleado(Empleado empleado) {
		repositorio.crearEmpleado(empleado);
	}

}
