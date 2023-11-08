package org.tesis.xs.manager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.tesis.xs.entity.StudentEntity;
import org.tesis.xs.exception.MasterException;
import org.tesis.xs.manager.imp.StudentDaoImp;
import org.tesis.xs.manager.service.AnalyzerException;
import org.tesis.xs.serv.StudentDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/student")
@RequestScoped
public class StudentController {

	private Logger log = LogManager.getLogger(this.getClass());
	private StudentDao dao = new StudentDaoImp();
	
	@GET
	@Path("/initialData")
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialData(
    		@Context HttpServletRequest request) {
        try {
			return Response.ok(dao.initialData()).build();
		} catch (Throwable e) {
            return AnalyzerException.analyzer(e, this.getClass());
        }
    }
	
	@POST
    @Path("/createStudent")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response createClass(
            @Context HttpServletRequest request, 
            StudentEntity entity) {
        try {
            log.debug(request.getPathInfo());
            return Response.ok(dao.createStudent(entity)).build();
        } catch (MasterException e){
			log.info(e);
        	return Response.status(Status.FORBIDDEN).entity(e.getEntity()).build();
		}  catch (Throwable e) {
            return AnalyzerException.analyzer(e, this.getClass());
        }
    }
	
	@PUT
    @Path("/updateStudent")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(
            @Context HttpServletRequest request, 
            StudentEntity entity) {
        try {
            log.debug(request.getPathInfo());
            return Response.ok(dao.updateStudent(entity)).build();
        } catch (MasterException e){
			log.info(e);
        	return Response.status(Status.FORBIDDEN).entity(e.getEntity()).build();
		}  catch (Throwable e) {        	
            return AnalyzerException.analyzer(e, this.getClass());
        }
    }
	
	@GET
    @Path("/getStudentById")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClassById(
            @Context HttpServletRequest request, 
            @QueryParam("id") int id) {
        try {
            log.debug(request.getPathInfo());
            log.debug(request.getQueryString());
            return Response.ok(dao.getStudentById(id)).build();
        } catch (MasterException e){
			log.info(e);
        	return Response.status(Status.FORBIDDEN).entity(e.getEntity()).build();
		} catch (Throwable e) {
            return AnalyzerException.analyzer(e, this.getClass());
        }
    }
	
	@DELETE
    @Path("/deleteStudent")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteStudent(
            @Context HttpServletRequest request, 
            @QueryParam("id") int id) {
        try {
            log.debug(request.getPathInfo());
            log.debug(request.getQueryString());
            dao.deleteStudent(id);
            return Response.ok().build();
        } catch (MasterException e){
			log.info(e);
        	return Response.status(Status.FORBIDDEN).entity(e.getEntity()).build();
		} catch (Throwable e) {
            return AnalyzerException.analyzer(e, this.getClass());
        }
    }

}
