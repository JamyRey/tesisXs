package org.tesis.xs.entity.full;

import java.io.Serializable;
import java.util.List;

import org.tesis.xs.entity.ClassEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassFullEntity  implements Serializable{

	private List<ClassEntity> classes;

	public List<ClassEntity> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassEntity> classes) {
		this.classes = classes;
	}
	
}
