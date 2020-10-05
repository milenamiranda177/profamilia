package com.profamilia.main;


import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import com.profamilia.main.ConfiguradorSpring;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.sun.faces.config.FacesInitializer;

public class SpringInicializador extends FacesInitializer implements WebApplicationInitializer {

	@Override
    public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {
        super.onStartup(classes, servletContext);
    }
	@Override
    /*public void onStartup(ServletContext sc) throws ServletException {
		
		//AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        //appContext.register(ConfiguradorSpring.class);
		AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(ConfiguradorSpring.class);
        sc.addListener(new ContextLoaderListener(root));
        sc.addListener(new RequestContextListener());
        
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(root);
        sc.addListener(contextLoaderListener);
        
		sc.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");

	    sc.setInitParameter("javax.faces.FACELETS_VIEW_MAPPINGS", "*.xhtml");

	    ServletRegistration.Dynamic facesServlet = sc.addServlet("Faces Servlet", javax.faces.webapp.FacesServlet.class);
	    facesServlet.setLoadOnStartup(1);
	    facesServlet.addMapping("*.xhtml");
	    
	    
        
	   // ContextLoaderListener contextLoaderListener = new ContextLoaderListener(appContext);

        //sc.addListener(contextLoaderListener);
    }*/
	
	/*public void onStartup(final ServletContext sc) throws ServletException {
        sc.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
        sc.setInitParameter("javax.faces.FACELETS_VIEW_MAPPINGS", "*.xhtml");
        sc.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
        // Create the 'root' Spring application context
        final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(ConfiguradorSpring.class);
        sc.addListener(new ContextLoaderListener(root));
    }*/
	
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(ConfiguradorSpring.class);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher",
                new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);



        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(appContext);

        
        servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
        servletContext.setInitParameter("javax.faces.FACELETS_VIEW_MAPPINGS", "*.xhtml");
        servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
        servletContext.addListener(contextLoaderListener);

        // Filter.
        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);

        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
    }

	
}
