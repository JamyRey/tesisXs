package org.tesis.xs.exception;

public class ConexionFailedException extends ConnectionFailedException{
	public ConexionFailedException() {}
	  
	  public ConexionFailedException(String message) {
	    super(message);
	  }
	  
	  public ConexionFailedException(Throwable cause) {
	    super(cause);
	  }
	  
	  public ConexionFailedException(String message, Throwable cause) {
	    super(message, cause);
	  }
}
