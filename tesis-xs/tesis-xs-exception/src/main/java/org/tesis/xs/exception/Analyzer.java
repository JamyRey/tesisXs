package org.tesis.xs.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Analyzer {
	 private static final Logger log = LoggerFactory.getLogger(Analyzer.class);
	  
	  public static ErrorCode analyze(Throwable e) {
	    if (e instanceof IdentityNotfoundException) {
	      log.warn("Identidad no encontrada", e);
	      return ErrorCode.IdentityNotFound;
	    } 
	    if (e instanceof IdentityNotAvailableException) {
	      log.warn("Identidad no encontrada", e);
	      return ErrorCode.IdentityNotFound;
	    } 
	    if (e instanceof IdentityDeniedException) {
	      log.warn("Permiso negado para la accion", e);
	      return ErrorCode.PermissionDenied;
	    } 
	    if (e instanceof ForbiddenException) {
	      log.warn("Permiso negado para la accion", e);
	      return ErrorCode.PermissionDenied;
	    } 
	    if (e instanceof UnauthorizedException) {
	      log.warn("El usuario no tiene sesion o el token es invalido", e);
	      return ErrorCode.Unauthorized;
	    } 
	    if (e instanceof IdentityExistsException) {
	      log.warn("Ya existe un registro", e);
	      return ErrorCode.IdentityExists;
	    } 
	    if (e instanceof AuthenticationTimeoutException) {
	      log.error("Timeout en autenticacion", e);
	      return ErrorCode.AuthenticationTimeout;
	    } 
	    if (e instanceof DatabaseConexionException) {
	      log.error("Error conectando a la BD", e);
	      return ErrorCode.DatabaseConnectionError;
	    } 
	    if (e instanceof ConnectionFailedException) {
	      log.error("Error de conexion", e);
	      return ErrorCode.ConnectionFailed;
	    } 
	    log.error("Error desconocido", e);
	    return ErrorCode.Unknown;
	  }
	  
	  public static ErrorResponse analyzeForResponse(Throwable e) {
	    return new ErrorResponse(analyze(e));
	  }
	  
	  public static BasicException analyzeCode(int errorCode) {
	    ErrorCode code = ErrorCode.fromCode(errorCode);
	    switch (code) {
	      case AuthenticationTimeout:
	        return new AuthenticationTimeoutException();
	      case ConnectionFailed:
	        return new ConnectionFailedException();
	      case DatabaseConnectionError:
	        return new DatabaseConexionException();
	      case IdentityExists:
	        return new IdentityExistsException();
	      case IdentityNotFound:
	        return new IdentityNotfoundException();
	      case PermissionDenied:
	        return new PermissionException();
	      case UnknownErrorCode:
	      case Unknown:
	        return new BasicException();
	    } 
	    return new BasicException();
	  }
	  
	  public static BasicException analyzeCode(ErrorCode code) {
	    return analyzeCode(code.code);
	  }
}
