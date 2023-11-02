package org.tesis.xs.exception;

public class ErrorResponse {
	
	  private int code;
	  
	  public ErrorResponse() {}
	  
	  public ErrorResponse(int code) {
	    this();
	    this.code = code;
	  }
	  
	  public ErrorResponse(ErrorCode errorCode) {
	    this(errorCode.code);
	  }
	  
	  public int getCode() {
	    return this.code;
	  }
	  
	  public void setCode(int code) {
	    this.code = code;
	  }
	}

