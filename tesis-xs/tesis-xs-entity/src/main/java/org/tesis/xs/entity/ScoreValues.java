package org.tesis.xs.entity;

import java.io.Serializable;

import org.tesis.xs.enums.ScoresTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScoreValues implements Serializable{
	
	private ScoresTypes type;
	private int valid;
	private int invalid;
	private double finalScore;
	
	public ScoreValues() {
		super();
	}
	
	public ScoreValues(ScoresTypes type) {
		super();
		this.type = type;
	}

	public void calFinal() {
		double temp = (valid*20)/(invalid+valid);;
		finalScore = temp;
	}
	
	public void sumScores(ScoreValues item) {
		if(type!=item.type)
			return;
		valid = valid+item.getValid();
		invalid = invalid+item.getInvalid();
		calFinal();
	}
	
	public ScoresTypes getType() {
		return type;
	}
	
	public void setType(ScoresTypes type) {
		this.type = type;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public int getInvalid() {
		return invalid;
	}

	public void setInvalid(int invalid) {
		this.invalid = invalid;
	}

	public double getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(double finalScore) {
		this.finalScore = finalScore;
	}

}
