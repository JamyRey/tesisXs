package org.tesis.xs.exception;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MasterException extends ForbiddenException implements Serializable {

    ExceptionEntity entity;
    
    public MasterException() {
        super();
    }
    
    public MasterException(MasterExceptionEnum exception) {
        super(exception.getMessage());
        this.entity = exception.entity();
    }
    
    public MasterException(MasterExceptionEnum exception, String message) {
        super(message);
        this.entity = exception.entity();
    }
    
    public ExceptionEntity getEntity() {
        return entity;
    }
    
}
