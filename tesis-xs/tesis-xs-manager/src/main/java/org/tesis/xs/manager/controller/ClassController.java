package org.tesis.xs.manager.controller;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.tesis.xs.manager.imp.ClassDaoImp;
import org.tesis.xs.serv.ClassDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/class")
@RequestScoped
public class ClassController {

	
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
            return Response.status(Status.UNAUTHORIZED).entity(e).build();
        }
    }
	
	
	
}
