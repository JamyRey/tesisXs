package org.tesis.xs.manager.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tesis.xs.exception.ForbiddenException;
import org.tesis.xs.exception.MasterException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class AnalyzerException {

    
    public static Response analyzer(Throwable exception, Class<?> clazz) {

    	Logger log = LogManager.getLogger(clazz);
    	
        if (exception instanceof MasterException) {
        	MasterException masterE = (MasterException)exception;
        	log.info("Excepción de entidad: "+exception.getMessage());
        	log.debug("Excepción de entidad: ",exception);
        	return Response.status(Status.FORBIDDEN).entity(masterE.getEntity()).build();
        }
        else if (exception instanceof ForbiddenException) {
        	log.info("Excepción de acción prohibida: "+exception.getMessage());
        	log.debug("Excepción de acción prohibida: ",exception);
        	return Response.status(Status.FORBIDDEN).entity(exception).build();
        }
        else {
            log.error("Error inesperado: ",exception);
            return Response.serverError().entity(exception).build();
        }
    }


}
