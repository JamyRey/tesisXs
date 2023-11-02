package org.tesis.xs.exception;

public class UnauthorizedException extends BasicException{
	  public UnauthorizedException() {}
	  
	  public UnauthorizedException(String message) {
	    super(message);
	  }
	  
	  public UnauthorizedException(Throwable cause) {
	    super(cause);
	  }
	  
	  public UnauthorizedException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public UnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }
}
