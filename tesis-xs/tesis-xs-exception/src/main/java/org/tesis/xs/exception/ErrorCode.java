package org.tesis.xs.exception;

public enum ErrorCode {
	  Unknown(1001),
	  AuthenticationTimeout(1002),
	  DatabaseConnectionError(1003),
	  IdentityExists(1011),
	  IdentityNotFound(1010),
	  PermissionDenied(1020),
	  Unauthorized(1025),
	  ConnectionFailed(1050),
	  UnknownErrorCode(1999);
	  
	  public final int code;
	  
	  ErrorCode(int code) {
	    this.code = code;
	  }
	  
	  public static ErrorCode fromCode(int code) {
	    for (ErrorCode ec : values()) {
	      if (ec.code == code)
	        return ec; 
	    } 
	    return UnknownErrorCode;
	  }
}
