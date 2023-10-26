package org.tesis.xs.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import org.tesis.xs.manager.service.BinderManager;

public class ApplicationConfig extends ResourceConfig {

	private Logger log = LogManager.getLogger(this.getClass());
	
	public ApplicationConfig() {
		
		log.debug("ApplicationConfig cargado");
		System.out.println("ApplicationConfig cargado");
		
		register(new BinderManager());
		packages(true, "org.tesis.xs.manager.controller");
		
	}
}

