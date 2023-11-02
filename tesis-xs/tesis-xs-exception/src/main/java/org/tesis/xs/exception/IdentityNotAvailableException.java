package org.tesis.xs.exception;

public class IdentityNotAvailableException extends BasicException{
	public IdentityNotAvailableException() {}
	  
	  public IdentityNotAvailableException(String message) {
	    super(message);
	  }
	  
	  public IdentityNotAvailableException(Throwable cause) {
	    super(cause);
	  }
	  
	  public IdentityNotAvailableException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public IdentityNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }
}
