package com.tpm.entity;

public class CurriculumMatrix {
	private Integer curriculumMatrixid;
	private Curriculum curriculum;
	private Ability ability;
	private Integer count;
	private String score;
	private Professional professional;
	public Professional getProfessional() {
		return professional;
	}
	public void setProfessional(Professional professional) {
		this.professional = professional;
	}
	public Integer getCurriculumMatrixid() {
		return curriculumMatrixid;
	}
	public void setCurriculumMatrixid(Integer curriculumMatrixid) {
		this.curriculumMatrixid = curriculumMatrixid;
	}
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	public Ability getAbility() {
		return ability;
	}
	public void setAbility(Ability ability) {
		this.ability = ability;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
}
