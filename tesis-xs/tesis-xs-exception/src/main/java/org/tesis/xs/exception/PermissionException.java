package org.tesis.xs.exception;

public class PermissionException extends BasicException {

	 public PermissionException() {}
	  
	  public PermissionException(String message) {
	    super(message);
	  }
	  
	  public PermissionException(Throwable cause) {
	    super(cause);
	  }
	  
	  public PermissionException(String message, Throwable cause) {
	    super(message, cause);
	  }
	  
	  public PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }
	
}
