package com.tpm.entity;

public class ScoreThreshold {
	private Integer scoreThresholdid;
	private Department department;
	private String score;
	public Integer getScoreThresholdid() {
		return scoreThresholdid;
	}
	public void setScoreThresholdid(Integer scoreThresholdid) {
		this.scoreThresholdid = scoreThresholdid;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
}
