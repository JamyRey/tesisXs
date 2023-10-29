package org.tesis.xs.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Entidad generica para almacenar detalles de una excepción
 * @author JOSE VASQUEZ
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class ExceptionEntity implements Serializable {

    private int code;
    private String langProperty;
    private String message;
    
    public ExceptionEntity() {
        super();
    }

    public ExceptionEntity(int code) {
        super();
        this.code = code;
    }

    public ExceptionEntity(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ExceptionEntity(int code, String langProperty, String message) {
        super();
        this.code = code;
        this.langProperty = langProperty;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLangProperty() {
        return langProperty;
    }

    public void setLangProperty(String langProperty) {
        this.langProperty = langProperty;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
