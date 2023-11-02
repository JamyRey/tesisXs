package org.tesis.xs.exception;

public class DatabaseConexionException extends BasicException {
	public DatabaseConexionException() {}
	  
	  public DatabaseConexionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }
	  
	  public DatabaseConexionException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public DatabaseConexionException(String message) {
	    super(message);
	  }
	  
	  public DatabaseConexionException(Throwable cause) {
	    super(cause);
	  }
}
