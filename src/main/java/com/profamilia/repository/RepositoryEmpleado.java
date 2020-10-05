package com.profamilia.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.TypedQuery;

import com.profamilia.model.Empleado;

@Repository
public class RepositoryEmpleado {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Empleado> buscarTodos() {

		TypedQuery<Empleado> consultaJPA= em.createQuery("select e from Empleado e",Empleado.class);
		return consultaJPA.getResultList();
	}
	
	@Transactional
	public void crearEmpleado(Empleado empleado) {
		em.persist(empleado);
	}
	
}
