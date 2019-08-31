package com.tpm.entity;

public class PTBasicInformation {
	private Integer ptBasicInformationid;
	private String learningTime;
	private String enableGrade;
	private String trainingObjectives;
	private String trainingRequirements;
	private String mainCourses;
	private String degreeCourses;
	private String degree;
	private	Integer abilityCount;
	private Department department;
	
	public Integer getAbilityCount() {
		return abilityCount;
	}
	public void setAbilityCount(Integer abilityCount) {
		this.abilityCount = abilityCount;
	}
	public Integer getPtBasicInformationid() {
		return ptBasicInformationid;
	}
	public void setPtBasicInformationid(Integer ptBasicInformationid) {
		this.ptBasicInformationid = ptBasicInformationid;
	}
	public String getLearningTime() {
		return learningTime;
	}
	public void setLearningTime(String learningTime) {
		this.learningTime = learningTime;
	}
	public String getEnableGrade() {
		return enableGrade;
	}
	public void setEnableGrade(String enableGrade) {
		this.enableGrade = enableGrade;
	}
	public String getTrainingObjectives() {
		return trainingObjectives;
	}
	public void setTrainingObjectives(String trainingObjectives) {
		this.trainingObjectives = trainingObjectives;
	}
	public String getTrainingRequirements() {
		return trainingRequirements;
	}
	public void setTrainingRequirements(String trainingRequirements) {
		this.trainingRequirements = trainingRequirements;
	}
	public String getMainCourses() {
		return mainCourses;
	}
	public void setMainCourses(String mainCourses) {
		this.mainCourses = mainCourses;
	}
	public String getDegreeCourses() {
		return degreeCourses;
	}
	public void setDegreeCourses(String degreeCourses) {
		this.degreeCourses = degreeCourses;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
