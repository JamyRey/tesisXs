package org.tesis.xs.exception;

public class AuthenticationTimeoutException extends BasicException {
	 public AuthenticationTimeoutException() {}
	  
	  public AuthenticationTimeoutException(String message) {
	    super(message);
	  }
	  
	  public AuthenticationTimeoutException(Throwable cause) {
	    super(cause);
	  }
	  
	  public AuthenticationTimeoutException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public AuthenticationTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }
}
