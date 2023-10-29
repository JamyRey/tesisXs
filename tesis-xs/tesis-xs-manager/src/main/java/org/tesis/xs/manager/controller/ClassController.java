package org.tesis.xs.manager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.tesis.xs.entity.ClassEntity;
import org.tesis.xs.manager.imp.ClassDaoImp;
import org.tesis.xs.manager.service.AnalyzerException;
import org.tesis.xs.serv.ClassDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/class")
@RequestScoped
public class ClassController {

	private Logger log = LogManager.getLogger(this.getClass());
	private ClassDao dao = new ClassDaoImp();
	
	@GET
	@Path("/initialData")
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialData(
    		@Context HttpServletRequest request) {
        try {
        	//log.debug(request.getPathInfo());
			return Response.ok(dao.initialData()).build();
		} catch (Throwable e) {
			// return Response.serverError().build();
            return AnalyzerException.analyzer(e, this.getClass());
        }
    }
	
	@POST
    @Path("/createClass")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response createClass(
            @Context HttpServletRequest request, 
            ClassEntity entity) {
        try {
            log.debug(request.getPathInfo());
            return Response.ok(dao.createClass(entity)).build();
        }  catch (Throwable e) {
            return AnalyzerException.analyzer(e, this.getClass());
        }
    }
	
	@PUT
    @Path("/updateClass")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateClass(
            @Context HttpServletRequest request, 
            ClassEntity entity) {
        try {
            log.debug(request.getPathInfo());
            return Response.ok(dao.updateClass(entity)).build();
        }  catch (Throwable e) {        	
            return AnalyzerException.analyzer(e, this.getClass());
        }
    }
	
}
