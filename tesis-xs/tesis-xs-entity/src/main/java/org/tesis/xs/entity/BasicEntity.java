package org.tesis.xs.entity;

import java.io.Serializable;
import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicEntity implements Serializable{
	
	private int id;
	private String name;
	private String description;
	private int status;
	private Instant createAt;
	private Instant updateAt;
	private Instant deleteAt;
	
	public BasicEntity() {
		super();
	}
	
	public BasicEntity(int id) {
		super();
		this.id = id;
	}
	
	public BasicEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public BasicEntity(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Instant getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Instant createAt) {
		this.createAt = createAt;
	}

	public Instant getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Instant updateAt) {
		this.updateAt = updateAt;
	}

	public Instant getDeleteAt() {
		return deleteAt;
	}

	public void setDeleteAt(Instant deleteAt) {
		this.deleteAt = deleteAt;
	}
	
}
