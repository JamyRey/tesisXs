package org.tesis.xs.entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameSession extends BasicEntity implements Serializable{

	private ClassEntity classE;
	private LocalDateTime startAt;
	private LocalDateTime endendAt;
	private List<SessionScore> scores;

	public List<SessionScore> getScores() {
		return scores;
	}

	public void setScores(List<SessionScore> scores) {
		this.scores = scores;
	}

	public LocalDateTime getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}

	public LocalDateTime getEndendAt() {
		return endendAt;
	}

	public void setEndendAt(LocalDateTime endendAt) {
		this.endendAt = endendAt;
	}

	public ClassEntity getClassE() {
		return classE;
	}

	public void setClassE(ClassEntity classE) {
		this.classE = classE;
	}
	
}
