package org.tesis.xs.exception;

public class BasicException extends Exception {

	public BasicException() {}
	  
	  public BasicException(String message) {
	    super(message);
	  }
	  
	  public BasicException(Throwable cause) {
	    super(cause);
	  }
	  
	  public BasicException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public BasicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }
	
}
