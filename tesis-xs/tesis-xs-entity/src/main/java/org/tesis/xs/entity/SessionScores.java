package org.tesis.xs.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionScores implements Serializable{
	
	private int type;
	private boolean valid;
	
	public SessionScores() {
		super();
	}
	
	public SessionScores(int type, boolean valid) {
		super();
		this.type = type;
		this.valid = valid;
	}

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
