package org.tesis.xs.exception;

public class IdentityNotfoundException extends BasicException {
	 public IdentityNotfoundException() {}
	  
	  public IdentityNotfoundException(String message) {
	    super(message);
	  }
	  
	  public IdentityNotfoundException(Throwable cause) {
	    super(cause);
	  }
	  
	  public IdentityNotfoundException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public IdentityNotfoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }
}
