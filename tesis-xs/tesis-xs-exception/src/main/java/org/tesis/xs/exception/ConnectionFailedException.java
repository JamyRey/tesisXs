package org.tesis.xs.exception;

public class ConnectionFailedException extends BasicException {
	 public ConnectionFailedException() {}
	  
	  public ConnectionFailedException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public ConnectionFailedException(String message) {
	    super(message);
	  }
	  
	  public ConnectionFailedException(Throwable cause) {
	    super(cause);
	  }
}
