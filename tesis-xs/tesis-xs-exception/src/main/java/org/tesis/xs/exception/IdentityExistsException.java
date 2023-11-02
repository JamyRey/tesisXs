package org.tesis.xs.exception;

public class IdentityExistsException extends BasicException {
	 public IdentityExistsException() {}
	  
	  public IdentityExistsException(String message) {
	    super(message);
	  }
	  
	  public IdentityExistsException(Throwable cause) {
	    super(cause);
	  }
	  
	  public IdentityExistsException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public IdentityExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }
}
