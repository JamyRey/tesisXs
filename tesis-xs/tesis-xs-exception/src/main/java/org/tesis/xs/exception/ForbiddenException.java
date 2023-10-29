package org.tesis.xs.exception;

public class ForbiddenException extends BasicException {

	public ForbiddenException() {}
	  
	  public ForbiddenException(String message) {
	    super(message);
	  }
	  
	  public ForbiddenException(Throwable cause) {
	    super(cause);
	  }
	  
	  public ForbiddenException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public ForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }
	
}
