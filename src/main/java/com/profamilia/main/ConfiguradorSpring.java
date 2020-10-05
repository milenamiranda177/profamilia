package com.profamilia.main;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@Configuration
@ComponentScan("com.profamilia")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableWebMvc
public class ConfiguradorSpring implements ApplicationContextAware {
	
	// cual es el contexto de mi aplicacion
	private ApplicationContext contexto;
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter adaptador = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(adaptador);
		em.setPersistenceUnitName("empleados");
		return em;
	}
	
	
	@Override
	public void setApplicationContext(ApplicationContext contexto) throws BeansException {
		
		this.contexto=contexto;
		
	}
	
	@Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/vistas");
        resolver.setSuffix(".xmthl");
        return resolver;
    }
	
	public void addViewControllers(ViewControllerRegistry registry) {
	       registry.addViewController("/").setViewName("forward:/index.xhtml");
	}
	
	

	
	
}
