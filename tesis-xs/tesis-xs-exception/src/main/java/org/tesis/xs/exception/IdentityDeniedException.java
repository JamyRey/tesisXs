package org.tesis.xs.exception;

public class IdentityDeniedException extends BasicException{
	public IdentityDeniedException() {}
	  
	  public IdentityDeniedException(String message) {
	    super(message);
	  }
	  
	  public IdentityDeniedException(Throwable cause) {
	    super(cause);
	  }
	  
	  public IdentityDeniedException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public IdentityDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }
}
