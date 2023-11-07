package org.tesis.xs.entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tesis.xs.enums.ScoresTypes;

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

	public List<ScoreValues> getAllScores() {
		return calScores();
	}
	
	public double getAllScoresValue() {
		List<ScoreValues> scores= calScores();
		if(calScores().size()==0)
			return 0;
		return scores.stream().mapToDouble(ScoreValues::getFinalScore).sum()/scores.size();
	}
	
	private List<ScoreValues> calScores(){
		List<ScoreValues> scores= new ArrayList<>();
		
		for (SessionScore score : this.scores) {
			
			if(scores.stream().anyMatch(s->s.getType()==score.getType())) {
				
				ScoreValues temp = scores.stream().filter(s->s.getType()==score.getType()).findFirst().get();
				if(score.isValid())
					temp.setValid(temp.getValid()+1);
				else
					temp.setInvalid(temp.getInvalid()+1);
			}else {
				ScoreValues temp = new ScoreValues(score.getType());
				if(score.isValid())
					temp.setValid(1);
				else
					temp.setInvalid(1);
				scores.add(temp);
			}
			
		}
		
		scores.forEach(s-> s.calFinal());
		
		return scores;
	}
	
	
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
