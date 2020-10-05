package com.profamilia.bean;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.profamilia.model.Empleado;
import com.profamilia.service.ServiceEmpleado;
import com.sun.faces.action.RequestMapping;

@Controller
@ManagedBean(name = "listarEmpleado")
@SessionScoped
public class ListarEmpleado {
	
	
	@Autowired(required=false)
	ServiceEmpleado service;

	private List<Empleado> lista;
	private Empleado empleado;
	private String operationMessage;
	
	@PostConstruct
	public void init() {
		lista = new ArrayList<Empleado>();
		empleado = new Empleado();
		/*ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) externalContext.getContext();
        WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).
                                   getAutowireCapableBeanFactory().
                                   autowireBean(this);*/
	}
	
	@RequestMapping(value = { "/" })
    public String homePage() {
        return "index";
    }
	
	public String crearEmpleado() throws IOException {
		try {
			service.crearEmpleado(empleado);
			FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Empleado creado correctamente"));
		    
		}catch (Exception ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error al guardar emmpleado"));
            
        }
		return null;
		
	}

	public List<Empleado> getLista() {
		lista = service.buscarTodos();
		return lista;
	}
	public void setLista(List<Empleado> lista) {
		this.lista = lista;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getOperationMessage() {
		return operationMessage;
	}

	public void setOperationMessage(String operationMessage) {
		this.operationMessage = operationMessage;
	}
	
	
	
	
}