package org.tesis.xs.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassEntity extends BasicEntity implements Serializable {

	private boolean activeClass;
	private int game;
	private List<BasicEntity> students;
	
	public ClassEntity() {
		super();
	}

	public boolean isActiveClass() {
		return activeClass;
	}

	public void setActiveClass(boolean activeClass) {
		this.activeClass = activeClass;
	}

	public List<BasicEntity> getStudents() {
		return students;
	}

	public void setStudents(List<BasicEntity> students) {
		this.students = students;
	}

	public int getGame() {
		return game;
	}

	public void setGame(int game) {
		this.game = game;
	}
	
	
	
	
	
	
}
