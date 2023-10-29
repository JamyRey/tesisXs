package org.tesis.xs.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameSession extends BasicEntity implements Serializable{

	private Instant startAt;
	private Instant endendAt;
	private List<SessionScore> scores;
	
	public Instant getStartAt() {
		return startAt;
	}
	
	public void setStartAt(Instant startAt) {
		this.startAt = startAt;
	}

	public Instant getEndendAt() {
		return endendAt;
	}

	public void setEndendAt(Instant endendAt) {
		this.endendAt = endendAt;
	}

	public List<SessionScore> getScores() {
		return scores;
	}

	public void setScores(List<SessionScore> scores) {
		this.scores = scores;
	}
	
}
