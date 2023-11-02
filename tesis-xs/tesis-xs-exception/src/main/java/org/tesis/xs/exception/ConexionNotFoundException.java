package org.tesis.xs.exception;

public class ConexionNotFoundException extends ConexionFailedException{
	public ConexionNotFoundException() {}
	  
	  public ConexionNotFoundException(String message) {
	    super(message);
	  }
	  
	  public ConexionNotFoundException(Throwable cause) {
	    super(cause);
	  }
	  
	  public ConexionNotFoundException(String message, Throwable cause) {
	    super(message, cause);
	  }
}
