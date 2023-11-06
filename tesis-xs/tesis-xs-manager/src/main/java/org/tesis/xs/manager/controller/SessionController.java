package org.tesis.xs.manager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.tesis.xs.entity.LoginEntity;
import org.tesis.xs.manager.imp.SessionDaoImp;
import org.tesis.xs.manager.service.AnalyzerException;
import org.tesis.xs.serv.SessionDao;

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

@Path("/session")
@RequestScoped
public class SessionController {
    
    private Logger log = LogManager.getLogger(this.getClass());
    
    private SessionDao dao = new SessionDaoImp();
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @Context HttpServletRequest request,
            LoginEntity login) {

        try {
            log.debug(request.getPathInfo());
            return Response.ok(dao.login(login)).build();
        } catch (Throwable e) {
            return AnalyzerException.analyzer(e, this.getClass());
        }
    }

    @GET
    @Path("/logout")
    public Response logout(
            @Context HttpServletRequest request,
            @QueryParam("userId") int userId) {
        try {
            log.debug(request.getPathInfo());
            log.debug(request.getQueryString());
            dao.logout(userId);
            return Response.ok().build();
        } catch (Throwable e) {
            return AnalyzerException.analyzer(e, this.getClass());
        }
    }

}
