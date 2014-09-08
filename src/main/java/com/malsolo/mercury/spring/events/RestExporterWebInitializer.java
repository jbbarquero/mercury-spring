package com.malsolo.mercury.spring.events;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.data.rest.webmvc.RepositoryRestDispatcherServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.malsolo.mercury.spring.events.repository.MongoDbRepositoryConfiguration;

public class RestExporterWebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		// Bootstrap repositories in root application context (Spring Data configuration at root: loaded before web context)
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(
				MongoDbRepositoryConfiguration.class
				//, JpaRepositoryConfiguration.class and so on
				);
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		// Enable Spring Data REST in the DispatcherServlet...
		//... with the special RepositoryRestExporterServlet
		RepositoryRestDispatcherServlet exporter = new RepositoryRestDispatcherServlet();
		//... or the standard DispatcherServlet
//		AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
//		webCtx.register(RestExporterWebConfiguration.class);
//		DispatcherServlet dispatcherServlet = new DispatcherServlet(webCtx);
	
		ServletRegistration.Dynamic reg = servletContext
				.addServlet("rest-exporter"
						, exporter);
//						, dispatcherServlet);
		reg.setLoadOnStartup(1);
		reg.addMapping("/*");
		
		
		
	}

}
