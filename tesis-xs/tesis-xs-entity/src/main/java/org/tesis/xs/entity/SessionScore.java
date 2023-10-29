package org.tesis.xs.entity;

import java.io.Serializable;

import org.tesis.xs.enums.ScoresTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionScore implements Serializable{
	
	private ScoresTypes type;
	private boolean valid;
	
	public SessionScore() {
		super();
	}
	
	public SessionScore(ScoresTypes type, boolean valid) {
		super();
		this.type = type;
		this.valid = valid;
	}

	public ScoresTypes getType() {
		return type;
	}
	
	public void setType(ScoresTypes type) {
		this.type = type;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
