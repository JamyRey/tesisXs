package org.tesis.xs.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentEntity extends BasicEntity implements Serializable{

	private int studentId;
	private List<GameSession> sessions;
	
	public int getStudentId() {
		return studentId;
	}
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public List<GameSession> getSessions() {
		return sessions;
	}
	
	public void setSessions(List<GameSession> sessions) {
		this.sessions = sessions;
	}
	
}
